package aki.pvnghe.unittestapi.users.module

import aki.pvnghe.data.scope.FragmentScope
import aki.pvnghe.domain.usecase.GetUsersListUseCase
import aki.pvnghe.domain.usecase.module.GetUsersListUseCaseModule
import aki.pvnghe.unittestapi.users.fragment.UsersPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [GetUsersListUseCaseModule::class])
class UsersFragmentModule {

    @Provides
    @FragmentScope
    internal fun provideUsersPresenter(getUsersListUseCase: GetUsersListUseCase) = UsersPresenter(getUsersListUseCase)
}
