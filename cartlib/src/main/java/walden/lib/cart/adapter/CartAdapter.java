package walden.lib.cart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import walden.lib.cart.ShopCart;
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


	public CartAdapter(Context context, ICart<T> cart)
	{
		mContext = context;
		mCart = cart;
		if (mCart instanceof ShopCart)
		{
			((ShopCart) mCart).setICartAction(this);
		}
	}

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

	public void selectAll(boolean isCheck)
	{
		for (ShopBean b : mCart.seeCart())
		{
			b.setJoin(isCheck);
		}
		notifyDataSetChanged();
		mCart.cashier();
	}

	@Override
	public void onCartErr(CartErrCode code)
	{

	}

	@Override
	public void onGoodsChange(List<ShopBean> shopList)
	{
		//数据改变一定要刷新!
		notifyDataSetChanged();
	}
}
