package walden.lib.cart.model;

import java.util.UUID;

/**
 * Created by next on 17-1-18.
 */

public class ShopMerchant
{
	/**
	 * Y
	 * 商户id , 自己生成
	 */
	private String id;
	/**
	 * N
	 * 商户id ,服务器预留
	 */
	private String merchantId;
	/**
	 * Y
	 * 名称
	 */
	private String name;

	//-------

	/**
	 * N
	 * 简介
	 */
	private String introduction;
	/**
	 * N
	 * 地址
	 */
	private String address;

	public ShopMerchant(String name)
	{
		id= UUID.randomUUID().toString();
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getMerchantId()
	{
		return merchantId;
	}

	public void setMerchantId(String merchantId)
	{
		this.merchantId = merchantId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
}
