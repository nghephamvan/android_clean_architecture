package aki.pvnghe.unittestapi

import aki.pvnghe.data.repository.RealmDatabase
import aki.pvnghe.unittestapi.di.component.AppComponent
import aki.pvnghe.unittestapi.di.component.DaggerAppComponent
import aki.pvnghe.unittestapi.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector


class App : DaggerApplication(), HasActivityInjector {

    val applicationComponent by lazy {
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