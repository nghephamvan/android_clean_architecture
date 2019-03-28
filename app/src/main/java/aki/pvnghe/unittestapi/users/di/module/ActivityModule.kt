package aki.pvnghe.unittestapi.users.di.module

import aki.pvnghe.mvp.di.scope.PerActivity
import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun provideActivity() = activity
}
