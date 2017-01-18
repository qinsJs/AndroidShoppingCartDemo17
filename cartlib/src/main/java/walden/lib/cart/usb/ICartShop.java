package walden.lib.cart.usb;

import java.util.Collection;

/**
 * Created by next on 17-1-18.
 */

public interface ICartShop
{
	/**
	 * 删除商品
	 * <p>
	 * 需要 synchronize
	 *
	 * @param id
	 */
	void deleteShop(int id);

	/**
	 * 删除商品
	 *
	 * @param shopId
	 */
	void deleteShop(String shopId);

	/**
	 * 删除商品
	 *
	 * @param ids
	 */
	void deletcShop(int[] ids);

	/**
	 * 删除商品
	 *
	 * @param ids
	 */
	void deletcShop(Collection<String> ids);

	/**
	 * 商品数量+1
	 *
	 * @param id
	 */
	void shopAdd(int id);

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
	void shopMinus(int id);

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
	void changeShopCount(int count, int id);

	/**
	 * 改变商品
	 *
	 * @param count
	 * @param id
	 */
	void changeShopCount(int count, String id);
}
