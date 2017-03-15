package com.github.hkokocin.viper.interactor

import com.github.hkokocin.viper.Presenter
import com.github.hkokocin.viper.router.Router

interface Interactor {
    val presenter: Presenter
    val router: Router
}