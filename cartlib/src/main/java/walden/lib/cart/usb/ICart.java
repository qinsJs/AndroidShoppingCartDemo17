package walden.lib.cart.usb;

import java.util.List;

import walden.lib.cart.model.ShopCartModel;

/**
 * 购物车意图
 * <p>
 * Created by next on 17-1-18.
 */

public interface ICart<T extends IServiceBean> extends IShop
{
	/**
	 * 加载商品进购物车
	 *
	 * @param shops
	 */
	void loadCart(List<T> shops);

	/**
	 * 差看购物车里面的商品
	 *
	 * @return
	 */
	List<ShopCartModel> seeCart();


	/**
	 * 查看选中的商品
	 *
	 * @return
	 */
	List<ShopCartModel> seeJoinShop();

	/**
	 * 商品合计
	 *
	 * @return
	 */
	double combined();

	/**
	 * 计算配送品费用
	 *
	 * @return
	 */
	double fee();

	/**
	 * 出购物车,去收银台
	 */
	void cashier();

}
