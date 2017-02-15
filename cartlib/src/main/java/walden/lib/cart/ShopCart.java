package walden.lib.cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import walden.lib.cart.model.MerchantsBean;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.ICart;
import walden.lib.cart.usb.IMerchantsModel;
import walden.lib.cart.usb.IShopCartAction;
import walden.lib.cart.usb.IShopModel;

/**
 * 购物车
 * <p>
 * Created by next on 17-1-19.
 */

public class ShopCart implements ICart {

    /**
     * 购物车商品
     */
    private List<MerchantsBean> shoppingCartOfGoods;

    /**
     * 联动回调
     */
    private IShopCartAction mICartAction;

    public ShopCart() {
        this(null);
    }

    public ShopCart(IShopCartAction iCartAction) {
        this(iCartAction, null);
    }

    public ShopCart(IShopCartAction iCartAction, List<IMerchantsModel> serviceModelList) {
        mICartAction = iCartAction;

        //防止空错误
        if (this.shoppingCartOfGoods == null)
            this.shoppingCartOfGoods = new ArrayList<>();
        else
            this.shoppingCartOfGoods = ShopCartTools.transitionMerchants(serviceModelList);
    }


    @Override
    public <T extends IMerchantsModel> void loadCart(List<T> shops) {
//如果这玩意是空的,就无法操作
        shoppingCartOfGoods = ShopCartTools.transitionMerchants(shops);
    }

    @Override
    @Deprecated
    public synchronized void addShop(String merchantsId, IShopModel shop) {
        addShop(merchantsId, new ShopBean(shop), mICartAction);
    }


    @Override
    @Deprecated
    public synchronized void cutHand(String merchantsId, Collection<IShopModel> shops) {
        for (IShopModel bean : shops) {
            addShop(merchantsId, new ShopBean(bean), null);
        }
        if (mICartAction != null) {
            mICartAction.onGoodsChange(seeCart());
            mICartAction.onCombinedChange(combined(), fee());
        }
    }

    /**
     * 添加商品的真正逻辑代码
     * <p>
     * 如果商品已经在购物车,找到对应商品 数量+1
     * <p>
     * 如果没有在购物车,添加进购物车
     *
     * @param shop 商品数据
     * @param ISCA 回调,通知跟新数据
     */
    private synchronized void addShop(String merchantsId, ShopBean shop, IShopCartAction ISCA) {
        MerchantsBean mb = null;

        if (merchantsId == null || merchantsId.length() == 0) {
            //默认加入第一个item
            if (shoppingCartOfGoods.size() == 0)
                shoppingCartOfGoods.add(mb = MerchantsBean.createDefault());
            else
                mb = shoppingCartOfGoods.get(0);
        } else {
            for (MerchantsBean mbb : shoppingCartOfGoods) {
                if (mbb.getMerchantsId().equals(merchantsId))
                    mb = mbb;
            }
            //找不到数据 就很尴尬了
            if (mb == null) return;
        }

        boolean b = false;

        //判断是否包含
        if (mb.getShopList() != null && mb.getShopList().size() > 0)
            for (ShopBean sb : mb.getShopList()) {
                if (sb.getId().equals(shop.getId()))
                    b = true;
            }

        if (b) {
            shopAdd(shop.getId());
        } else {
            mb.getShopList().add(shop);
        }

        if (ISCA != null) {
            ISCA.onGoodsChange(seeCart());
            ISCA.onCombinedChange(combined(), fee());
        }
    }

    @Override
    public List<MerchantsBean> seeCart() {
        return shoppingCartOfGoods;
    }


    @Override
    public List<ShopBean> seeJoinShop() {
        List<ShopBean> result = new ArrayList<>();
        for (MerchantsBean mb : shoppingCartOfGoods) {
            for (ShopBean sb : mb.getShopList()) {
                if (sb.isJoin())
                    result.add(sb);
            }
        }
        return result;
    }

