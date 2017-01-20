package walden.shoppingcart.demo.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import walden.lib.cart.adapter.CartAdapter;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.ICart;
import walden.shoppingcart.demo.R;

/**
 * Created by next on 17-1-19.
 */

public class Test1Adapter extends CartAdapter<Test1Bean.DataBean>
{
	public Test1Adapter(Context context, ICart<Test1Bean.DataBean> cart)
	{
		super(context, cart);

	}

	@Override
	protected View getItemView(int position, View convertView, ViewGroup parent, ShopBean b, Test1Bean.DataBean dataBean, ICart<Test1Bean.DataBean> cart)
	{
		ViewHolder holder;
		if (convertView == null)
		{
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.ui_1_item, parent, false);
			holder.checks = (CheckBox) convertView.findViewById(R.id.checks);//选择框
			holder.p_img1 = (ImageView) convertView.findViewById(R.id.p_img1);//商品缩略图
			holder.p_name = (TextView) convertView.findViewById(R.id.p_name);//名称
			holder.p_price = (TextView) convertView.findViewById(R.id.p_price);//价格
			holder.p_mprice = (TextView) convertView.findViewById(R.id.p_mprice);//过时价格
			holder.p_discount = (TextView) convertView.findViewById(R.id.p_discount);//折扣
			holder.p_news = (TextView) convertView.findViewById(R.id.p_news);//成色
			holder.p_stock = (TextView) convertView.findViewById(R.id.p_stock);//库存
			holder.p_number = (TextView) convertView.findViewById(R.id.p_number);//数量
			holder.minus = (ImageView) convertView.findViewById(R.id.minus);//减
			holder.add = (ImageView) convertView.findViewById(R.id.add);//加

			holder.p_discount.setVisibility(View.VISIBLE);
			holder.p_news.setVisibility(View.VISIBLE);

			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		click cccc = new click(b);

		holder.p_name.setText(b.getName());
		holder.p_price.setText(b.getPrice() + "");
		holder.p_number.setText(b.getCount() + "");

		if (b.isJoin())
		{
			holder.checks.setChecked(true);
		} else
		{
			holder.checks.setChecked(false);
		}
		holder.checks.setOnClickListener(cccc);
		holder.minus.setOnClickListener(cccc);
		holder.add.setOnClickListener(cccc);

		return convertView;
	}


	private class click implements View.OnClickListener
	{
		private ShopBean sBean;

		public click(ShopBean sBean)
		{
			this.sBean = sBean;
		}

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
				case R.id.checks:
					sBean.setJoin(!sBean.isJoin());
					mCart.cashier();
					break;
				case R.id.add:
					mCart.shopAdd(sBean.getId());
					break;
				case R.id.minus:
					mCart.shopMinus(sBean.getId());
					break;
			}
			notifyDataSetChanged();
		}
	}


	private class ViewHolder
	{
		private CheckBox checks;
		private ImageView p_img1;
		private TextView p_name;
		private TextView p_price;
		private TextView p_mprice;
		private TextView p_discount;
		private TextView p_news;
		private TextView p_stock;
		private TextView p_number;
		private ImageView minus;
		private ImageView add;
	}


	@Override
	public void onCartErr(CartErrCode code)
	{

	}


	@Override
	public void onCombinedChange(double combined, double fee)
	{
		Toast.makeText(mContext, "合计", Toast.LENGTH_LONG).show();
		//偷懒
		((Test1Activity) mContext).heji((combined + fee) + "");
	}
}
