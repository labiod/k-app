package com.kgb.kapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kgb.kapp.databinding.ChallengeSettingsBinding

class ChallengeSettingsActivity : AppCompatActivity() {
    companion object {
        const val COMPONENT_DATA = "component_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ChallengeSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}