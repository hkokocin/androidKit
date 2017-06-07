package com.github.hkokocin.androidkit

import com.nhaarman.mockito_kotlin.willReturn
import org.mockito.BDDMockito
import kotlin.reflect.KClass

fun <T> BDDMockito.BDDMyOngoingStubbing<T>.onArgument(index: Int) = Argument(this, Any::class, index)
fun <T> BDDMockito.BDDMyOngoingStubbing<T>.andReturn(callback: () -> T) = willReturn(callback)
fun <T> BDDMockito.BDDMyOngoingStubbing<T>.andReturn(value: T) = willReturn(value)!!
fun <T> BDDMockito.BDDMyOngoingStubbing<T>.andReturn(value: T, vararg values: T) = willReturn(value, *values)!!

class Argument<T : Any, R>(
        val stubbing: BDDMockito.BDDMyOngoingStubbing<R>,
        val type: KClass<T>,
        val index: Int
) {
    inline fun <reified V : Any> ofType(): Argument<V, R> = Argument(stubbing, V::class, index)

    @Suppress("UNCHECKED_CAST")
    fun doApply(callback: T.() -> Unit) = stubbing.will {
        callback.invoke(it.arguments[index] as T)
    }!!
}