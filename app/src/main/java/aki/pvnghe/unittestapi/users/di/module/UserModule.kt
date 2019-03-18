package aki.pvnghe.unittestapi.users.di.module

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.mvp.di.scope.PerActivity
import aki.pvnghe.unittestapi.users.UsersPresenter
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @PerActivity
    @Provides
    internal fun provideArticlesPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}
