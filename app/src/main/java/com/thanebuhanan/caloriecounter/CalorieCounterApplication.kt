package com.thanebuhanan.caloriecounter

import android.app.Application
import com.thanebuhanan.caloriecounter.di.AppComponent
import com.thanebuhanan.caloriecounter.di.DaggerAppComponent

class CalorieCounterApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}