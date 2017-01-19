package walden.lib.cart.usb;

import java.util.List;

import walden.lib.cart.model.ShopBean;

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
	 * @param b
	 */
	void addShop(T b);

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
	 * 查看服务器数据
	 *
	 * @return
	 */
	List<T> seeSourceCart();

	/**
	 * 查看选中的商品
	 *
	 * @return
	 */
	List<ShopBean> seeJoinShop();

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
