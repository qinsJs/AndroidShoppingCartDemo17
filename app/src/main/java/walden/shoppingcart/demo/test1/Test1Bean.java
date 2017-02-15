package walden.shoppingcart.demo.test1;

import java.util.List;

import walden.lib.cart.model.ShopBean;
import walden.lib.cart.usb.IShopModel;

/**
 * Created by next on 17-1-19.
 */

public class Test1Bean
{

	/**
	 * Success : true
	 * Msg : 操作成功
	 * Data : [{"p_id":"84","p_name":"拿运费来拿运","p_brand":"嘿嘿嘿","p_xinhao":"鱼画江湖","c_id":"21","p_price":"0.02","p_mprice":"100.00","p_stock":"5","p_img1":"http://211.152.51.156:8045/Photo/20161123/4d5d1258-2dcb-4f07-a980-080aa85827e1.jpg","m_id":"1","m_store":"admin商店","p_number":"7","p_discount":"0.00","p_news":"九成新"}]
	 * Code : 200
	 */

	private boolean Success;
	private String Msg;
	private int Code;
	private List<DataBean> Data;

	public boolean isSuccess()
	{
		return Success;
	}

	public void setSuccess(boolean Success)
	{
		this.Success = Success;
	}

	public String getMsg()
	{
		return Msg;
	}

	public void setMsg(String Msg)
	{
		this.Msg = Msg;
	}

	public int getCode()
	{
		return Code;
	}

	public void setCode(int Code)
	{
		this.Code = Code;
	}

	public List<DataBean> getData()
	{
		return Data;
	}

	public void setData(List<DataBean> Data)
	{
		this.Data = Data;
	}

	public static class DataBean implements IShopModel
	{
		/**
		 * p_id : 84
		 * p_name : 拿运费来拿运
		 * p_brand : 嘿嘿嘿
		 * p_xinhao : 鱼画江湖
		 * c_id : 21
		 * p_price : 0.02
		 * p_mprice : 100.00
		 * p_stock : 5
		 * p_img1 : http://211.152.51.156:8045/Photo/20161123/4d5d1258-2dcb-4f07-a980-080aa85827e1.jpg
		 * m_id : 1
		 * m_store : admin商店
		 * p_number : 7
		 * p_discount : 0.00
		 * p_news : 九成新
		 */

		private String p_id;
		private String p_name;
		private String p_brand;
		private String p_xinhao;
		private String c_id;
		private String p_price;
		private String p_mprice;
		private String p_stock;
		private String p_img1;
		private String m_id;
		private String m_store;
		private String p_number;
		private String p_discount;
		private String p_news;

		public String getP_id()
		{
			return p_id;
		}

		public void setP_id(String p_id)
		{
			this.p_id = p_id;
		}

		public String getP_name()
		{
			return p_name;
		}

		public void setP_name(String p_name)
		{
			this.p_name = p_name;
		}

		public String getP_brand()
		{
			return p_brand;
		}

		public void setP_brand(String p_brand)
		{
			this.p_brand = p_brand;
		}

		public String getP_xinhao()
		{
			return p_xinhao;
		}

		public void setP_xinhao(String p_xinhao)
		{
			this.p_xinhao = p_xinhao;
		}

		public String getC_id()
		{
			return c_id;
		}

		public void setC_id(String c_id)
		{
			this.c_id = c_id;
		}

		public String getP_price()
		{
			return p_price;
		}

		public void setP_price(String p_price)
		{
			this.p_price = p_price;
		}

		public String getP_mprice()
		{
			return p_mprice;
		}

		public void setP_mprice(String p_mprice)
		{
			this.p_mprice = p_mprice;
		}

		public String getP_stock()
		{
			return p_stock;
		}

		public void setP_stock(String p_stock)
		{
			this.p_stock = p_stock;
		}

		public String getP_img1()
		{
			return p_img1;
		}

		public void setP_img1(String p_img1)
		{
			this.p_img1 = p_img1;
		}

		public String getM_id()
		{
			return m_id;
		}

		public void setM_id(String m_id)
		{
			this.m_id = m_id;
		}

		public String getM_store()
		{
			return m_store;
		}

		public void setM_store(String m_store)
		{
			this.m_store = m_store;
		}

		public String getP_number()
		{
			return p_number;
		}

		public void setP_number(String p_number)
		{
			this.p_number = p_number;
		}

		public String getP_discount()
		{
			return p_discount;
		}

		public void setP_discount(String p_discount)
		{
			this.p_discount = p_discount;
		}

		public String getP_news()
		{
			return p_news;
		}

		public void setP_news(String p_news)
		{
			this.p_news = p_news;
		}


		@Override
		public String giftId()
		{
			return p_id;
		}

		@Override
		public String giftName()
		{
			return p_name;
		}

		@Override
		public double giftPrice()
		{
			return Double.valueOf(p_mprice);
		}

		@Override
		public int giftCount()
		{
			return Integer.valueOf(p_number);
		}

		@Override
		public double giftFee()
		{
			return 0.0;
		}

		@Override
		public boolean isJoin()
		{
			return false;
		}

		@Override
		public boolean isFailure()
		{
			return false;
		}

		@Override
		public int minCount()
		{
			return 1;
		}

		@Override
		public int maxCount()
		{
			return 1000;
		}

		public void syncData(ShopBean b)
		{
			p_number = b.getCount() + "";
		}
	}
}
