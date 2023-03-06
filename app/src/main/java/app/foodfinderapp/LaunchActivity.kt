package app.foodfinderapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is where add any code to initialize your app

        // Start the MainActivity
        val intent = Intent(this@LaunchActivity, MainActivity::class.java)
        startActivity(intent)

        // Finish the LaunchActivity so it can't be returned to
        finish()
    }
}
