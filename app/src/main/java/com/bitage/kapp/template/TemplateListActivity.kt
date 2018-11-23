package com.bitage.kapp.template

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bitage.kapp.R

/**
 * Activity with template list
 */
class TemplateListActivity : AppCompatActivity() {

    /**
     * Method was call when system create activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_list)
    }
}
