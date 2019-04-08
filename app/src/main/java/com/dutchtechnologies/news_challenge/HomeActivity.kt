package com.dutchtechnologies.news_challenge

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.dutchtechnologies.news_challenge.Navigation.Companion.DESTINATION_HOME
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {
    override fun screenName(): String? {
        return ""
    }

    override fun layoutResource(): Int? {
        return R.layout.activity_home
    }

    override fun setupView() {
        onDestinationSelected(DESTINATION_HOME)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            popBackStack()
            return
        }

        finish()
    }
}
