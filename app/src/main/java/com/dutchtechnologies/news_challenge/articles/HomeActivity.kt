package com.dutchtechnologies.news_challenge.articles

import android.os.Bundle
import com.dutchtechnologies.news_challenge.Navigation.Companion.DESTINATION_HOME
import com.dutchtechnologies.news_challenge.R
import com.dutchtechnologies.news_challenge.base.BaseActivity
import com.dutchtechnologies.news_challenge.onDestinationSelected
import com.dutchtechnologies.news_challenge.popBackStack

class HomeActivity : BaseActivity() {
    override fun screenName(): String? {
        return ""
    }

    override fun layoutResource(): Int? {
        return R.layout.activity_home
    }

    override fun setupView() {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            onDestinationSelected(DESTINATION_HOME)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            popBackStack()
            return
        }

        finish()
    }
}
