package aki.pvnghe.unittestapi.di.module.view

import aki.pvnghe.unittestapi.di.module.view.Constants.CHILD_FRAGMENT_MANAGER
import aki.pvnghe.unittestapi.di.module.view.Constants.FRAGMENT
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class BaseFragmentModule {

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    fun childFragmentManager(@Named(FRAGMENT)fragment: Fragment) : FragmentManager = fragment.childFragmentManager
}