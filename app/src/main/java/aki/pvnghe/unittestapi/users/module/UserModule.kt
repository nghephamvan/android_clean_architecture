package aki.pvnghe.unittestapi.users.module

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.unittestapi.scope.PerFragment
import aki.pvnghe.unittestapi.users.UsersPresenter
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @PerFragment
    @Provides
    internal fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}
