package com.github.hkokocin.androidkit.app

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.github.hkokocin.androidkit.AndroidKit
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.winterbe.expekt.should
import org.junit.Before
import org.junit.Test
import java.io.Serializable

class BaseKitTest {

    val intent: Intent = mock()
    val resources: Resources = mock()
    val classToTest = object : BaseKit {}

    @Test
    fun canGetCharExtra() {
        given(intent.getCharExtra("name", ' ')).willReturn('c')

        val result = classToTest.getExtra(intent, "name", Char::class)

        result.should.be.equal('c')
    }

    @Test
    fun canGetArrayOfCharsExtra() {
        given(intent.getCharArrayExtra("name")).willReturn(CharArray(1) { 'c' })

        val result = classToTest.getExtra(intent, "name", Array<Char>::class)

        result!![0].should.be.equal('c')
    }

    @Test
    fun canGetCharArrayExtra() {
        given(intent.getCharArrayExtra("name")).willReturn(CharArray(1) { 'c' })

        val result = classToTest.getExtra(intent, "name", CharArray::class)

        result!![0].should.be.equal('c')
    }

    @Test
    fun canGetStringExtra() {
        given(intent.getStringExtra("name")).willReturn("string")

        val result = classToTest.getExtra(intent, "name", String::class)

        result.should.be.equal("string")
    }

    @Test
    fun canGetStringArrayExtra() {
        given(intent.getStringArrayExtra("name")).willReturn(arrayOf("string"))

        val result = classToTest.getExtra(intent, "name", Array<String>::class)

        result!![0].should.be.equal("string")
    }

    @Test
    fun canGetCharSequenceExtra() {
        given(intent.getCharSequenceExtra("name")).willReturn("charsequence")

        val result = classToTest.getExtra(intent, "name", CharSequence::class)

        result.should.be.equal("charsequence")
    }

    @Test
    fun canGetCharSequenceArrayExtra() {
        given(intent.getCharSequenceArrayExtra("name")).willReturn(arrayOf("charsequence"))

        val result = classToTest.getExtra(intent, "name", Array<CharSequence>::class)

        result!![0].should.be.equal("charsequence")
    }

    @Test
    fun canGetShortExtra() {
        given(intent.getShortExtra("name", 0)).willReturn(1)

        val result = classToTest.getExtra(intent, "name", Short::class)

        result.should.be.equal(1)
    }

    @Test
    fun canGetArrayOfShortsExtra() {
        given(intent.getShortArrayExtra("name")).willReturn(ShortArray(1) { 1 })

        val result = classToTest.getExtra(intent, "name", Array<Short>::class)

        result!![0].should.be.equal(1)
    }

    @Test
    fun canGetShortArrayExtra() {
        given(intent.getShortArrayExtra("name")).willReturn(ShortArray(1) { 1 })

        val result = classToTest.getExtra(intent, "name", ShortArray::class)

        result!![0].should.be.equal(1)
    }

    @Test
    fun canGetIntExtra() {
        given(intent.getIntExtra("name", 0)).willReturn(1)

        val result = classToTest.getExtra(intent, "name", Int::class)

        result.should.be.equal(1)
    }

    @Test
    fun canGetArrayOfIntsExtra() {
        given(intent.getIntArrayExtra("name")).willReturn(IntArray(1) { 1 })

        val result = classToTest.getExtra(intent, "name", Array<Int>::class)

        result!![0].should.be.equal(1)
    }

    @Test
    fun canGetIntArrayExtra() {
        given(intent.getIntArrayExtra("name")).willReturn(IntArray(1) { 1 })

        val result = classToTest.getExtra(intent, "name", IntArray::class)

        result!![0].should.be.equal(1)
    }

    @Test
    fun canGetLongExtra() {
        given(intent.getLongExtra("name", 0)).willReturn(1L)

        val result = classToTest.getExtra(intent, "name", Long::class)

        result.should.be.equal(1L)
    }

    @Test
    fun canGetArrayOfLongsExtra() {
        given(intent.getLongArrayExtra("name")).willReturn(LongArray(1) { 1L })

        val result = classToTest.getExtra(intent, "name", Array<Long>::class)

        result!![0].should.be.equal(1L)
    }

    @Test
    fun canGetLongArrayExtra() {
        given(intent.getLongArrayExtra("name")).willReturn(LongArray(1) { 1L })

        val result = classToTest.getExtra(intent, "name", LongArray::class)

        result!![0].should.be.equal(1L)
    }

    @Test
    fun canGetDoubleExtra() {
        given(intent.getDoubleExtra("name", 0.0)).willReturn(1.0)

        val result = classToTest.getExtra(intent, "name", Double::class)

        result.should.be.equal(1.0)
    }

    @Test
    fun canGetArrayOfDoublesExtra() {
        given(intent.getDoubleArrayExtra("name")).willReturn(DoubleArray(1) { 1.0 })

        val result = classToTest.getExtra(intent, "name", Array<Double>::class)

        result!![0].should.be.equal(1.0)
    }

    @Test
    fun canGetDoubleArrayExtra() {
        given(intent.getDoubleArrayExtra("name")).willReturn(DoubleArray(1) { 1.0 })

        val result = classToTest.getExtra(intent, "name", DoubleArray::class)

        result!![0].should.be.equal(1.0)
    }

