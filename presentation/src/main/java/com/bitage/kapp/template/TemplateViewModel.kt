package com.bitage.kapp.template

import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * View model class for template model
 *
 */
class TemplateViewModel(private val repository: TemplateRepository) : KViewModel() {

    /**
     * Insert template to repository
     * @param template - template object
     */
    fun createTemplate(template: Template, onComplete: Action, onErrorAction: Consumer<Throwable>) {
        addDisposable(repository.insertTemplate(template).subscribe(onComplete, onErrorAction))
    }
}