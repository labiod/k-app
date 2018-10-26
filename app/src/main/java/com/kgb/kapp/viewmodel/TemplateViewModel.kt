package com.kgb.kapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.kgb.kapp.OnExecuteListener
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.repository.TemplateRepository

/**
 * View model class used in [com.kgb.kapp.TemplateActivity]
 *
 */
class TemplateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TemplateRepository.getInstance(application)

    /**
     * Insert template to repository
     * @param template - template object
     * @param callback - insert template listener check [com.kgb.kapp.OnExecuteListener] for more details
     */
    fun createTemplate(template: TemplateEntity, callback: OnExecuteListener) {
        repository.insertTemplate(template, callback)
    }
}