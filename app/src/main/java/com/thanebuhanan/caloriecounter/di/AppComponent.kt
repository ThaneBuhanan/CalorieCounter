package com.thanebuhanan.caloriecounter.di

import android.app.Application
import com.squareup.anvil.annotations.MergeComponent
import com.thanebuhanan.caloriecounter.ui.day.DayFragment
import com.thanebuhanan.caloriecounter.ui.food.FoodFragment
import com.thanebuhanan.caloriecounter.ui.home.HomeFragment
import com.thanebuhanan.caloriecounter.ui.setup.SetupFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@MergeComponent(AppScope::class)
interface AppComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: SetupFragment)
    fun inject(fragment: DayFragment)
    fun inject(fragment: FoodFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): AppComponent
    }
}