package walden.lib.cart;

import java.util.ArrayList;
import java.util.List;

import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.IServiceBean;

/**
 * 数据中间层 转换适配
 * <p>
 * Created by next on 17-1-19.
 */

public abstract class ShopBeanTransition<T extends IServiceBean>
{
	private List<T> sourceData;

	public ShopBeanTransition()
	{
	}

	/**
	 * 转换方法
	 *
	 * @return
	 */
	public List<ShopBean> transition(List<T> sd)
	{
		this.sourceData = sd;

		if (sourceData == null)
			throw new NullPointerException(" 转换 源数据 你不能拿空的糊弄我! ");

		List<ShopBean> result = new ArrayList<ShopBean>();
		for (T s : sourceData)
		{
			ShopBean bean = toShopBean(s);
			if (bean == null) continue;
			result.add(bean);
		}

		return result;
	}

	public List<T> getSourceData()
	{
		return sourceData;
	}

	/**
	 * 转换细节
	 *
	 * @param source
	 * @return return null 放弃这个数据
	 */
	public abstract ShopBean toShopBean(T source);
}
