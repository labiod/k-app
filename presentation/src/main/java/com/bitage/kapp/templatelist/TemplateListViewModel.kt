package com.bitage.kapp.templatelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitage.kapp.model.Template
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.repository.TemplateRepository
import com.bitage.kapp.template.GetTemplateListUseCase
import com.bitage.kapp.template.RemoveTemplateUseCase
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * View model class used in template list screen
 */
class TemplateListViewModel(
    private val getTemplateListUseCase: GetTemplateListUseCase,
    private val removeTemplateUseCase: RemoveTemplateUseCase
) : KViewModel() {
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
        removeTemplateUseCase.execute(RemoveTemplateUseCase.Params(item),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    action.run()
                }

                override fun onError(e: Throwable) {}
            }
        )
    }

    init {
        getTemplateListUseCase.execute(object : DisposableSubscriber<List<Template>>() {
            override fun onComplete() {}

            override fun onNext(t: List<Template>) {
                _templates.postValue(t)
            }

            override fun onError(t: Throwable) {
                error(t)
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        getTemplateListUseCase.dispose()
        removeTemplateUseCase.dispose()
    }
}