package com.kgb.kapp

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.challenge.Constants
import com.kgb.kapp.components.ChallengesForTemplateAdapter
import com.kgb.kapp.databinding.ActivityTemplateBinding
import com.kgb.kapp.db.entity.TemplateEntity
import com.kgb.kapp.viewmodel.TemplateViewModel

/**
 * Activity used to create new template
 */
class TemplateActivity : AppCompatActivity(), OnExecuteListener {
    private lateinit var binding: ActivityTemplateBinding
    private lateinit var adapter: ChallengesForTemplateAdapter
    private lateinit var model: TemplateViewModel

    /**
     * Method call by android when create activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_template)
        adapter = ChallengesForTemplateAdapter()
        binding.challengesList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.challengesList.adapter = adapter
        binding.addNextChallenge.setOnClickListener {
            addNewChallenge()
        }
        binding.confirmChanges.setOnClickListener {
            createTemplate()
        }
        model = ViewModelProviders.of(this).get(TemplateViewModel::class.java)
    }

    override fun onSucessed() {
        runOnUiThread {
            Toast.makeText(this, "New item added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onFailer(ex: Throwable) {
        runOnUiThread {
            Log.e(Constants.GLOBAL_TAG, "error:", ex)
            Toast.makeText(this, "Problem with adding template to Database. Check logs form more details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addNewChallenge() {
        adapter.addNext()
        if (adapter.challenges.size == ChallengeType.values().size) {
            binding.addNextChallenge.isEnabled = false
        }
    }

    private fun createTemplate() {
        val template = TemplateEntity(null, binding.templateName.text.toString())
        template.challenges.addAll(adapter.challenges)
        model.createTemplate(template, this)
    }
}