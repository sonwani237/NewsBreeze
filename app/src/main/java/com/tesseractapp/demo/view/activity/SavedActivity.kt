package com.tesseractapp.demo.view.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tesseractapp.demo.R
import com.tesseractapp.demo.app.MyApplication
import com.tesseractapp.demo.base.BaseActivity
import com.tesseractapp.demo.databinding.ActivityMainBinding
import com.tesseractapp.demo.databinding.ActivitySavedBinding
import com.tesseractapp.demo.models.HeadlineModel
import com.tesseractapp.demo.view.adapter.NewsAdapter
import com.tesseractapp.demo.viewmodel.DashboardViewModel
import com.tesseractapp.demo.viewmodel.SaveViewModel
import com.tesseractapp.demo.viewmodel.ViewModelFactory
import javax.inject.Inject
import com.google.gson.reflect.TypeToken
import com.tesseractapp.demo.listeners.NewsListener
import com.tesseractapp.demo.view.adapter.SaveAdapter


class SavedActivity : BaseActivity<ActivitySavedBinding>(), NewsListener {

    @Inject
    lateinit var viewModelProviders: ViewModelFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var viewModel: SaveViewModel
    private var list: ArrayList<HeadlineModel.Articles> = ArrayList()

    override val layoutRes: Int
        get() = R.layout.activity_saved

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).getAppComponent().doInjection(this)
        viewModel = ViewModelProviders.of(this, viewModelProviders).get(SaveViewModel::class.java)
        dataBinding.viewModel = viewModel

        val data = intent.getStringExtra("List")
        list = Gson().fromJson(data, object : TypeToken<List<HeadlineModel.Articles>>() {}.type)
        setData(dataBinding.rvSaved, list)

        viewModel.onBackClick().observe(this, { onBackPressed()})

    }

    private fun setData(rvSaved: RecyclerView, list: java.util.ArrayList<HeadlineModel.Articles>?) {
        val mAdapter = SaveAdapter(this)
        rvSaved.adapter = mAdapter
        rvSaved.layoutManager = LinearLayoutManager(this)
        mAdapter.setList(list!!)
    }

    override fun onSave(articles: HeadlineModel.Articles) {
        startActivity(
            Intent(this, DetailActivity::class.java)
            .apply {
                putExtra("data", Gson().toJson(articles))
            })
    }

}