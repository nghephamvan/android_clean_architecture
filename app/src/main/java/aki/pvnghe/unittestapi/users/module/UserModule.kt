package aki.pvnghe.unittestapi.users.module

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.mvp.di.scope.PerActivity
import aki.pvnghe.unittestapi.users.UsersPresenter
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @PerActivity
    @Provides
    internal fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}
