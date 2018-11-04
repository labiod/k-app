package com.kgb.kapp.viewmodel

import android.arch.lifecycle.ViewModel
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.Completable

/**
 * View model class used in [com.kgb.kapp.TemplateActivity]
 *
 */
class TemplateViewModel(private val repository: TemplateRepository) : KViewModel() {

    /**
     * Insert template to repository
     * @param template - template object
     */
    fun createTemplate(template: Template): Completable {
        return repository.insertTemplate(template)
    }
}