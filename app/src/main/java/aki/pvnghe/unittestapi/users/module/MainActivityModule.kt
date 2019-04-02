package aki.pvnghe.unittestapi.users.module

import aki.pvnghe.unittestapi.scope.PerActivity
import aki.pvnghe.unittestapi.users.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @PerActivity
    @Provides
    internal fun provideMainPresenter() = MainPresenter()
}
