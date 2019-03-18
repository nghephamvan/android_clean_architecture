package aki.pvnghe.unittestapi.di.component

import aki.pvnghe.data.service.GithubUserService
import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.di.module.AppModule
import aki.pvnghe.unittestapi.di.module.NetworkModule
import aki.pvnghe.unittestapi.di.module.RepositoryModule
import aki.pvnghe.unittestapi.di.module.UseCaseModule
import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, UseCaseModule::class])
interface AppComponent {
    fun inject(app: App)
    fun getApplicationContext(): Context
    fun getUserService(): GithubUserService

    fun getUsersListUseCase(): GetUsersListUseCase
}
