package com.kgb.k_app.helper;

import android.content.Context;
import android.os.Build;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 3/7/17
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

public class ResHelper {

    public static int getColor(Context context, int colorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorRes);
        } else {
            return context.getResources().getColor(colorRes);
        }
    }
}
