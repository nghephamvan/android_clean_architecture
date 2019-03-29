package aki.pvnghe.unittestapi.users.di.module

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.unittestapi.di.module.view.BaseActivityModule
import aki.pvnghe.unittestapi.di.scope.PerActivity
import aki.pvnghe.unittestapi.di.scope.PerFragment
import aki.pvnghe.unittestapi.users.MainActivity
import aki.pvnghe.unittestapi.users.UsersFragment
import aki.pvnghe.unittestapi.users.UsersPresenter
import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [BaseActivityModule::class])
abstract class ActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun usersfragmentInjector() : UsersFragment

    @Binds
    @PerActivity
    abstract fun activity(mainactivity: MainActivity) : Activity

    @Binds
    @PerFragment
    fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)

}
