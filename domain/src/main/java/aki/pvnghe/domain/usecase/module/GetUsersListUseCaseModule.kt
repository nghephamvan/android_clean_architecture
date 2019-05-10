package aki.pvnghe.domain.usecase.module

import aki.pvnghe.data.scope.FragmentScope
import aki.pvnghe.data.service.getusers.UserService
import aki.pvnghe.data.service.getusers.UserServiceModule
import aki.pvnghe.domain.usecase.GetUsersListUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module(includes = [UserServiceModule::class])
class GetUsersListUseCaseModule {
    @Provides
    @FragmentScope
    internal fun provideGetUsersListUseCase(
            userService: UserService, @Named(UseCaseModule.RX_IO_SCHEDULER) ioScheduler: Scheduler, @Named(
                    UseCaseModule.RX_MAIN_THREAD_SCHEDULER
            ) mainThreadScheduler: Scheduler
    ): GetUsersListUseCase = GetUsersListUseCase(userService, ioScheduler, mainThreadScheduler)
}