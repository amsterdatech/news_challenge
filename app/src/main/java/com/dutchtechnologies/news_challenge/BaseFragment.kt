package com.dutchtechnologies.news_challenge

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : Fragment(), View.OnClickListener {
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        screenName()?.run {
            //            Tracking.screenView(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutResource(), container, false)
        setupView(view)

        return view
    }

    override fun onClick(view: View?) {
    }

    @LayoutRes
    abstract fun layoutResource(): Int

    abstract fun setupView(view: View)

    abstract fun screenName(): String?

}