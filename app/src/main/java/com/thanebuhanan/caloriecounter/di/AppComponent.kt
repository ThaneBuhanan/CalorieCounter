package com.thanebuhanan.caloriecounter.di

import android.app.Application
import com.squareup.anvil.annotations.MergeComponent
import com.thanebuhanan.caloriecounter.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@MergeComponent(AppScope::class)
interface AppComponent {
    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): AppComponent
    }
}