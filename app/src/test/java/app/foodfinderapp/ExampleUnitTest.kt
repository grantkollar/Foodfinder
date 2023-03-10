package app.foodfinderapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.foodfinderapp.dto.Restaurant
import app.foodfinderapp.service.RestaurantService
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Test
import kotlinx.coroutines.launch
import org.junit.Assert.*
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    lateinit var restaurantService: RestaurantService
    var allRestaurants : List<Restaurant>? = ArrayList<Restaurant>()


    @MockK
    lateinit var mockRestaurantService: RestaurantService
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `Given servie connects with JSON of local restaurants,and the data is read and parsed then the restaurant collection should be greater then zero`()=runTest{
        launch(Dispatchers.Main){
            givenRestaurantServiceIsInitialized()
            wheServiceDataAreReadAndParsed()
            thenTheRestaurantCollectionSizeShouldBeGreaterThenZero()
        }
    }
    private fun givenRestaurantServiceIsInitialized(){
        restaurantService = RestaurantService()
    }
     private fun wheServiceDataAreReadAndParsed(){
        allRestaurants = restaurantService.getAllRestaurants()
     }
    private fun thenTheRestaurantCollectionSizeShouldBeGreaterThenZero(){
        assertNotNull(allRestaurants)
        assertTrue(allRestaurants!!.isNotEmpty())
    }

}