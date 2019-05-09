package aki.pvnghe.unittestapi.di.component

import aki.pvnghe.data.repository.module.RepositoryModule
import aki.pvnghe.data.retrofit.module.RetrofitModule
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, RetrofitModule::class, RepositoryModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
