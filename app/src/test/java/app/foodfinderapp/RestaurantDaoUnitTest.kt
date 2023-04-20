package app.foodfinderapp.dao

import app.foodfinderapp.dto.Restaurant
import com.google.firebase.firestore.FirebaseFirestore
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class RestaurantDaoTest {

    private lateinit var restaurantDao: RestaurantDao
    private val firestoreMock = mockk<FirebaseFirestore>()

    @Before
    fun setUp() {
        restaurantDao = RestaurantDao()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `addRestaurant should call set method of Firestore with correct parameters`() {
        val restaurant = Restaurant(
            "12345",
            "Test Restaurant",
            "Fast Food",
            "Mon-Fri 9am-5pm",
            "123-456-7890",
            "123 Test St",
            "ownerId"
        )


        every {
            firestoreMock.collection("restaurants").document(restaurant.restaurantID)
                .set(restaurant)
        } returns mockk()

        restaurantDao.addRestaurant(restaurant)

        verify(exactly = 1) {
            firestoreMock.collection("restaurants").document(restaurant.restaurantID)
                .set(restaurant)
        }
    }

    @Test
    fun `updateRestaurant should call update method of Firestore with correct parameters`() {
        val restaurant = Restaurant(
            "12345",
            "Test Restaurant",
            "Fast Food",
            "Mon-Fri 9am-5pm",
            "123-456-7890",
            "123 Test St",
            "ownerId"
        )


        every {
            firestoreMock.collection("restaurants").document(restaurant.restaurantID).update(
                "name", restaurant.name,
                "address", restaurant.address,
                "category", restaurant.category,
                "contact", restaurant.contact,
                "hours", restaurant.hours
            )
        } returns mockk()

        restaurantDao.updateRestaurant(restaurant)

        verify(exactly = 1) {
            firestoreMock.collection("restaurants").document(restaurant.restaurantID).update(
                "name", restaurant.name,
                "address", restaurant.address,
                "category", restaurant.category,
                "contact", restaurant.contact,
                "hours", restaurant.hours
            )
        }
    }

    @Test
    fun `deleteRestaurant should call delete method of Firestore with correct parameters`() {
        val restaurant = Restaurant(
            "12345",
            "Test Restaurant",
            "Fast Food",
            "Mon-Fri 9am-5pm",
            "123-456-7890",
            "123 Test St",
            "ownerId"
        )


        every {
            firestoreMock.collection("restaurants").document(restaurant.restaurantID).delete()
        } returns mockk()

        restaurantDao.deleteRestaurant(restaurant)

        verify(exactly = 1) {
            firestoreMock.collection("restaurants").document(restaurant.restaurantID).delete()
        }
    }

    @Test
    fun `getRestaurantByName should call get method of Firestore with correct parameters`() {
        val name = "Test Restaurant"

        every {
            firestoreMock.collection("restaurants").document(name).get()
        } returns mockk()

        restaurantDao.getRestaurantByName(name) {}

        verify(exactly = 1) {
            firestoreMock.collection("restaurants").document(name).get()
        }
    }
}
