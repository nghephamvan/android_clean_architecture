package aki.pvnghe.unittestapi

import aki.pvnghe.data.repository.RealmDatabase
import aki.pvnghe.unittestapi.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector


class App : DaggerApplication(), HasActivityInjector {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().create(this).inject(this)
        RealmDatabase.initializeRealm(this)
    }

    override fun applicationInjector(): AndroidInjector<App> {
        return DaggerAppComponent.builder().create(this)
    }

    /*@Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }*/
}