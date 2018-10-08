package com.kgb.kapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.kgb.kapp.db.converter.ChallengeTypeConverter
import com.kgb.kapp.db.converter.DateConverter
import com.kgb.kapp.db.converter.StepProgressConverter
import com.kgb.kapp.db.entity.ChallengeEntity
import com.kgb.kapp.db.entity.TemplateChallengesEntity
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.db.entity.UserProgressEntity

@Database(entities = [
        ChallengeEntity::class,
        TemplateEntity::class,
        TemplateChallengesEntity::class,
        UserProgressEntity::class
    ],
    version = 3, exportSchema = false)
@TypeConverters(DateConverter::class, ChallengeTypeConverter::class, StepProgressConverter::class)
abstract class ChallengeRoomDB : RoomDatabase() {
    abstract fun noteDao() : ChallengeDao
    abstract fun templateDao() : TemplateDao
    abstract fun userDao() : UserDao

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
                            )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return instance!!
        }
    }
}