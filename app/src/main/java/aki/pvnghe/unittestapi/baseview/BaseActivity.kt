package aki.pvnghe.unittestapi.baseview

import aki.pvnghe.domain.BasePresenter
import aki.pvnghe.unittestapi.R
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.transition.Fade
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<P : BasePresenter<Any>> : DaggerAppCompatActivity() {

    @Inject lateinit var presenter: P

    protected abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initPresenter()

        setPendingTransitionLeftRight(true)
    }

    open fun setPendingTransitionLeftRight(pendingTransition: Boolean) {
        if (pendingTransition) {
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        } else {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    override fun onResume() {
        super.onResume()
        setPendingTransitionUpDown(Up =  true)
    }

    override fun onPause() {
        super.onPause()
        setPendingTransitionUpDown(Up = false)
    }
    open fun setPendingTransitionUpDown(Up: Boolean) {
        if (Up) {
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.not_doing)
        } else {
            overridePendingTransition(R.anim.not_doing, R.anim.slide_out_bottom)
        }
    }

    private fun setAnimation(view: View, mContext: Context) {
        view.clearAnimation()
        if (view.animation != null) return
        view.setHasTransientState(true)
        val animShake = AnimationUtils.loadAnimation(mContext, R.anim.shake_repeat)
        view.animation = animShake
    }

    fun viewAnimation(mContext: Context, btnDown: Button) {
        val animateFadeIn = AnimationUtils.loadAnimation(mContext, R.anim.fadein)
        btnDown.visibility = View.VISIBLE
        animateFadeIn.reset()
        btnDown.clearAnimation()
        btnDown.startAnimation(animateFadeIn)
    }

    private fun initPresenter() {
        presenter.attachView(this)
        presenter.initialise()
        initialiseView()
    }

    override fun onDestroy() {
        presenter.disposeSubscriptions()
        presenter.detachView()
        super.onDestroy()
    }

    abstract fun initialiseView()


    protected fun addFragmentWithSlideAnimationLeftRight(@IdRes containerViewId: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right)
                .replace(containerViewId, fragment, fragment.javaClass.simpleName)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    @SuppressLint("ResourceType")
    protected fun addFragmentWithSlideAnimationUpDown(@IdRes containerViewId: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.not_doing, R.anim.not_doing, R.anim.slide_out_bottom)
                .replace(containerViewId, fragment, fragment.javaClass.simpleName)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    protected fun addFragmentWithOutReplaceLeftRight(@IdRes containerViewId: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right)
                .add(containerViewId, fragment, fragment.javaClass.simpleName)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    @SuppressLint("ResourceType")
    protected fun addFragmentWithOutReplaceUpDown(@IdRes containerViewId: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.not_doing, R.anim.not_doing, R.anim.slide_out_bottom)
                .add(containerViewId, fragment, fragment.javaClass.simpleName)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    protected fun addFragmentWithElement(@IdRes containerViewId: Int, fragment: Fragment, view: View?, transitionName: String?) {
        val fade = Fade()
        fade.duration = 500
        fragment.enterTransition = fade
        fragment.allowEnterTransitionOverlap = true
        fragment.allowReturnTransitionOverlap = true
        supportFragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(null)
                .addSharedElement(view!!, transitionName!!)
                .commit()
    }
}
