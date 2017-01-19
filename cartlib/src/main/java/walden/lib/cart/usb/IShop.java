package walden.lib.cart.usb;

import java.util.Collection;

import walden.lib.cart.model.ShopBean;

/**
 * 商品操作
 * <p>
 * Created by next on 17-1-18.
 */

public interface IShop
{
	/**
	 * 删除商品
	 *
	 * @param id
	 */
	void deleteShop(String id);


	/**
	 * 删除商品
	 *
	 * @param ids
	 */
	void deleteShop(Collection<String> ids);


	/**
	 * 商品数量+1
	 *
	 * @param id
	 */
	void shopAdd(String id);

	/**
	 * 商品数量-1
	 *
	 * @param id
	 */
	void shopMinus(String id);


	/**
	 * 改变商品
	 *
	 * @param count
	 * @param id
	 */
	void changeShopCount(int count, String id);

	ShopBean getShopBeanById(String id);
}
