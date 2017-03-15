package com.github.hkokocin.viper.android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.hkokocin.viper.android.FragmentLifecycle
import com.github.hkokocin.viper.android.ViperActivity
import com.github.hkokocin.viper.interactor.FragmentInteractor

abstract class ViperFragment() : Fragment() {

    abstract val interactor: FragmentInteractor

    lateinit var lifecycle: FragmentLifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycle = interactor.router.lifecycle
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (activity is ViperActivity) {
            val viperActivity = activity as ViperActivity
            viperActivity.lifecycle.onOptionsItemSelected = { lifecycle.onOptionsItemSelected(it) }
        }

        return interactor.presenter.createView(inflater, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        lifecycle.onStart()
    }

    override fun onResume() {
        super.onResume()
        lifecycle.onResume()
    }

    override fun onPause() {
        super.onPause()
        lifecycle.onPause()
    }

    override fun onStop() {
        super.onStop()
        lifecycle.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        lifecycle.onSaveInstanceState(outState)
    }
}