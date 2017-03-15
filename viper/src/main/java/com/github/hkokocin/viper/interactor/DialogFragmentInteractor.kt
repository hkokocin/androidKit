package com.github.hkokocin.viper.interactor

import com.github.hkokocin.viper.interactor.Interactor
import com.github.hkokocin.viper.router.DialogFragmentRouter

interface DialogFragmentInteractor : Interactor {
    override val router: DialogFragmentRouter

}