package com.kgb.k_app.database;

import android.provider.BaseColumns;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/21/17
 * @copyright Copyright (c)2017 KGBetlej
 */

public final class ChallengeContract {
    private ChallengeContract() {}

    public static class ChallengeEntry implements BaseColumns {
        public static final String TABLE_NAME = "challenge";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_RATE = "rate";
    }

    public static class YourChallengeEntry implements BaseColumns {
        public static final String TABLE_NAME = "your_challenge";
        public static final String COLUMN_CHALLENGE_ID = "challenge_id";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_EXPIRE_DATE = "expire_date";
        public static final String COLUMN_PROGRESS = "progress";
    }

    public static class ChallengeTypeEntry implements BaseColumns {
        public static final String TABLE_NAME = "challenge_type";
        public static final String COLUMN_NAME = "name";
    }
}
