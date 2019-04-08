package com.dutchtechnologies.news_challenge.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dutchtechnologies.news_challenge.articles.HomeActivity
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_DURATION = 1500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Handler().postDelayed(
//            {
//                goToSearchResultsActivity(this)
//            }, SPLASH_DURATION
//        )

        Completable
            .complete()
            .delay(SPLASH_DURATION, TimeUnit.MILLISECONDS)
            .subscribe {
                goToHomeActivity(this)

            }
    }

    private fun goToHomeActivity(activity: Activity) {
        startActivity(Intent(activity, HomeActivity::class.java))
        finish()
    }
}