package aki.pvnghe.unittestapi.di.component

import aki.pvnghe.data.service.GithubUserService
import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilder::class, AppModule::class, NetworkModule::class, RepositoryModule::class, UseCaseModule::class])
interface AppComponent : AndroidInjector<App> {

    fun getUserService(): GithubUserService
    fun getUsersListUseCase(): GetUsersListUseCase

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
