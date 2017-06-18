package com.github.hkokocin.androidkit.widget

import android.support.v4.view.ViewPager

class PageChangedListener(
        private val onPageSelected: (Int) -> Unit = {},
        private val onPageScrollStateChanged: (Int) -> Unit = {},
        private val onPageScrolled: (Int, Float, Int) -> Unit = {_, _, _ ->}
): ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int):Unit = onPageScrollStateChanged.invoke(state)

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        onPageScrolled.invoke(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int):Unit = onPageSelected.invoke(position)
}