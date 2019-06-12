package aki.pvnghe.data.service.getusers

import aki.pvnghe.data.retrofit.module.RetrofitModule
import aki.pvnghe.data.service.SchedulerModule
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import retrofit2.Retrofit
import javax.inject.Named

@Module(includes = [SchedulerModule::class])
class SearchUsersServiceModule {
    //@ActivityScope - you can add this scope or not.
    //If add, this module is only used on activity
    //If not add, this mean is the module will be used on both activity and fragment.
    //Never use @Singleton -> memory leak
    @Provides
    fun provideUserApi(@Named(RetrofitModule.RX_RETROFIT) retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    internal fun provideSearchUsersService(
            userApi: UserApi,
            @Named(SchedulerModule.RX_IO_SCHEDULER) ioScheduler: Scheduler,
            @Named(SchedulerModule.RX_MAIN_THREAD_SCHEDULER) mainThreadScheduler: Scheduler
    ): SearchUsersService = SearchUsersService(userApi, ioScheduler, mainThreadScheduler)

}