package com.kgb.kapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.kgb.kapp.db.converter.ChallengeTypeConverter
import com.kgb.kapp.db.converter.DateConverter
import com.kgb.kapp.db.converter.StepProgressConverter

@Database(entities = [ChallengeEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, ChallengeTypeConverter::class, StepProgressConverter::class)
abstract class ChallengeRoomDB : RoomDatabase() {
    abstract fun noteDao() : ChallengeDao

    companion object {
        private const val DATABASE_NAME = "AppDatabase.db"
        @Volatile
        private var instance : ChallengeRoomDB? = null

        val lock = Any()

        @JvmStatic
        fun getInstance(context: Context) : ChallengeRoomDB {
            if (instance == null) {
                synchronized(lock) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            ChallengeRoomDB::class.java,
                            ChallengeRoomDB.DATABASE_NAME
                        ).build()
                    }
                }
            }
            return instance!!
        }
    }
}