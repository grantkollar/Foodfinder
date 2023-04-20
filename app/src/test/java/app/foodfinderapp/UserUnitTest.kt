import app.foodfinderapp.dto.User
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {

    @Test
    fun createUser() {
        val id = "test_user_id"
        val userPhoto = "test_user_photo_url"

        // Create a new User instance
        val user = User(userPhoto, Firebase.database.reference.child("Authentication").child(id))

        // Test that the user's photo and reference were set correctly
        assertEquals(userPhoto, user.userPhoto)
        assertEquals("Authentication/$id", user.ref.toString())
    }
}
