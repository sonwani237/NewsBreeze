package com.tesseractapp.demo.app

import android.app.Application
import com.tesseractapp.demo.injection.*
import com.tesseractapp.demo.injection.AppContext

class MyApplication :Application() {

    private var appComponents: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponents = DaggerApplicationComponent.builder()
            .appContext(AppContext(this))
            .utilModule(UtilModule())
            .build()
    }

    fun getAppComponent(): ApplicationComponent {
        return appComponents!!
    }
}