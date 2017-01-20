package walden.lib.cart.usb;

import walden.lib.cart.model.ShopBean;

/**
 * 服务器返回的 源数据 需要 则实现这个 interface
 * <p>
 * Created by next on 17-1-19.
 */
public interface IServiceBean
{

	/**
	 * 商品的id,如果没有可以使用UUID
	 *
	 * @return
	 */
	String giftId();

	/**
	 * 商品名称
	 *
	 * @return
	 */
	String giftName();

	/**
	 * 商品的价格
	 *
	 * @return
	 */
	double giftPrice();

	/**
	 * 商品的数量
	 *
	 * @return
	 */
	int giftCount();

	/**
	 * 配送费用
	 *
	 * @return
	 */
	double giftFee();

	/**
	 * 是否参与 合计 计算 , true 表示 参加 合计 计算
	 *
	 * @return
	 */
	boolean isJoin();

	/**
	 * 是否失效, true 失效 不参加运算
	 *
	 * @return
	 */
	boolean isFailure();

	/**
	 * 商品数量最小限制
	 *
	 * @return
	 */
	int minCount();

	/**
	 * 商品数量最大限制
	 *
	 * @return
	 */
	int maxCount();

	/**
	 * 同步数据
	 *
	 * @param b
	 */
	void syncData(ShopBean b);
}
