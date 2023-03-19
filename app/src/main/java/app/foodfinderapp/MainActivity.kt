package app.foodfinderapp

import app.foodfinderapp.AddRestaurantFragment
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.foodfinderapp.adapter.RestaurantAdapter
import app.foodfinderapp.databinding.ActivityMainBinding
import app.foodfinderapp.dto.Restaurant
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.recyclerview.widget.RecyclerView




class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val db = Firebase.firestore
    private lateinit var adapter: RestaurantAdapter
    private lateinit var recyclerView : RecyclerView
    private var isSearching = false
    val restaurantList = mutableListOf<Restaurant>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        adapter = RestaurantAdapter(restaurantList)
        recyclerView = findViewById<RecyclerView>(R.id.restaurantRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.visibility = View.GONE

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    if (isLoggedIn()) {
                        val currentUser = FirebaseAuth.getInstance().currentUser
                        if (currentUser != null) {
                            // Show a toast with the user's email
                            Toast.makeText(this, "Logged in as ${currentUser.email}", Toast.LENGTH_SHORT).show()
                        }
                        navController.navigate(R.id.navigation_profile)
                    } else {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    true
                }

                R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_dashboard -> {
                    navController.navigate(item.itemId)
                    true
                }
                else -> false
            }
        }

        val restaurantRef = db.collection("restaurants")
        restaurantRef.get()
            .addOnSuccessListener { documents ->
            for (document in documents) {
                val restaurant = document.toObject(Restaurant::class.java)
                restaurantList.add(restaurant)
            }
            adapter.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            Log.e(TAG, "Error getting restaurants", exception)
        }

    }

    fun isLoggedIn(): Boolean {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return currentUser != null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = "Search Restaurants"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle query text change

                if (newText.isNullOrEmpty() || newText == "") {
                    recyclerView.visibility = View.GONE
                } else {
                    recyclerView.visibility = View.VISIBLE

                    val filteredList = restaurantList.filter { it.name.contains(newText ?: "") }
                    adapter.setDataList(filteredList)
                    adapter.notifyDataSetChanged()
                }

                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "lol", Toast.LENGTH_SHORT).show()
                true }

            R.id.action_add_restaurant -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.navigation_add_restaurant)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.menu.findItem(R.id.navigation_home).isChecked = true
        findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.navigation_home)
    }

}
