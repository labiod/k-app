package com.kgb.kapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.bitage.kapp.converter.DateConverter
import com.bitage.kapp.converter.StepProgressConverter
import com.bitage.kapp.db.ChallengeDB
import com.bitage.kapp.entity.ChallengeEntity
import com.bitage.kapp.entity.TemplateEntity
import com.bitage.kapp.entity.UserProgressEntity
import com.kgb.kapp.db.converter.ChallengeTypeConverter
import com.kgb.kapp.db.entity.TemplateChallengesEntity

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
abstract class ChallengeRoomDB : RoomDatabase(), ChallengeDB {
    companion object {
        const val DATABASE_NAME = "AppDatabase.db"
    }
}