package aki.pvnghe.unittestapi.users.module

import aki.pvnghe.data.scope.ActivityScope
import aki.pvnghe.unittestapi.users.activity.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @ActivityScope
    @Provides
    internal fun provideMainPresenter() = MainPresenter()
}
