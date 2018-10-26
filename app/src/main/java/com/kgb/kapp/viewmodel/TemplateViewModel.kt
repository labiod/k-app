package com.kgb.kapp.viewmodel

import android.arch.lifecycle.ViewModel
import com.kgb.kapp.OnExecuteListener
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.repository.TemplateRepository

/**
 * View model class used in [com.kgb.kapp.TemplateActivity]
 *
 */
class TemplateViewModel(private val repository: TemplateRepository) : ViewModel() {

    /**
     * Insert template to repository
     * @param template - template object
     * @param callback - insert template listener check [com.kgb.kapp.OnExecuteListener] for more details
     */
    fun createTemplate(template: TemplateEntity, callback: OnExecuteListener) {
        repository.insertTemplate(template, callback)
    }
}