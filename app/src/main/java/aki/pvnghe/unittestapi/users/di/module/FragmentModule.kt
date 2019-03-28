package aki.pvnghe.unittestapi.users.di.module

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.mvp.di.scope.PerFragment
import aki.pvnghe.unittestapi.users.UsersPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    @PerFragment
    internal fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}