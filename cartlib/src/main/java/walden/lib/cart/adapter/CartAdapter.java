package walden.lib.cart.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import walden.lib.cart.ShopCart;
import walden.lib.cart.model.MerchantsBean;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.ICart;
import walden.lib.cart.usb.IShopCartAction;

/**
 * 这个仅仅使用于没有 商家 的情况下
 * <p>
 * Created by next on 17-1-19.
 */
public abstract class CartAdapter extends BaseAdapter implements IShopCartAction {
    protected ICart mCart;


    public CartAdapter(ICart cart) {
        mCart = cart;
        if (mCart instanceof ShopCart) {
            ((ShopCart) mCart).setICartAction(this);
        }
    }

    @Override
    public int getCount() {
        if (mCart != null && mCart.seeCart() != null)
            return mCart.seeCart().get(0).getShopList().size();
        return 0;
    }

    @Override
    public ShopBean getItem(int position) {
        return mCart.seeCart().get(0).getShopList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShopBean scm = getItem(position);
        return getItemView(position, convertView, parent, scm, mCart);
    }

    protected abstract View getItemView(int position, View convertView, ViewGroup parent, ShopBean b, ICart cart);

    public void selectAll(boolean isCheck) {
        for (ShopBean b : mCart.seeCart().get(0).getShopList()) {
            b.setJoin(isCheck);
        }
        notifyDataSetChanged();
        mCart.cashier();
    }

    @Override
    public void onCartErr(CartErrCode code) {

    }


    @Override
    public void onGoodsChange(List<MerchantsBean> shopList) {
        notifyDataSetChanged();
    }
}
