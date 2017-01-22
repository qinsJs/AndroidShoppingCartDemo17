package walden.shoppingcart.demo.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import walden.lib.cart.adapter.CartAdapter;
import walden.lib.cart.model.ShopBean;
import walden.lib.cart.model.ShopCartModel;
import walden.lib.cart.usb.ICart;
import walden.shoppingcart.demo.R;

/**
 * Created by next on 17-1-20.
 */

public class Test2Adapter extends CartAdapter<Test2Bean.ContentBean.GooddetailBean>
{
	Map<String, ShopBean> mMerchants;
	//设计失败!
	Map<String, Boolean> mMerchantsCheck;
	boolean isEdit;

	public Test2Adapter(Context context, ICart<Test2Bean.ContentBean.GooddetailBean> cart)
	{
		super(context, cart);
		mMerchants = new HashMap<>();
		mMerchantsCheck = new HashMap<>();
	}

	public void change()
	{
		isEdit = !isEdit;
		notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetChanged()
	{
		//mMerchants.clear();
		super.notifyDataSetChanged();
	}

	@Override
	protected View getItemView(int position, View convertView, ViewGroup parent, ShopBean b, Test2Bean.ContentBean.GooddetailBean g, ICart<Test2Bean.ContentBean.GooddetailBean> cart)
	{
		ViewHolder holder;
		if (convertView == null)
		{
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_ui_2, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		click c = new click(b, g);
//		click c = null;

		//判断是否需要显示商家信息
		boolean isFist;
		if (mMerchants.get(g.getMerchants()) == null)
		{
			//第一次 肯定是要显示的
			isFist = true;
		} else
		{
			isFist = mMerchants.get(g.getMerchants()).getId().equals(b.getId());
		}

		if (!isFist)
		{
			holder.item_merchants_ll.setVisibility(View.GONE);
			//设置选择监听
			//holder.cb_GroupItem.setOnClickListener(null);
			//附初始值

		} else
		{
			holder.item_merchants_ll.setVisibility(View.VISIBLE);
			//设置选择监听
			holder.cb_GroupItem.setOnClickListener(c);

			if (mMerchants.get(g.getMerchants()) == null)
				mMerchants.put(g.getMerchants(), b);

			holder.tv_Position.setText(g.getMerchants());
		}

		if (mMerchantsCheck.get(g.getMerchants()) == null)
			mMerchantsCheck.put(g.getMerchants(), false);
		else
			holder.cb_GroupItem.setChecked(mMerchantsCheck.get(g.getMerchants()));

		holder.cb_Item.setChecked(b.isJoin());
		holder.cb_Item.setOnClickListener(c);

		holder.tv_Price.setText("￥" + b.getPrice());
		holder.tv_Count.setText("x" + b.getCount());

		if (isEdit)
		{
			holder.ll_Nomal.setVisibility(View.GONE);
			holder.ll_Edit.setVisibility(View.VISIBLE);
			holder.iv_Delete.setVisibility(View.VISIBLE);
			holder.iv_Delete.setOnClickListener(c);

			holder.tv_Reduce.setOnClickListener(c);
			holder.tv_Add.setOnClickListener(c);

			holder.et_Count.setText(b.getCount() + "");
		} else
		{
			holder.ll_Nomal.setVisibility(View.VISIBLE);
			holder.ll_Edit.setVisibility(View.GONE);
			holder.iv_Delete.setVisibility(View.GONE);
			holder.iv_Delete.setOnClickListener(null);

			holder.tv_Reduce.setOnClickListener(null);
			holder.tv_Add.setOnClickListener(null);

			holder.tv_GoodName.setText(b.getName());
			holder.tv_LimitCount.setText(String.format("限购%d件", b.getMax_count()));
		}

		return convertView;
	}


	private class click implements View.OnClickListener
	{
		ShopBean b;
		Test2Bean.ContentBean.GooddetailBean g;

		public click(ShopBean b, Test2Bean.ContentBean.GooddetailBean g)
		{
			this.b = b;
			this.g = g;
		}

		@Override
		public void onClick(View v)
		{
			String id = b.getId();
			switch (v.getId())
			{
				//商家选择
				case R.id.cb_GroupItem:

					//if (mMerchantsCheck.get(g.getMerchants()) == null) return;

					boolean isMerchantsCheck = mMerchantsCheck.get(g.getMerchants());
					mMerchantsCheck.put(g.getMerchants(), !isMerchantsCheck);
					for (ShopCartModel scm : mCart.seeCart())
					{
						Test2Bean.ContentBean.GooddetailBean gb = (Test2Bean.ContentBean.GooddetailBean) scm.getSource();

						if (gb.getMerchants().equals(g.getMerchants()))
						{
							scm.getShop().setJoin(!isMerchantsCheck);
						}
					}
					break;
				//商品选择
				case R.id.cb_Item:
					boolean isShopCheck = !mCart.getShopBeanById(id).getShop().isJoin();
					mCart.getShopBeanById(id).getShop().setJoin(isShopCheck);

					if (isShopCheck)
					{
						//TODO 全选判断
						for (ShopCartModel shop : mCart.seeCart())
						{
							Test2Bean.ContentBean.GooddetailBean myBean = (Test2Bean.ContentBean.GooddetailBean) shop.getSource();
							ShopBean mShop = shop.getShop();

							if (!myBean.getMerchants().equals(g.getMerchants())) continue;

							//如果有一个子 item 有一个没有选中,旅行结束
							if (!mShop.isJoin())
							{
								mMerchantsCheck.put(g.getMerchants(), false);
								notifyDataSetChanged();
								return;
							}
						}
						mMerchantsCheck.put(g.getMerchants(), true);
//						如果这么循环完了,那就是全部选中了...
					} else
					{
						mMerchantsCheck.put(g.getMerchants(), false);
					}

					break;
				//单个商品删除
				case R.id.iv_Delete:
					mCart.deleteShop(id);
					break;
				//加
				case R.id.tv_Add:
					mCart.shopAdd(id);
					break;
				//减
				case R.id.tv_Reduce:
					mCart.shopMinus(id);
					break;
			}
			notifyDataSetChanged();
		}
	}


	@Override
	public void onCombinedChange(double combined, double fee)
	{
		// 懒 ++
		((Test2Activity) mContext).heji(combined + "", "运费:" + fee);
	}

	private class ViewHolder
	{
		//商家信息
		public View item_merchants_ll;
		public CheckBox cb_GroupItem;
		//		地址
		public TextView tv_Position;


		//商品信息
		public CheckBox cb_Item;

		//状态1
		public View ll_Nomal;
		public TextView tv_GoodName;
		public TextView tv_LimitCount;

		//		状态2 修改
		public View ll_Edit;
		//      减
		public View tv_Reduce;
		public TextView et_Count;
		//加
		public View tv_Add;

		public TextView tv_Price;
		public TextView tv_Count;
		public View iv_Delete;

		public ViewHolder(View q)
		{
			this.item_merchants_ll = q.findViewById(R.id.item_merchants_ll);
			this.cb_GroupItem = (CheckBox) q.findViewById(R.id.cb_GroupItem);
			this.tv_Position = (TextView) q.findViewById(R.id.tv_Position);
			this.cb_Item = (CheckBox) q.findViewById(R.id.cb_Item);
			this.ll_Nomal = q.findViewById(R.id.ll_Nomal);
			this.tv_GoodName = (TextView) q.findViewById(R.id.tv_GoodName);
			this.tv_LimitCount = (TextView) q.findViewById(R.id.tv_LimitCount);
			this.ll_Edit = q.findViewById(R.id.ll_Edit);
			this.tv_Reduce = q.findViewById(R.id.tv_Reduce);
			this.et_Count = (TextView) q.findViewById(R.id.et_Count);
			this.tv_Add = q.findViewById(R.id.tv_Add);
			this.tv_Price = (TextView) q.findViewById(R.id.tv_Price);
			this.tv_Count = (TextView) q.findViewById(R.id.tv_Count);
			this.iv_Delete = q.findViewById(R.id.iv_Delete);
		}
	}
}
