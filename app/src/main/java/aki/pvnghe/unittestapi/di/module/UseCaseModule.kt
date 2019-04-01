package aki.pvnghe.unittestapi.di.module

import aki.pvnghe.data.repository.UserRepository
import aki.pvnghe.domain.GetUsersListUseCase
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
    fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideGetUsersListUseCase(userRepository: UserRepository, @Named("ioScheduler") ioScheduler: Scheduler, @Named("mainThreadScheduler") mainThreadScheduler: Scheduler): GetUsersListUseCase =
            GetUsersListUseCase(userRepository, ioScheduler, mainThreadScheduler)
}
