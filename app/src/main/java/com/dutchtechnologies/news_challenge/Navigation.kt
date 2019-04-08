package com.dutchtechnologies.news_challenge

import android.support.annotation.IdRes
import android.support.annotation.StringDef
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.dutchtechnologies.news_challenge.Navigation.Companion.DESTINATION_HOME
import com.dutchtechnologies.news_challenge.articles.NewsFragment
import com.dutchtechnologies.news_challenge.articles.SourcesFragment

const val BACK_STACK_ROOT_TAG = "root_fragment"

class Navigation {
    companion object {
        const val DESTINATION_HOME = "D_HOME"
        const val DESTINATION_NEWS = "D_NEWS"

    }

    @StringDef(DESTINATION_HOME, DESTINATION_NEWS)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Destination
}

fun FragmentActivity.onDestinationSelected(@Navigation.Destination fragmentTag: String) {

    supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
        .replace(
            R.id.home_container,
            getSelectedFragmentDestination(fragmentTag),
            fragmentTag
        )
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
        else -> NewsFragment.newInstance()
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

fun FragmentActivity.popBackStack() {
    supportFragmentManager.popBackStack()
}