package walden.lib.cart.usb;

import java.util.List;

import walden.lib.cart.model.MerchantsBean;
import walden.lib.cart.model.ShopBean;

/**
 * 购物车意图
 * <p>
 * Created by next on 17-1-18.
 */

public interface ICart extends IShop {
    /**
     * 加载商品进购物车
     *
     * @param shops
     */
    <T extends IMerchantsModel> void loadCart(List<T> shops);

    /**
     * 差看购物车里面的商品
     *
     * @return
     */
    List<MerchantsBean> seeCart();


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
