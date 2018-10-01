package com.kgb.kapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.kgb.kapp.components.ChallengeType
import com.kgb.kapp.components.ComponentDAO
import com.kgb.kapp.components.ComponentsAdapter
import com.kgb.kapp.components.StepProgress
import com.kgb.kapp.databinding.ChallengesMainBinding
import kotlinx.android.synthetic.main.challenges_main.*

class ChallengesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindig = ChallengesMainBinding.inflate(layoutInflater)
        setContentView(bindig.root)
        val list = ArrayList<ComponentDAO>()
        list.add(ComponentDAO(ChallengeType.PUSHUP, 1, StepProgress.BEGINNER))
        list.add(ComponentDAO(ChallengeType.PULLUP, 1, StepProgress.BEGINNER))
        list.add(ComponentDAO(ChallengeType.SQUAT, 1, StepProgress.BEGINNER))
        list.add(ComponentDAO(ChallengeType.BRIDGE, 1, StepProgress.BEGINNER))
        list.add(ComponentDAO(ChallengeType.LEG_RAISE, 1, StepProgress.BEGINNER))
        list.add(ComponentDAO(ChallengeType.HANDSTAND_PUSHUP, 1, StepProgress.BEGINNER))
        challenges_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        challenges_list.adapter = ComponentsAdapter(list)
    }
}