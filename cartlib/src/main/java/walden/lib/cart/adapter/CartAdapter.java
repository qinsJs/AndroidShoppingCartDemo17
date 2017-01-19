package walden.lib.cart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.ICart;
import walden.lib.cart.usb.IServiceBean;
import walden.lib.cart.usb.IShopCartAction;

/**
 * Created by next on 17-1-19.
 */

public abstract class CartAdapter<T extends IServiceBean> extends BaseAdapter implements IShopCartAction
{
	protected Context mContext;
	protected ICart<T> mCart;

	@Override
	public int getCount()
	{
		if (mCart != null && mCart.seeCart() != null)
			return mCart.seeCart().size();
		return 0;
	}

	@Override
	public ShopBean getItem(int position)
	{
		return mCart.seeCart().get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ShopBean b = mCart.seeCart().get(position);
		return getItemView(position, convertView, parent, b, mCart);
	}

	protected abstract View getItemView(int position, View convertView, ViewGroup parent, ShopBean b, ICart<T> cart);
}
