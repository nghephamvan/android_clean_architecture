package aki.pvnghe.unittestapi.di.module

import aki.pvnghe.data.service.GithubUserService
import aki.pvnghe.unittestapi.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val userService : GithubUserService

    init {
        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.BUILD_TYPE == "debug") {
            okHttpBuilder.addInterceptor { chain ->
                println(chain.request())
                chain.proceed(chain.request())
            }
        }

        val okHttpClient = okHttpBuilder.build()

        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .build()

        userService = retrofit.create(GithubUserService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideEndpoint(): GithubUserService = userService
}