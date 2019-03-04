package com.chanven.lib.cptr.header;

import android.content.Context;

import com.chanven.lib.cptr.header.progressheader.DefaultHeaderView;
import com.chanven.lib.cptr.header.smileheader.SmileHeaderViewCopy;
import com.chanven.lib.cptr.header.storehouseheader.StoreHouseHeader;

/**
 * Created to :
 *
 * @author WANG
 * @date 2019/3/4
 */
public class PtrHeaderFactory {

    public static final int HEADER_TYPE_PROGRESSBAR = 101;
    public static final int HEADER_TYPE_SMILE = 102;
    public static final int HEADER_TYPE_STORE_HOUSER = 103;

    private PtrHeaderFactory(){

    }

    public static BaseHeaderView create(Context context ,int type) {
        switch (type) {
            case HEADER_TYPE_PROGRESSBAR:
                return getProgressBarHeaderView(context);
            case HEADER_TYPE_SMILE:
                return getSmileHeaderView(context);
            case HEADER_TYPE_STORE_HOUSER:
                return getStoreHouseHeaderView(context);
            default:
                return getSmileHeaderView(context);
        }
    }

    private static BaseHeaderView getSmileHeaderView(Context context) {
        return new SmileHeaderViewCopy(context);
    }

    private static BaseHeaderView getProgressBarHeaderView(Context context) {
        return new DefaultHeaderView(context);
    }

    private static BaseHeaderView getStoreHouseHeaderView(Context context) {
        return new StoreHouseHeader(context);
    }
}
