package com.github.hkokocin.viper.interactor

import com.github.hkokocin.viper.interactor.Interactor
import com.github.hkokocin.viper.router.FragmentRouter

interface FragmentInteractor : Interactor {
    override val router: FragmentRouter
}