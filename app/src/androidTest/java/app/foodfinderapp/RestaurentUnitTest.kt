package app.foodfinderapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RestaurentUnitTest {
    @Test
    fun `Uses Application Context`() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("app.foodfinderapp", appContext.packageName)
    }
    @Test
    fun `Given a feed of restaurent data, when i search for "Taco" then i should receive a result with the name EI Taco Veloz and with the number of comments being a rating of 10 and the given address of 7 Martin Luther Ling Dr E, Corryville`(){
        givenAFeedOfMockedrestaurentDataIsAvailable()
        whenSearchForEITacoVeloz()
        thenResultContains10RatingAndMartinLutherAddress()
        thenVerifyFunctionsHasInvoked()
    }

    private fun givenAFeedOfMockedrestaurentDataIsAvailable() {
        TODO("Not yet implemented")
    }

    private fun whenSearchForEITacoVeloz() {
        TODO("Not yet implemented")
    }

    private fun thenResultContains10RatingAndMartinLutherAddress() {
        TODO("Not yet implemented")
    }

    private fun thenVerifyFunctionsHasInvoked() {
        TODO("Not yet implemented")
    }
}