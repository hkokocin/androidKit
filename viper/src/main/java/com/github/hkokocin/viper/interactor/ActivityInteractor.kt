package com.github.hkokocin.viper.interactor

import com.github.hkokocin.viper.android.ActivityLifecycle
import com.github.hkokocin.viper.router.ActivityRouter
import com.github.hkokocin.viper.router.Router
import com.github.hkokocin.viper.interactor.Interactor

interface ActivityInteractor : Interactor {
    override val router: ActivityRouter
}