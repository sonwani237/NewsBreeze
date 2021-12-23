package com.tesseractapp.demo.injection

import com.tesseractapp.demo.view.activity.Dashboard
import com.tesseractapp.demo.view.activity.DetailActivity
import com.tesseractapp.demo.view.activity.SavedActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppContext::class, UtilModule::class])
@Singleton
interface ApplicationComponent {
    fun doInjection(dashboard: Dashboard)
    fun doInjection(savedActivity: SavedActivity)
    fun doInjection(detailActivity: DetailActivity)
}