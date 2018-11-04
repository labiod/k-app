package com.bitage.kapp.repository

import com.bitage.kapp.model.Template
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.Date

interface TemplateRepository {
    /**
     * Insert template to database
     * @param template - template to be insert
     */
    fun insertTemplate(template: Template): Completable

    /**
     * Get list of template
     * @return list of template
     */
    fun getTemplates(): Flowable<List<Template>>

    /**
     * Load template and create challenges for given template and date
     * @param template - template for challenges
     * @param date - date for challenges
     */
    fun loadDataFromTemplate(template: Template, date: Date)
}