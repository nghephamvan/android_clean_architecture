package aki.pvnghe.unittestapi.di.module

import aki.pvnghe.unittestapi.di.scope.PerActivity
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.users.MainActivity
import aki.pvnghe.unittestapi.users.di.module.ActivityModule
import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuilder {
    @Binds
    @Singleton
    abstract fun application(app: App) : Application

    @PerActivity
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun mainActivityInjector() : MainActivity
}