package aki.pvnghe.unittestapi

import aki.pvnghe.unittestapi.di.component.AppComponent
import aki.pvnghe.unittestapi.di.component.DaggerAppComponent
import aki.pvnghe.unittestapi.di.module.AppModule
import android.app.Application

class App : Application() {
    val applicationComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        initInjector()
    }

    private fun initInjector() {
        applicationComponent.inject(this)
    }
}