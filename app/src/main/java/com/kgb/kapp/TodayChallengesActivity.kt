package com.kgb.kapp

import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kgb.kapp.challenge.Constants
import com.kgb.kapp.components.TemplatesDialogAdapter
import com.kgb.kapp.components.TodayChallengesAdapter
import com.kgb.kapp.databinding.DayChallengesBinding
import com.kgb.kapp.viewmodel.DayViewModelFactory
import com.kgb.kapp.viewmodel.DayChallengeViewModel
import kotlinx.android.synthetic.main.day_challenges.*
import java.text.SimpleDateFormat
import java.util.*

class TodayChallengesActivity : AppCompatActivity() {
    private lateinit var binding: DayChallengesBinding
    private lateinit var viewModelDay: DayChallengeViewModel
    private var adapter: TodayChallengesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.challenges_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId ?: 0
        return when(id) {
            R.id.action_load_template -> {
                loadTemplateData()
                true
            }
            R.id.action_create_template -> {
                createTemplate()
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

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.day_challenges)
        val day = intent.extras.getInt(Constants.CURRENT_DATE_DAY)
        val month = intent.extras.getInt(Constants.CURRENT_DATE_MONTH)
        val year = intent.extras.getInt(Constants.CURRENT_DATE_YEAR)
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("EEE MMM d, ''yy", Locale.getDefault())
        binding.todayDate = dateFormat.format(calendar.time)
        initRecyclerView()
        initViewModel(calendar.time)
        fab.setOnClickListener {
            val intent = Intent(it.context, EditChallengeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteAll() {
        viewModelDay.deleteAll()
    }

    private fun loadTemplateData() {
        val adapter = TemplatesDialogAdapter(viewModelDay.templates)
        viewModelDay.templates.observe(this, android.arch.lifecycle.Observer {
            adapter.notifyDataSetChanged()
        })
        val onChooseListener = DialogInterface.OnClickListener { dialog, which ->
            Log.d(Constants.GLOBAL_TAG, "on choose $which")
            adapter.getItem(which)?.let {
                viewModelDay.loadDataFromTemplate(it)
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Templates list")
            .setAdapter(adapter, onChooseListener)
            .create()
        dialog.show()
    }

    private fun createTemplate() {
        val intent = Intent(this, TemplateActivity::class.java)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        challenges_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        challenges_list.layoutManager = layoutManager
        challenges_list.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
    }

    private fun initViewModel(date: Date) {
        viewModelDay = ViewModelProviders.of(this, DayViewModelFactory(date)).get(DayChallengeViewModel::class.java)
        adapter = TodayChallengesAdapter(viewModelDay)
        challenges_list.adapter = adapter
        viewModelDay.challenges.observe(this, android.arch.lifecycle.Observer {
            adapter!!.notifyDataSetChanged()
        })
    }
}