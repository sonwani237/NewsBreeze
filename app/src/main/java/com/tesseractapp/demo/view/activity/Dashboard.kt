package com.tesseractapp.demo.view.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tesseractapp.demo.R
import com.tesseractapp.demo.base.BaseActivity
import com.tesseractapp.demo.app.MyApplication
import com.tesseractapp.demo.databinding.ActivityMainBinding
import com.tesseractapp.demo.listeners.NewsListener
import com.tesseractapp.demo.models.HeadlineModel
import com.tesseractapp.demo.network.ApiResponse
import com.tesseractapp.demo.network.Status
import com.tesseractapp.demo.utils.UtilMethods
import com.tesseractapp.demo.view.adapter.NewsAdapter
import com.tesseractapp.demo.viewmodel.DashboardViewModel
import com.tesseractapp.demo.viewmodel.ViewModelFactory
import javax.inject.Inject

class Dashboard : BaseActivity<ActivityMainBinding>(), NewsListener {

    @Inject
    lateinit var viewModelProviders: ViewModelFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var viewModel: DashboardViewModel
    lateinit var mAdapter: NewsAdapter
    private var savedList: ArrayList<HeadlineModel.Articles> = ArrayList()


    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).getAppComponent().doInjection(this)
        viewModel = ViewModelProviders.of(this, viewModelProviders).get(DashboardViewModel::class.java)
        dataBinding.viewModel = viewModel

        viewModel.apiResponse.observe(this, Observer { data -> onApiRes(data) })
        if (UtilMethods.checkInternetAvailability(this)) {
            viewModel.getHeadline("us")
        } else {
            UtilMethods.showSnackBar(this, getString(R.string.network_error), dataBinding.parent)
            val data = sharedPreferences.getString("HomeData", "")
            if (data!=null && data.isNotEmpty()) {
                savedList = Gson().fromJson(data, object : TypeToken<List<HeadlineModel.Articles>>() {}.type)
                if (savedList.isNotEmpty()) {
                    setData(savedList, dataBinding.rvFeeds)
                }
            }
        }

        viewModel.onSaveClick().observe(this, {
            val list : ArrayList<HeadlineModel.Articles> = ArrayList()
            mAdapter.mDataList?.forEach {
                if (it.saved) {
                    list.add(it)
                }
            }
            if (list.isNotEmpty()) {
                startActivity(Intent(this, SavedActivity::class.java)
                    .apply {
                        putExtra("List", Gson().toJson(list))
                    })
            } else {
                Toast.makeText(this, "No item in saved", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun onApiRes(apiResponse: ApiResponse) {
        //when you get response from api you can access data like {apiResponse.data}

        UtilMethods.printLog("LoginResp", "onLoginRes: " + apiResponse.data + "onError: " + apiResponse.error)
        when (apiResponse.status) {

            Status.LOADING -> Toast.makeText(this, "Data Loading....", Toast.LENGTH_LONG).show()

            Status.SUCCESS -> {
                val data = Gson().fromJson(apiResponse.data, HeadlineModel::class.java)
                UtilMethods.save(sharedPreferences, "HomeData", Gson().toJson(data.articles))
                setData(data.articles, dataBinding.rvFeeds)
            }

            Status.ERROR -> Toast.makeText(this, "Something went wrong..", Toast.LENGTH_LONG).show()

        }
    }

    private fun setData(model: ArrayList<HeadlineModel.Articles>, recyclerView: RecyclerView) {
        mAdapter = NewsAdapter(this)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.setList(model)
    }

    override fun onSave(articles: HeadlineModel.Articles) {
        startActivity(Intent(this, DetailActivity::class.java)
            .apply {
                putExtra("data", Gson().toJson(articles))
            })
    }

}
