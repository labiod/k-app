package com.bitage.kapp.template

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * View model class for template model
 */
class TemplateViewModel(private val repository: TemplateRepository) : KViewModel() {

    private val _template= MutableLiveData<Template>()
    /**
     * Getter for _template field
     */
    val template: LiveData<Template>
        get() = _template

    /**
     * Insert template to repository
     * @param template - template object
     */
    fun createOrUpdateTemplate(template: Template, onComplete: Action, onErrorAction: Consumer<Throwable>) {
        addDisposable(repository.insertTemplate(template).subscribe(onComplete, onErrorAction))
    }

    /**
     * Load template for given id and put result to _template field
     * If user want to get this data it must observe challenge getter
     *
     * @param templateId - template id
     */
    fun loadTemplate(templateId: Long) {
        val note = repository.getTemplateById(templateId)
        addDisposable(note.subscribe{ next ->
            _template.postValue(next)
        })
    }
}