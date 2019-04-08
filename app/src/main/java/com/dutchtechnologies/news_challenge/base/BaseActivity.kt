package com.dutchtechnologies.news_challenge.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import android.view.View
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), View.OnClickListener {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (isSmartPhone()) {
//            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        }

        layoutResource()?.run { setContentView(this) }
        setupView()
    }

    override fun onResume() {
        super.onResume()

        screenName()?.run {
            //            Tracking.screenView(this)
        }
//        Tracking.enterForeground()
    }

    override fun onPause() {
        super.onPause()
//        Tracking.exitForeground()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View?) {
    }

    abstract fun screenName(): String?

    abstract fun layoutResource(): Int?

    abstract fun setupView()
}