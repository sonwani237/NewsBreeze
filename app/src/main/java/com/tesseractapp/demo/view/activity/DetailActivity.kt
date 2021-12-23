package com.tesseractapp.demo.view.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tesseractapp.demo.R
import com.tesseractapp.demo.app.MyApplication
import com.tesseractapp.demo.base.BaseActivity
import com.tesseractapp.demo.databinding.ActivityDetailBinding
import com.tesseractapp.demo.databinding.ActivitySavedBinding
import com.tesseractapp.demo.models.HeadlineModel
import com.tesseractapp.demo.viewmodel.DetailViewModel
import com.tesseractapp.demo.viewmodel.SaveViewModel
import com.tesseractapp.demo.viewmodel.ViewModelFactory
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    @Inject
    lateinit var viewModelProviders: ViewModelFactory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var viewModel: DetailViewModel

    override val layoutRes: Int
        get() = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).getAppComponent().doInjection(this)
        viewModel = ViewModelProviders.of(this, viewModelProviders).get(DetailViewModel::class.java)
        dataBinding.viewModel = viewModel

        val data = intent.getStringExtra("data")
        val mData = Gson().fromJson(data, HeadlineModel.Articles::class.java)
        viewModel.setData(mData)

        viewModel.onBackClick().observe(this, { onBackPressed()})

    }

}