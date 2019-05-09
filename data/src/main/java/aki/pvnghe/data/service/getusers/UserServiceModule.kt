package aki.pvnghe.data.service.getusers

import aki.pvnghe.data.retrofit.module.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class UserServiceModule {
    //@ActivityScope - you can add this scope or not.
    //If add, this module is only used on activity
    //If not add, this mean is the module will be used on both activity and fragment.
    //Never use @Singleton -> memory leak
    @Provides
    fun provideUserApi(@Named(RetrofitModule.RX_RETROFIT) retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    internal fun provideUserService(userApi: UserApi): UserService = UserServiceImpl(userApi)
}