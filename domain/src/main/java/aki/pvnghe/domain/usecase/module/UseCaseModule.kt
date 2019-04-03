package aki.pvnghe.domain.usecase.module

import aki.pvnghe.data.service.user.UserService
import aki.pvnghe.domain.usecase.GetUsersListUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    @Named("ioScheduler")
    internal fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    internal fun provideGetUsersListUseCase(userService: UserService, @Named("ioScheduler") ioScheduler: Scheduler, @Named("mainThreadScheduler") mainThreadScheduler: Scheduler): GetUsersListUseCase =
            GetUsersListUseCase(userService, ioScheduler, mainThreadScheduler)
}