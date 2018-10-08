package com.kgb.kapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.kgb.kapp.OnExecuteListener
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.repository.TemplateRepository

class TemplateViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TemplateRepository.getInstance(application)

    fun createTemplate(template: TemplateEntity, callback: OnExecuteListener) {
        repository.addTemplate(template, callback)
    }
}