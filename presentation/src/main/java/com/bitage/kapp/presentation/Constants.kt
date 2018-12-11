package com.bitage.kapp.presentation

/**
 * Interface with constant fields use in hole application
 */
interface Constants {
    companion object {
        /**
         * global tag for android log
         */
        const val GLOBAL_TAG = "[KGB]"
        /**
         * max challenge level
         */
        const val MAX_LEVEL = 10
        /**
         * key used to transfer challenge id between activity
         */
        const val CHALLENGE_ITEM_ID_KEY = "challenge_item_id_key"
        /**
         * tag used to transfer day value between activity
         */
        const val CURRENT_DATE_DAY = "current_date_day"
        /**
         * tag used to transfer month value between activity
         */
        const val CURRENT_DATE_MONTH = "current_date_month"
        /**
         * tag used to transfer year value between activity
         */
        const val CURRENT_DATE_YEAR = "current_date_year"

        /**
         * Application default format for date
         */
        const val APP_DATE_FORMAT = "EEE MMM d, ''yy"
    }
}