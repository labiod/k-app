package com.bitage.kapp.db

/**
 * Interface for database fo challenges
 */
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