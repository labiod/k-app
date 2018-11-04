package com.kgb.kapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.Template
import com.kgb.kapp.components.ChallengesForTemplateAdapter
import com.kgb.kapp.databinding.ActivityTemplateBinding
import com.kgb.kapp.viewmodel.TemplateViewModel
import javax.inject.Inject

/**
 * Activity used to create new template
 */
class TemplateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTemplateBinding
    private lateinit var adapter: ChallengesForTemplateAdapter
    @Inject
    lateinit var model: TemplateViewModel

    /**
     * Method call by android when create activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        KApplication.instance.activityInjector.inject(this)
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
    }

    private fun addNewChallenge() {
        adapter.addNext()
        if (adapter.challenges.size == ChallengeType.values().size) {
            binding.addNextChallenge.isEnabled = false
        }
    }

    private fun createTemplate() {
        val template = Template(null, binding.templateName.text.toString())
        template.challenges.addAll(adapter.challenges)
        model.createTemplate(template).subscribe({
            runOnUiThread {
                Toast.makeText(this, "New item added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }) {
            runOnUiThread {
                Toast.makeText(this, "Problem with adding template to Database. Check logs form more details", Toast.LENGTH_SHORT).show()
            }
        }
    }
}