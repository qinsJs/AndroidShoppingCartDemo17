package walden.lib.cart.usb;

import java.util.List;

import walden.lib.cart.model.ShopBean;

/**
 * Created by next on 17-1-18.
 */

public interface ICart extends ICartShop
{
	/**
	 * 加载商品进购物车
	 *
	 * @param shops
	 */
	void loadCart(List<ShopBean> shops);

	/**
	 * 添加一个商品进购物车
	 *
	 * @param shop
	 */
	void addShop(ShopBean shop);

	/**
	 * 砍手,添加分多分多分多商品进入购物车
	 *
	 * @param shops
	 */
	void cutHand(List<ShopBean> shops);

	/**
	 * 差看购物车里面的商品
	 *
	 * @return
	 */
	List<ShopBean> seeCart();

	/**
	 * 商品合计
	 *
	 * @return
	 */
	double shopCombined();

	/**
	 * 计算配送品费用
	 *
	 * @return
	 */
	double distributionCosts();

	/**
	 * 出购物车,去收银台
	 */
	void cashier();

}
