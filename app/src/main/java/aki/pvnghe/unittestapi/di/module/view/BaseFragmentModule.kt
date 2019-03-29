package aki.pvnghe.unittestapi.di.module.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class BaseFragmentModule {
    companion object {
        const val FRAGMENT = "BaseFragmentModule.Fragment"
        const val CHILD_FRAGMENT = "BaseFragmentModule.ChildFragment"
        const val CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.ChildFragmentManager"
    }

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    fun childFragmentManager(@Named(FRAGMENT)fragment: Fragment) : FragmentManager = fragment.childFragmentManager
}