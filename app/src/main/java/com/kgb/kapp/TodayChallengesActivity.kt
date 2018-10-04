package com.kgb.kapp

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.kgb.kapp.components.TodayChallengesAdapter
import com.kgb.kapp.databinding.ChallengesMainBinding
import com.kgb.kapp.db.ChallengeEntity
import com.kgb.kapp.viewmodel.TodayChallengeViewModel
import kotlinx.android.synthetic.main.challenges_main.*

class TodayChallengesActivity : AppCompatActivity() {
    private lateinit var binding: ChallengesMainBinding
    private lateinit var viewModelToday: TodayChallengeViewModel
    private var adapter: TodayChallengesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.challenges_main)
        initRecyclerView()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.challenges_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId ?: 0
        return when(id) {
            R.id.action_load_sample -> {
                loadSampleData()
                true
            }

            R.id.action_delete_all -> {
                deleteAll()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun deleteAll() {
        viewModelToday.deleteAll()
    }

    private fun loadSampleData() {
        viewModelToday.addSampleData()
    }

    private fun initRecyclerView() {
        challenges_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        challenges_list.layoutManager = layoutManager
        challenges_list.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
    }

    private fun initViewModel() {
        viewModelToday = ViewModelProviders.of(this).get(TodayChallengeViewModel::class.java)
        adapter = TodayChallengesAdapter(viewModelToday)
        challenges_list.adapter = adapter
        viewModelToday.challenges.observe(this, android.arch.lifecycle.Observer { challenges ->
            adapter!!.notifyDataSetChanged()
        })
    }
}