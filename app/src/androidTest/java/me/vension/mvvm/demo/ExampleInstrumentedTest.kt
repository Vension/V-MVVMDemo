package me.vension.mvvm.demo

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented TestSort, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under TestSort.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("me.vension.mvvm.demo", appContext.packageName)
    }
}
