package walden.lib.cart.usb;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by walden on 2017/2/15.
 */

public interface IMerchantsModel {

    String gifMerchantsId();

    String gifMerchantsName();

    boolean selected();

    boolean exit();

    <T extends IShopModel> List<T> shops();
}
