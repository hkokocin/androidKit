package com.github.hkokocin.androidkit.widget

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import org.junit.Assert.*
import org.junit.Test

class PageChangedListenerTest{

    @Test
    fun canRedirectOnPageSelectedEvent() {
        val onPageSelected: (Int)->Unit = mock()
        val classToTest = PageChangedListener(onPageSelected)

        classToTest.onPageSelected(1)

        then(onPageSelected).should().invoke(1)
    }

    @Test
    fun canRedirectOnPageScrolledEvent() {
        val onPageScrolled: (Int, Float, Int)->Unit = mock()
        val classToTest = PageChangedListener(onPageScrolled = onPageScrolled)

        classToTest.onPageScrolled(1, 2f, 3)

        then(onPageScrolled).should().invoke(1, 2f, 3)
    }

    @Test
    fun canRedirectOnScrollStateChangedEvent() {
        val onScrollStateChanged: (Int)->Unit = mock()
        val classToTest = PageChangedListener(onPageScrollStateChanged = onScrollStateChanged)

        classToTest.onPageScrollStateChanged(1)

        then(onScrollStateChanged).should().invoke(1)
    }


}