package walden.lib.cart;

import java.util.ArrayList;
import java.util.List;

import walden.lib.cart.model.ShopBean;
import walden.lib.cart.model.ShopCartModel;
import walden.lib.cart.usb.IServiceBean;

/**
 * 数据中间层 转换适配
 * <p>
 * Created by next on 17-1-19.
 */

public class ShopBeanTransition
{
	private List<IServiceBean> sourceData;

	public ShopBeanTransition()
	{
	}

	/**
	 * 转换方法
	 *
	 * @return
	 */
	public List<ShopCartModel> transition(List<IServiceBean> sd)
	{
		this.sourceData = sd;

		if (sourceData == null)
			throw new NullPointerException(" 转换 源数据 你不能拿空的糊弄我! ");

		List<ShopCartModel> result = new ArrayList<ShopCartModel>();
		for (IServiceBean s : sourceData)
		{
			ShopBean bean = toShopBean(s);
			if (bean == null) continue;
			result.add(toShopCartModel(bean, s));
		}
		return result;
	}


	public ShopCartModel toShopCartModel(IServiceBean source)
	{
		return toShopCartModel(toShopBean(source), source);
	}

	public ShopCartModel toShopCartModel(ShopBean b, IServiceBean t)
	{
		return new ShopCartModel(b, t);
	}

	/**
	 * 转换细节
	 *
	 * @param source
	 * @return return null 放弃这个数据
	 */
	public ShopBean toShopBean(IServiceBean source)
	{
		return new ShopBean(source);
	}

}
