package com.dutchtechnologies.news_challenge

import android.os.Build
import android.support.annotation.IdRes
import android.support.annotation.StringDef
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.dutchtechnologies.news_challenge.Navigation.Companion.DESTINATION_HOME

const val BACK_STACK_ROOT_TAG = "root_fragment"

class Navigation {
    companion object {
        const val DESTINATION_HOME = "D_HOME"
    }

    @StringDef(DESTINATION_HOME)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Destination
}

fun FragmentActivity.onDestinationSelected(@Navigation.Destination fragmentTag: String) {

    supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        .replace(
            R.id.home_container,
            getSelectedFragmentDestination(fragmentTag),
            fragmentTag)
        .addToBackStack(BACK_STACK_ROOT_TAG)
        .commitAllowingStateLoss()
}


fun FragmentActivity.addFragmentOnTop(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.home_container, fragment)
        .addToBackStack(null)
        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        .commitAllowingStateLoss()
}

fun FragmentActivity.getSelectedFragmentDestination(@Navigation.Destination fragmentTag: String): Fragment {
    var destinationFragment = supportFragmentManager.findFragmentByTag(fragmentTag)

    if (destinationFragment == null) {
        destinationFragment = createFragmentDestinationInstance(fragmentTag)
    }

    return destinationFragment
}

private fun createFragmentDestinationInstance(@Navigation.Destination fragmentTag: String): Fragment =
    when (fragmentTag) {
        DESTINATION_HOME -> SourcesFragment.newInstance()
        else -> SourcesFragment.newInstance()
    }

fun FragmentActivity.fragmentAddToBackStack(@IdRes container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().run {
        val name = fragment.javaClass.simpleName
        replace(container, fragment, name)
        setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        addToBackStack(name)
        commitAllowingStateLoss()
    }
}

fun FragmentActivity.fragmentReplace(@IdRes container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().run {
        val name = fragment.javaClass.simpleName
        setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        replace(container, supportFragmentManager.findFragmentByTag(name) ?: fragment, name)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) commitNowAllowingStateLoss() else commitAllowingStateLoss()
    }
}

fun FragmentActivity.commitTransaction(@IdRes container: Int, fragment: Fragment) {
    val tag = fragment.javaClass.simpleName
    val fragmentPopped = supportFragmentManager.popBackStackImmediate(tag, 0)
    val fragmentTransaction = supportFragmentManager.beginTransaction()

    if (!fragmentPopped) {
        fragmentTransaction
            .replace(container, fragment)
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
    }

    fragmentTransaction
        .addToBackStack(tag)
        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        .commitAllowingStateLoss()
}

fun FragmentActivity.popBackStack() {
    supportFragmentManager.popBackStack()
}