package app.foodfinderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.foodfinderapp.dto.Restaurant

class MyRestaurantFragment : Fragment() {

    private lateinit var viewModel: MyRestaurantViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_restaurant, container, false)

        viewModel = ViewModelProvider(this).get(MyRestaurantViewModel::class.java)

        viewModel.restaurantsLiveData.observe(viewLifecycleOwner, Observer { restaurantList ->
            val restaurantContainer = view.findViewById<LinearLayout>(R.id.restaurant_list)
            restaurantContainer.removeAllViews()
            restaurantList.forEach { restaurant ->
                val cardView = createRestaurantCard(restaurant)
                restaurantContainer.addView(cardView)
            }
        })

        return view
    }

    private fun createRestaurantCard(restaurant: Restaurant): View {
        val cardView = layoutInflater.inflate(R.layout.restaurant_card, null)

        cardView.findViewById<TextView>(R.id.restaurant_name).text = restaurant.name
        cardView.findViewById<TextView>(R.id.restaurant_address).text = restaurant.address

        cardView.findViewById<View>(R.id.edit_button).setOnClickListener {
            val editPanel = cardView.findViewById<LinearLayout>(R.id.edit_panel)
            val saveButton = cardView.findViewById<Button>(R.id.save_button)

            if (editPanel.visibility == View.GONE) {
                // Show dropdown panel
                editPanel.visibility = View.VISIBLE
                saveButton.setOnClickListener {
                    // Save changes
                    val name = cardView.findViewById<EditText>(R.id.edit_name).text.toString()
                    val address = cardView.findViewById<EditText>(R.id.edit_address).text.toString()

                    val newRestaurant = Restaurant().apply {
                        this.restaurantID = restaurant.restaurantID
                        this.category = restaurant.category
                        this.contact = restaurant.contact
                        this.hours = restaurant.hours
                        this.ownerId = restaurant.ownerId
                        this.name = name
                        this.address = address
                    }
                    viewModel.updateRestaurant(newRestaurant)

                    // hide dropdown panel
                    editPanel.visibility = View.GONE
                }
            } else {
                editPanel.visibility = View.GONE
            }
        }


        cardView.findViewById<View>(R.id.delete_button).setOnClickListener {
            viewModel.deleteRestaurant(restaurant)
        }

        return cardView
    }
}
