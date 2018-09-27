package com.kgb.kapp.components.squats

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kgb.kapp.databinding.ActivitySquatsBinding

class SquatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivitySquatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
