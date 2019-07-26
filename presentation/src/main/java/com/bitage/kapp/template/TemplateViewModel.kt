package com.bitage.kapp.template

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bitage.kapp.challenge.GetChallengeByIdUseCase
import com.bitage.kapp.presentation.KViewModel
import com.bitage.kapp.model.Template
import com.bitage.kapp.repository.TemplateRepository
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.subscribers.DisposableSubscriber

/**
 * View model class for template model
 */
class TemplateViewModel(
    private val getTemplateByIdUseCase: GetTemplateByIdUseCase,
    private val setTemplateUseCase: SetTemplateUseCase
) : KViewModel() {

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
        setTemplateUseCase.execute(SetTemplateUseCase.Params(template),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onComplete.run()
                }

                override fun onError(e: Throwable) {
                    onErrorAction.accept(e)
                }
            }
        )
    }

    /**
     * Load template for given id and put result to _template field
     * If user want to get this data it must observe challenge getter
     *
     * @param templateId - template id
     */
    fun loadTemplate(templateId: Long) {
        getTemplateByIdUseCase.execute(GetTemplateByIdUseCase.Params(templateId),
            object : DisposableSubscriber<Template>() {
                override fun onComplete() {
                }

                override fun onNext(t: Template?) {
                    _template.postValue(t)
                }

                override fun onError(t: Throwable?) {
                }

            })
    }
}