    @Test
    fun canGetFloatExtra() {
        given(intent.getFloatExtra("name", 0f)).willReturn(1f)

        val result = classToTest.getExtra(intent, "name", Float::class)

        result.should.be.equal(1f)
    }

    @Test
    fun canGetArrayOfFloatsExtra() {
        given(intent.getFloatArrayExtra("name")).willReturn(FloatArray(1) { 1f })

        val result = classToTest.getExtra(intent, "name", Array<Float>::class)

        result!![0].should.be.equal(1f)
    }

    @Test
    fun canGetFloatArrayExtra() {
        given(intent.getFloatArrayExtra("name")).willReturn(FloatArray(1) { 1f })

        val result = classToTest.getExtra(intent, "name", FloatArray::class)

        result!![0].should.be.equal(1f)
    }

    @Test
    fun canGetByteExtra() {
        given(intent.getByteExtra("name", 0)).willReturn(1)

        val result = classToTest.getExtra(intent, "name", Byte::class)

        result.should.be.equal(1)
    }

    @Test
    fun canGetArrayOfBytesExtra() {
        given(intent.getByteArrayExtra("name")).willReturn(ByteArray(1) { 1 })

        val result = classToTest.getExtra(intent, "name", Array<Byte>::class)

        result!![0].should.be.equal(1)
    }

    @Test
    fun canGetByteArrayExtra() {
        given(intent.getByteArrayExtra("name")).willReturn(ByteArray(1) { 1 })

        val result = classToTest.getExtra(intent, "name", ByteArray::class)

        result!![0].should.be.equal(1)
    }

    @Test
    fun canGetSerializableExtra() {
        val serializable: Serializable = mock()
        given(intent.getSerializableExtra("name")).willReturn(serializable)

        val result = classToTest.getExtra(intent, "name", Serializable::class)

        result.should.be.equal(serializable)
    }

    @Test
    fun canGetBundleExtra() {
        val bundle: Bundle = mock()
        given(intent.getBundleExtra("name")).willReturn(bundle)

        val result = classToTest.getExtra(intent, "name", Bundle::class)

        result.should.be.equal(bundle)
    }

    @Test
    fun canGetStringResource() {
        given(resources.getString(1)).willReturn("string")

        val result = classToTest.getResource(resources, 1, String::class)

        result.should.be.equal("string")
    }

    @Test
    fun canGetStringArrayResource() {
        given(resources.getStringArray(1)).willReturn(arrayOf("string"))

        val result = classToTest.getResource(resources, 1, Array<String>::class)

        result[0].should.be.equal("string")
    }

    @Test
    fun canGetCharSequenceResource() {
        given(resources.getText(1)).willReturn("string")

        val result = classToTest.getResource(resources, 1, CharSequence::class)

        result.should.be.equal("string")
    }

    @Test
    fun canGetCharSequenceArrayResource() {
        given(resources.getTextArray(1)).willReturn(arrayOf("string"))

        val result = classToTest.getResource(resources, 1, Array<CharSequence>::class)

        result[0].should.be.equal("string")
    }

    @Test
    fun canGetIntResource() {
        given(resources.getInteger(1)).willReturn(2)

        val result = classToTest.getResource(resources, 1, Int::class)

        result.should.be.equal(2)
    }

    @Test
    fun canGetIntArrayResource() {
        given(resources.getIntArray(1)).willReturn(IntArray(1) { 2 })

        val result = classToTest.getResource(resources, 1, Array<Int>::class)

        result[0].should.be.equal(2)
    }

    @Test
    fun canGetBooleanResource() {
        given(resources.getBoolean(1)).willReturn(true)

        val result = classToTest.getResource(resources, 1, Boolean::class)

        result.should.be.`true`
    }

    @Test
    fun canGetDrawableResource() {
        val drawable: Drawable = mock()
        given(resources.getDrawable(1)).willReturn(drawable)

        val result = classToTest.getResource(resources, 1, Drawable::class)

        result.should.be.equal(drawable)
    }

    @Test
    fun canGetDimension() {
        given(resources.getDimensionPixelSize(1)).willReturn(2)

        val result = classToTest.dimensionInPixels(resources, 1)

        result.should.be.equal(2)
    }
}

class BaseKitExtensionsTest {

    val intent: Intent = mock()
    val resources: Resources = mock()
    val kit: AndroidKit = mock()

    @Before
    fun setUp() {
        AndroidKit.instance = kit
    }

    @Test
    fun canGetExtra() {
        given(kit.getExtra(intent, "name", String::class)).willReturn("value")

        val result = getExtra(intent, "name", String::class)

        result.should.be.equal("value")
    }

    @Test
    fun canGetResource() {
        given(kit.getResource(resources, 1, String::class)).willReturn("value")

        val result = getResource(resources, 1, String::class)

        result.should.be.equal("value")
    }

    @Test
    fun canGetDimension() {
        given(kit.dimensionInPixels(resources, 1)).willReturn(2)

        val result by dimensionInPixels(resources, 1)

        result.should.be.equal(2)
    }

}