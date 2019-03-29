package aki.pvnghe.unittestapi.users.di.module

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.unittestapi.di.module.view.BaseFragmentModule
import aki.pvnghe.unittestapi.di.scope.PerFragment
import aki.pvnghe.unittestapi.users.UsersFragment
import aki.pvnghe.unittestapi.users.UsersPresenter
import aki.pvnghe.unittestapi.users.UsersView
import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module(includes = [BaseFragmentModule::class])
abstract class FragmentModule {

    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract fun fragment(usersFragment: UsersFragment) : Fragment

    @Binds
    @PerFragment
    abstract fun provideUsersView(usersFragment: UsersFragment) : UsersView

    @Binds
    @PerFragment
    fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}