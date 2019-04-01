package aki.pvnghe.unittestapi.baseview

import aki.pvnghe.unittestapi.di.module.view.Constants.ACTIVITY_FRAGMENT_MANAGER
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Named

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    protected abstract fun getLayout(): Int

    @Inject
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    lateinit var fragmentManager : FragmentManager

    @Inject
    lateinit var fragmentInjector : DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun addFragment(@IdRes layoutIdRes: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(layoutIdRes, fragment, fragment::class.java.simpleName)
            .commit()
    }
}
