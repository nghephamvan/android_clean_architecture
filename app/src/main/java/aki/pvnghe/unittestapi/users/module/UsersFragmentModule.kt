package aki.pvnghe.unittestapi.users.module

import aki.pvnghe.domain.usecase.GetUsersListUseCase
import aki.pvnghe.unittestapi.scope.PerFragment
import aki.pvnghe.unittestapi.users.fragment.UsersPresenter
import dagger.Module
import dagger.Provides

@Module
class UsersFragmentModule {

    @PerFragment
    @Provides
    internal fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}
