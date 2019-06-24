package aki.pvnghe.domain.users.module

import aki.pvnghe.data.scope.FragmentScope
import aki.pvnghe.data.service.getusers.SearchUsersService
import aki.pvnghe.data.service.getusers.SearchUsersServiceModule
import aki.pvnghe.domain.users.fragment.UsersPresenter
import dagger.Module
import dagger.Provides

@Module(includes = [SearchUsersServiceModule::class])
class UsersFragmentModule {

    @Provides
    @FragmentScope
    internal fun provideUsersPresenter(searchUsersService: SearchUsersService) = UsersPresenter(searchUsersService)
}
