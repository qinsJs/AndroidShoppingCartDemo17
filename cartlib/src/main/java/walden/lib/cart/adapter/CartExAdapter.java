package walden.lib.cart.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import walden.lib.cart.ShopCart;
import walden.lib.cart.model.MerchantsBean;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.ICart;
import walden.lib.cart.usb.IShopCartAction;

/**
 * Created by walden on 2017/2/15.
 */

public abstract class CartExAdapter extends BaseExpandableListAdapter implements IShopCartAction {

    protected ICart mCart;


    public CartExAdapter(ICart cart) {
        mCart = cart;
        if (mCart instanceof ShopCart) {
            ((ShopCart) mCart).setICartAction(this);
        }
    }


    @Override
    public int getGroupCount() {
        return mCart.seeCart().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCart.seeCart().get(groupPosition).getShopList().size();
    }

    @Override
    public MerchantsBean getGroup(int groupPosition) {
        return mCart.seeCart().get(groupPosition);
    }

    @Override
    public ShopBean getChild(int groupPosition, int childPosition) {
        return mCart.seeCart().get(groupPosition).getShopList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        return getGroupView_(groupPosition, isExpanded, convertView, parent, getGroup(groupPosition));
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return getChildView_(groupPosition, childPosition, isLastChild, convertView, parent, getChild(groupPosition, childPosition));
    }

    public abstract View getGroupView_(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent, MerchantsBean b);

    public abstract View getChildView_(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent, ShopBean b);
    //--------------------------------------------------------------------------------

    @Override
    public void onCartErr(CartErrCode code) {

    }

    @Override
    public void onGoodsChange(List<MerchantsBean> shopList) {
        notifyDataSetChanged();
    }
}
