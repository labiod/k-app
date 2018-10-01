package com.kgb.kapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kgb.kapp.components.ComponentViewModel
import com.kgb.kapp.databinding.ChallengeSettingsBinding

/**
 * This activity is a setting screen for choosen challenge
 * It's bind layout [R.layout.challenge_settings]
 */
class ChallengeSettingsActivity : AppCompatActivity() {
    companion object {
        const val COMPONENT_DATA = "component_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ChallengeSettingsBinding.inflate(layoutInflater)
        val model = ComponentViewModel()
        model.post(intent.getParcelableExtra(COMPONENT_DATA))
        binding.viewmodel = model
        setContentView(binding.root)
    }
}