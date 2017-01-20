package walden.lib.cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import walden.lib.cart.model.ShopCartModel;
import walden.lib.cart.usb.ICart;
import walden.lib.cart.usb.IServiceBean;
import walden.lib.cart.usb.IShopCartAction;

/**
 * 购物车
 * <p>
 * Created by next on 17-1-19.
 */

public class ShopCart<T extends IServiceBean> implements ICart<T>
{

	/**
	 * 购物车商品
	 */
	private List<ShopCartModel> shoppingCartOfGoods;

	/**
	 * 数据转换器
	 */
	private ShopBeanTransition mTransition;

	/**
	 * 联动回调
	 */
	private IShopCartAction mICartAction;

	public ShopCart()
	{
		this(null);
	}

	public ShopCart(IShopCartAction iCartAction)
	{
		this(iCartAction, null);
	}

	public ShopCart(IShopCartAction iCartAction, List<ShopCartModel> shoppingCartOfGoods)
	{
		mTransition = new ShopBeanTransition();
		mICartAction = iCartAction;
		this.shoppingCartOfGoods = shoppingCartOfGoods;

		//防止空错误
		if (this.shoppingCartOfGoods == null)
			this.shoppingCartOfGoods = new ArrayList<>();
	}

	@Override
	public void loadCart(List shops)
	{
		//如果这玩意是空的,就无法操作
		shoppingCartOfGoods = mTransition.transition(shops);
	}


	@Override
	public synchronized void addShop(IServiceBean shop)
	{
		addShop(mTransition.toShopCartModel(shop), mICartAction);
	}


	@Override
	public synchronized void cutHand(Collection<IServiceBean> shops)
	{
		for (IServiceBean bean : shops)
		{
			addShop(mTransition.toShopCartModel(bean), null);
		}
		if (mICartAction != null)
		{
			mICartAction.onGoodsChange(seeCart());
			mICartAction.onCombinedChange(combined(), fee());
		}
	}

	/**
	 * 添加商品的真正逻辑代码
	 * <p>
	 * 如果商品已经在购物车,找到对应商品 数量+1
	 * <p>
	 * 如果没有在购物车,添加进购物车
	 *
	 * @param shop 商品数据
	 * @param ISCA 回调,通知跟新数据
	 */
	private synchronized void addShop(ShopCartModel shop, IShopCartAction ISCA)
	{
		if (existing(shop))
		{
			shopAdd(shop.getShop().getId());
		} else
		{
			shoppingCartOfGoods.add(shop);
		}

		if (ISCA != null)
		{
			ISCA.onGoodsChange(seeCart());
			ISCA.onCombinedChange(combined(), fee());
		}
	}

	/**
	 * 判断这个商品在购物车中是否存在
	 *
	 * @param shop true 代表已近存在于购物车中
	 * @return
	 */
	private boolean existing(ShopCartModel shop)
	{
		if (shoppingCartOfGoods.contains(shop)) return true;//不可靠

		for (ShopCartModel m : shoppingCartOfGoods)
		{
			if (m.getShop().getId().equals(shop.getShop().getId()))
				return true;
		}

		return false;
	}


	@Override
	public List<ShopCartModel> seeCart()
	{
		return shoppingCartOfGoods;
	}


	@Override
	public List<ShopCartModel> seeJoinShop()
	{
		List<ShopCartModel> result = new ArrayList<>();
		for (ShopCartModel bean : shoppingCartOfGoods)
		{
			if (bean.getShop().isJoin())
				result.add(bean);
		}

		return result;
	}

	@Override
	public double combined()
	{
		double result = 0;

		for (ShopCartModel b : shoppingCartOfGoods)
		{
			if (!canCombined(b)) continue;
			//单间 * 数量
			result += b.getShop().getPrice() * b.getShop().getCount();
		}

		return result;
	}

	/**
	 * 是否可以运算
	 *
	 * @param b
	 * @return true 可以运算 ,false 不允许运算
	 */
	private boolean canCombined(ShopCartModel b)
	{
		if (b == null || b.getShop() == null)//||b.getSource() 理论上这种情况不存在
			return false;
		//没有 加入 结算行列 , continue
		if (!b.getShop().isJoin()) return false;
		//如果 失效了 , continue
		if (b.getShop().isFailure()) return false;

		return true;
	}

	@Override
	public double fee()
	{
		double result = 0;

		for (ShopCartModel b : shoppingCartOfGoods)
		{
			if (!canCombined(b)) continue;
			result += b.getShop().getFee();
		}

		return result;
	}

	@Override
	public void cashier()
	{
		//1.计算勾选商品
		//2.计算价格
		//TODO 还没有想好
		if (mICartAction != null)
			mICartAction.onCombinedChange(combined(), fee());
	}


	@Override
	public synchronized void deleteShop(String id)
	{
		deleteShop(id, mICartAction);
	}


	@Override
	public void deleteShop(Collection<String> ids)
	{
		for (String id : ids)
		{
			deleteShop(id, null);
		}
		if (mICartAction != null)
		{
			mICartAction.onGoodsChange(seeCart());
			mICartAction.onCombinedChange(combined(), fee());
		}
	}

	private void deleteShop(String id, IShopCartAction ISCA)
	{
		ShopCartModel bean = getShopBeanById(id);
		if (bean != null)//&& existing(bean) 多余了
		{
			shoppingCartOfGoods.remove(bean);
		}

		if (ISCA != null)
		{
			ISCA.onGoodsChange(seeCart());
			ISCA.onCombinedChange(combined(), fee());
		}
	}


	@Override
	public synchronized void shopAdd(String id)
	{
		ShopCartModel bean = getShopBeanById(id);
		if (bean == null || bean.getShop() == null) return;
		changeShopCount(bean.getShop().getCount() + 1, id);
	}


	@Override
	public synchronized void shopMinus(String id)
	{
		ShopCartModel bean = getShopBeanById(id);
		if (bean == null || bean.getShop() == null) return;
		changeShopCount(bean.getShop().getCount() - 1, id);
	}


	@Override
	public void changeShopCount(int count, String id)
	{
		ShopCartModel bean = getShopBeanById(id);
		if (bean == null || bean.getShop() == null) return;
		if (count < 1)
		{
			if (mICartAction != null)
				mICartAction.onCartErr(IShopCartAction.CartErrCode.COUNT_LESS_THAN_1);
			return;
		}
		if (count < bean.getShop().getMin_count())
		{
			if (mICartAction != null)
				mICartAction.onCartErr(IShopCartAction.CartErrCode.OUT_OF_MIN_COUNT);
			return;
		}
		if (count > bean.getShop().getMax_count())
		{
			if (mICartAction != null)
				mICartAction.onCartErr(IShopCartAction.CartErrCode.OUT_OF_MAX_COUNT);
			return;
		}
		bean.getShop().setCount(count);

		if (mICartAction != null)
			mICartAction.onCombinedChange(combined(), fee());
	}

	@Override
	public ShopCartModel getShopBeanById(String id)
	{
		for (ShopCartModel bean : shoppingCartOfGoods)
		{
			if (bean.getShop().getId().equals(id))
				return bean;
		}
		return null;
	}

	public void setICartAction(IShopCartAction ICartAction)
	{
		mICartAction = ICartAction;
	}
}
