package aki.pvnghe.unittestapi.di.module

import aki.pvnghe.mvp.di.scope.PerActivity
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.users.MainActivity
import aki.pvnghe.unittestapi.users.module.UserModule
import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun application(app: App): Application

    @PerActivity
    @ContributesAndroidInjector(modules = [UserModule::class])
    internal abstract fun contributeActivityInjector(): MainActivity
}
