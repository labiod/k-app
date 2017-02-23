package com.kgb.k_app.database;

import android.provider.BaseColumns;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 2/21/17
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

public final class ChallengeContract {
    private ChallengeContract() {}

    public static class ChallengeEntry implements BaseColumns {
        public static final String TABLE_NAME = "challenge";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_RATE = "rate";
    }

    public static class ChallengeTypeEntry implements BaseColumns {
        public static final String TABLE_NAME = "challenge_type";
        public static final String COLUMN_NAME = "name";
    }
}
