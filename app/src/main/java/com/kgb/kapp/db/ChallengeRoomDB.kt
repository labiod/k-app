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

/**
 * Room database class that contains table for all project entities
 * Instance of this class was create by builder check [ChallengeRoomDB.getInstance] method
 */
@Database(entities = [
        ChallengeEntity::class,
        TemplateEntity::class,
        TemplateChallengesEntity::class,
        UserProgressEntity::class
    ],
    version = 3, exportSchema = false)
@TypeConverters(DateConverter::class, ChallengeTypeConverter::class, StepProgressConverter::class)
abstract class ChallengeRoomDB : RoomDatabase() {
    /**
     * Return challenge dao object
     */
    abstract fun noteDao(): ChallengeDao
    /**
     * Return template dao object
     */
    abstract fun templateDao(): TemplateDao
    /**
     * Return user dao object
     */
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "AppDatabase.db"

        @Volatile
        private var instance: ChallengeRoomDB? = null

        private val lock = Any()

        /**
         * Create instance of [ChallengeRoomDB] and save it to instance static field and return it
         * Build instance of [ChallengeRoomDB] or return instance that was already created
         * @return instance of [ChallengeRoomDB] class.
         */
        @JvmStatic
        fun getInstance(context: Context): ChallengeRoomDB {
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