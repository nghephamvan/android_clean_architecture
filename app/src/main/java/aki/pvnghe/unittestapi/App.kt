package aki.pvnghe.unittestapi

import aki.pvnghe.data.repository.RealmDatabase
import aki.pvnghe.unittestapi.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    private val applicationComponent: AndroidInjector<App> by lazy {
        DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)
        RealmDatabase.initializeRealm(this)
    }

    override fun applicationInjector(): AndroidInjector<App> {
        return applicationComponent
    }
}