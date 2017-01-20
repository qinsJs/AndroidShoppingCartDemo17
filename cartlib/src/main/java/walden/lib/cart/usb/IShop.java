package walden.lib.cart.usb;

import java.util.Collection;

import walden.lib.cart.model.ShopCartModel;

/**
 * 商品操作
 * <p>
 * Created by next on 17-1-18.
 */

public interface IShop
{
	/**
	 * @param b
	 */
	void addShop(IServiceBean b);


	/**
	 * 砍手,添加分多分多分多商品进入购物车
	 *
	 * @param shops
	 */
	void cutHand(Collection<IServiceBean> shops);

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

	ShopCartModel getShopBeanById(String id);
}
