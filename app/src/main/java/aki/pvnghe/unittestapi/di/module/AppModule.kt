package aki.pvnghe.unittestapi.di.module

import aki.pvnghe.data.scope.PerActivity
import aki.pvnghe.data.scope.PerFragment
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.users.activity.MainActivity
import aki.pvnghe.unittestapi.users.fragment.UsersFragment
import aki.pvnghe.unittestapi.users.module.MainActivityModule
import aki.pvnghe.unittestapi.users.module.UsersFragmentModule
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

    //Multi-scoping Dagger components
    //https://proandroiddev.com/multi-scoping-dagger-components-89b6f4bdb73b
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeActivityInjector(): MainActivity

    @PerFragment
    @ContributesAndroidInjector(modules = [UsersFragmentModule::class])
    internal abstract fun contributeFragmentInjector(): UsersFragment
}
