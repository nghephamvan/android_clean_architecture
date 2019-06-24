package aki.pvnghe.domain.users.module

import aki.pvnghe.data.scope.ActivityScope
import aki.pvnghe.domain.users.activity.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @ActivityScope
    @Provides
    internal fun provideMainPresenter() = MainPresenter()
}
