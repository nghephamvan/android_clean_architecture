package aki.pvnghe.unittestapi.di.module.view

import aki.pvnghe.unittestapi.di.module.view.Constants.ACTIVITY_FRAGMENT_MANAGER
import aki.pvnghe.unittestapi.di.scope.PerActivity
import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class BaseActivityModule {

//    @Binds
//    @PerActivity
//    abstract fun context(activity: Activity) : Context

    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @PerActivity
    fun provideFragmentmanager(actvity: Activity) : FragmentManager = (actvity as AppCompatActivity).supportFragmentManager


}