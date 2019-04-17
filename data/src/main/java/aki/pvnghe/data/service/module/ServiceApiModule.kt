package aki.pvnghe.data.service.module

import aki.pvnghe.data.retrofit.module.RetrofitModule
import aki.pvnghe.data.service.user.UserApi
import aki.pvnghe.data.service.user.UserService
import aki.pvnghe.data.service.user.UserServiceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceApiModule {
    @Provides
    fun provideUserApi(@Named(RetrofitModule.RX_RETROFIT) retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    internal fun provideUserService(userApi: UserApi): UserService = UserServiceImpl(userApi)
}