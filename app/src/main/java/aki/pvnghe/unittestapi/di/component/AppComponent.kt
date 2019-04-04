package aki.pvnghe.unittestapi.di.component

import aki.pvnghe.data.retrofit.module.RetrofitModule
import aki.pvnghe.data.service.module.ServiceApiModule
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.di.module.AppModule
import aki.pvnghe.data.repository.module.RepositoryModule
import aki.pvnghe.domain.usecase.module.UseCaseModule
import aki.pvnghe.unittestapi.users.module.MainActivityModule
import aki.pvnghe.unittestapi.users.module.UsersFragmentModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, RetrofitModule::class,
    ServiceApiModule::class, RepositoryModule::class, UseCaseModule::class, MainActivityModule::class, UsersFragmentModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