    @Override
    public double combined() {
        double result = 0;

        for (MerchantsBean mb : shoppingCartOfGoods) {

            for (ShopBean sb : mb.getShopList()) {

                if (!canCombined(sb)) continue;
                //单间 * 数量
                result += sb.getPrice() * sb.getCount();
            }

        }

        return result;
    }

    /**
     * 是否可以运算
     *
     * @param b
     * @return true 可以运算 ,false 不允许运算
     */
    private boolean canCombined(ShopBean b) {
        if (b == null)//||b.getSource() 理论上这种情况不存在
            return false;
        //没有 加入 结算行列 , continue
        if (!b.isJoin()) return false;
        //如果 失效了 , continue
        if (b.isFailure()) return false;

        return true;
    }

    @Override
    public double fee() {
        double result = 0;

        for (MerchantsBean mb : shoppingCartOfGoods) {

            for (ShopBean sb : mb.getShopList()) {
                if (!canCombined(sb)) continue;
                result += sb.getFee();
            }
        }

        return result;
    }

    @Override
    public void cashier() {
        if (mICartAction != null)
            mICartAction.onCombinedChange(combined(), fee());
    }


    @Override
    public synchronized void deleteShop(String id) {
        deleteShop(id, mICartAction);
    }


    @Override
    public void deleteShop(Collection<String> ids) {
        for (String id : ids) {
            deleteShop(id, null);
        }
        if (mICartAction != null) {
            mICartAction.onGoodsChange(seeCart());
            mICartAction.onCombinedChange(combined(), fee());
        }
    }

    private void deleteShop(String id, IShopCartAction ISCA) {
        ShopBean bean = getShopBeanById(id);
        if (bean != null)//&& existing(bean) 多余了
        {
            for (MerchantsBean mb : shoppingCartOfGoods) {
                if (!mb.getShopList().contains(bean)) continue;
                mb.getShopList().remove(bean);
            }
        }

        if (ISCA != null) {
            ISCA.onGoodsChange(seeCart());
            ISCA.onCombinedChange(combined(), fee());
        }
    }


    @Override
    public synchronized void shopAdd(String id) {
        ShopBean bean = getShopBeanById(id);
        if (bean == null) return;
        changeShopCount(bean.getCount() + 1, id);
    }


    @Override
    public synchronized void shopMinus(String id) {
        ShopBean bean = getShopBeanById(id);
        if (bean == null) return;
        changeShopCount(bean.getCount() - 1, id);
    }


    @Override
    public void changeShopCount(int count, String id) {
        ShopBean bean = getShopBeanById(id);
        if (bean == null) return;
        if (count < 1) {
            if (mICartAction != null)
                mICartAction.onCartErr(IShopCartAction.CartErrCode.COUNT_LESS_THAN_1);
            return;
        }
        if (count < bean.getMin_count()) {
            if (mICartAction != null)
                mICartAction.onCartErr(IShopCartAction.CartErrCode.OUT_OF_MIN_COUNT);
            return;
        }
        if (count > bean.getMax_count()) {
            if (mICartAction != null)
                mICartAction.onCartErr(IShopCartAction.CartErrCode.OUT_OF_MAX_COUNT);
            return;
        }
        bean.setCount(count);

        if (mICartAction != null)
            mICartAction.onCombinedChange(combined(), fee());
    }

    @Override
    public ShopBean getShopBeanById(String id) {
        for (MerchantsBean mb : shoppingCartOfGoods) {
            for (ShopBean sb : mb.getShopList())
                if (sb.getId().equals(id))
                    return sb;
        }
        return null;
    }

    @Override
    public MerchantsBean getMerchantsBeanById(String merchantId) {
        for (MerchantsBean mb : shoppingCartOfGoods) {
            if (mb.getMerchantsId().equals(merchantId)) {
                return mb;
            }
        }
        return null;
    }

    public void setICartAction(IShopCartAction ICartAction) {
        mICartAction = ICartAction;
    }
}
