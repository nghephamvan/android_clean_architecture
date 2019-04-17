package aki.pvnghe.unittestapi.users.module

import aki.pvnghe.domain.usecase.GetUsersListUseCase
import aki.pvnghe.data.scope.PerFragment
import aki.pvnghe.domain.usecase.module.UseCaseModule
import aki.pvnghe.unittestapi.users.fragment.UsersPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [UseCaseModule::class])
class UsersFragmentModule {

    @Provides
    @PerFragment
    internal fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}
