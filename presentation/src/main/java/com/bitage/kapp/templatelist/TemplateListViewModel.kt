package com.bitage.kapp.templatelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bitage.kapp.model.Template
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

/**
 * View model class used in template list screen
 */
class TemplateListViewModel(private val repository: TemplateRepository) : KViewModel() {
    private val _templates = MutableLiveData<List<Template>>()
    /**
     * Getter for _templates field
     */
    val templates: LiveData<List<Template>>
        get() = _templates

    /**
     * Delete template from repository
     * @param item - template to remove
     * @param action - call when finish action
     */
    fun deleteTemplate(item: Template, action: Action) {
        addDisposable(repository.deleteTemplate(item)
            .subscribeOn(Schedulers.io())
            .subscribe(action))
    }

    init {
        addDisposable(repository.getTemplates().subscribe({
            _templates.postValue(it)
        }) {
            error(it)
        })
    }
}