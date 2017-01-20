package walden.lib.cart.model;

import java.util.UUID;

/**
 * Created by next on 17-1-18.
 */

public class ShopBean
{
	/**
	 * Y
	 * 自己使用的 id,自己生成
	 */
	private String id;
	/**
	 * N
	 * 这个是给服务器预留的Id位置
	 */
	private String shopId;
	/**
	 * Y
	 * 商品名称
	 */
	private String name;
	/**
	 * Y
	 * 商品价格
	 */
	private double price;
	/**
	 * Y
	 * 商品数量
	 */
	private int count;

	/**
	 * 是否参与 合计 计算
	 */
	private boolean isJoin;
	//-----
	/**
	 * 商品图片
	 */
	private String pic;

	/**
	 * N
	 * 商品简介
	 */
	private String introduction;
	/**
	 * N
	 * 商品状态,下架等
	 */
	private ShopStatus status;
	/**
	 * N
	 * 商品供应商信息
	 */
	private ShopMerchant merchant;
	/**
	 * N
	 * 配送费用
	 */
	private double distribution;

	/**
	 * 商品数量最小限制
	 */
	private int min_count = 1;

	/**
	 * 商品数量最大限制
	 */
	private int max_count = Integer.MAX_VALUE;

	public ShopBean(String name, double price)
	{
		this(name, price, 1);
	}

	public ShopBean(String name, double price, int count)
	{
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.price = price;
		this.count = count;
	}


	public synchronized void setCount(int count)
	{
		this.count = count;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getShopId()
	{
		return shopId;
	}

	public void setShopId(String shopId)
	{
		this.shopId = shopId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getCount()
	{
		return count;
	}

	public boolean isJoin()
	{
		return isJoin;
	}

	public void setJoin(boolean join)
	{
		isJoin = join;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public ShopStatus getStatus()
	{
		return status;
	}

	public void setStatus(ShopStatus status)
	{
		this.status = status;
	}

	public ShopMerchant getMerchant()
	{
		return merchant;
	}

	public void setMerchant(ShopMerchant merchant)
	{
		this.merchant = merchant;
	}

	public double getDistribution()
	{
		return distribution;
	}

	public void setDistribution(double distribution)
	{
		this.distribution = distribution;
	}

	public int getMin_count()
	{
		return min_count;
	}

	public void setMin_count(int min_count)
	{
		this.min_count = min_count;
	}

	public int getMax_count()
	{
		return max_count;
	}

	public void setMax_count(int max_count)
	{
		this.max_count = max_count;
	}
}
