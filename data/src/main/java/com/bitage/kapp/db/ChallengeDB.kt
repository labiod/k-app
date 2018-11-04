package com.bitage.kapp.db

import com.kgb.kapp.db.TemplateDao
import com.kgb.kapp.db.UserDao

interface ChallengeDB {
    /**
     * Return challenge dao object
     */
    fun noteDao(): ChallengeDao
    /**
     * Return template dao object
     */
    fun templateDao(): TemplateDao
    /**
     * Return user dao object
     */
    fun userDao(): UserDao
}