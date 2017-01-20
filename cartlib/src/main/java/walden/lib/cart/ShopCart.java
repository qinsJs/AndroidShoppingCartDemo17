package walden.lib.cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import walden.lib.cart.model.ShopBean;
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
	private ShopBeanTransition<T> mTransition;

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
		mTransition = new ShopBeanTransition<T>();
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
	public void addShop(IServiceBean b)
	{
		addShop(mTransition.toShopBean(b));
	}

	@Override
	public void cutHand(Collection<IServiceBean> shops)
	{

	}

	/**
	 * 如果商品已经在购物车数量+1
	 * <p>
	 * 如果没有在购物车,添加进购物车
	 *
	 * @param shop
	 */
	@Override
	public synchronized void addShop(ShopBean shop)
	{
		addShop(shop, mICartAction);
	}

	/**
	 * 循环使用 {@link #addShop(ShopBean)} 进行数据添加
	 *
	 * @param shops
	 */
	@Override
	public synchronized void cutHand(List<ShopBean> shops)
	{
		for (ShopBean bean : shops)
		{
			addShop(bean, null);
		}
		if (mICartAction != null)
		{
			mICartAction.onGoodsChange(seeCart());
			mICartAction.onCombinedChange(combined(), fee());
		}
	}


	private synchronized void addShop(ShopBean shop, IShopCartAction ISCA)
	{
		if (shoppingCartOfGoods.contains(shop))
		{
			shopAdd(shop.getId());
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


	@Override
	public List<ShopBean> seeCart()
	{
		return shoppingCartOfGoods;
	}

	@Override
	public List<T> seeSourceCart()
	{
		List<T> result = new ArrayList<>();
		for (ShopBean bean : shoppingCartOfGoods)
		{
			if (bean.getShopId() == null || bean.getShopId().length() == 0) continue;
			//确定 shopid
			String shopId = bean.getShopId();
			for (T b : mTransition.getSourceData())
			{
				//id 相同,表示是一个id
				if (b.isAn(shopId))
				{
					result.add(b);
					break;
				}
			}
		}
		return result;
	}

	@Override
	public List<ShopBean> seeJoinShop()
	{
		List<ShopBean> result = new ArrayList<>();
		for (ShopBean bean : shoppingCartOfGoods)
		{
			if (bean.isJoin())
				result.add(bean);
		}

		return result;
	}

	@Override
	public double combined()
	{
		double result = 0;

		for (ShopBean b : shoppingCartOfGoods)
		{
			if (!b.isJoin()) continue;
			//单间 * 数量
			result += b.getPrice() * b.getCount();
		}

		return result;
	}

	@Override
	public double fee()
	{
		double result = 0;

		for (ShopBean b : shoppingCartOfGoods)
		{
			if (!b.isJoin()) continue;
			result += b.getFee();
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
		ShopBean bean = getShopBeanById(id);
		if (bean != null && shoppingCartOfGoods.contains(bean))
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
		ShopBean bean = getShopBeanById(id);
		if (bean == null) return;
		changeShopCount(bean.getCount() + 1, id);
	}


	@Override
	public synchronized void shopMinus(String id)
	{
		ShopBean bean = getShopBeanById(id);
		if (bean == null) return;
		changeShopCount(bean.getCount() - 1, id);
	}


	@Override
	public void changeShopCount(int count, String id)
	{
		ShopBean bean = getShopBeanById(id);
		if (bean == null) return;
		if (count < 1)
		{
			if (mICartAction != null)
				mICartAction.onCartErr(IShopCartAction.CartErrCode.COUNT_LESS_THAN_1);
			return;
		}
		if (count < bean.getMin_count())
		{
			if (mICartAction != null)
				mICartAction.onCartErr(IShopCartAction.CartErrCode.OUT_OF_MIN_COUNT);
			return;
		}
		if (count > bean.getMax_count())
		{
			if (mICartAction != null)
				mICartAction.onCartErr(IShopCartAction.CartErrCode.OUT_OF_MAX_COUNT);
			return;
		}
		bean.setCount(count);

		if (mICartAction != null)
			mICartAction.onCombinedChange(combined(), fee());
	}

	@Override
	public ShopBean getShopBeanById(String id)
	{
		for (ShopBean bean : shoppingCartOfGoods)
		{
			if (bean.getId().equals(id))
				return bean;
		}
		return null;
	}

	public void setICartAction(IShopCartAction ICartAction)
	{
		mICartAction = ICartAction;
	}
}
