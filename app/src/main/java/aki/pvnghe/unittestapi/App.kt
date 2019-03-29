package aki.pvnghe.unittestapi

import aki.pvnghe.unittestapi.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector

class App : DaggerApplication(), HasActivityInjector {

    override fun applicationInjector(): AndroidInjector<out App> {
        val appComponent = DaggerAppComponent.builder().create(this)
        appComponent.inject(this)
        return appComponent
    }
}