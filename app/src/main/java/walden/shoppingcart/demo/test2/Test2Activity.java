package walden.shoppingcart.demo.test2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import walden.lib.cart.ShopCart;
import walden.lib.cart.usb.ICart;
import walden.shoppingcart.demo.A;
import walden.shoppingcart.demo.R;

/**
 * Created by next on 17-1-20.
 */

public class Test2Activity extends AppCompatActivity implements View.OnClickListener
{
	TextView btn_Edit;
	ListView expandableListView;
	//全选
	CheckBox cb_SelectAll;
	//合计
	TextView tv_AllMoney;
	//运费
	TextView tv_Transport;

	Test2Adapter mAdapter;

	ICart<Test2Bean.ContentBean.GooddetailBean> mCart;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_ui_2);
		btn_Edit = (TextView) findViewById(R.id.btn_Edit);
		expandableListView = (ListView) findViewById(R.id.expandableListView);
		cb_SelectAll = (CheckBox) findViewById(R.id.cb_SelectAll);
		tv_AllMoney = (TextView) findViewById(R.id.tv_AllMoney);
		tv_Transport = (TextView) findViewById(R.id.tv_Transport);

		cb_SelectAll.setOnClickListener(this);
		btn_Edit.setOnClickListener(this);

		initData();
	}

	public static void toActivity(Context c)
	{
		c.startActivity(new Intent(c, Test2Activity.class));
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			//编辑
			case R.id.btn_Edit:
				mAdapter.change();
				break;
			//全选
			case R.id.cb_SelectAll:
				mAdapter.selectAll(cb_SelectAll.isChecked());
				break;
		}
	}

	private void initData()
	{
		Test2Bean tb2 = null;
		String josn = A.getJson(this, "test2.json");
		tb2 = new Gson().fromJson(josn, Test2Bean.class);

		List<Test2Bean.ContentBean.GooddetailBean> data = new ArrayList<>();
		for (Test2Bean.ContentBean tcb : tb2.getContent())
		{
			for (Test2Bean.ContentBean.GooddetailBean tcg : tcb.getGooddetail())
			{
				tcg.setMerchants(tcb.getAdress());
				data.add(tcg);
			}
		}

		mCart = new ShopCart<>();
		mCart.loadCart(data);

		mAdapter = new Test2Adapter(this, mCart);

		expandableListView.setAdapter(mAdapter);
	}

	public void heji(String heji, String yunfei)
	{
		tv_AllMoney.setText(heji);
		tv_Transport.setText(yunfei);
	}

}
