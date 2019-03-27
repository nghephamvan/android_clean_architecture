package aki.pvnghe.unittestapi.users.di.module

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.unittestapi.users.UsersPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    internal fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}