/**

This file contains the implementation of the AddRestaurantFragment class, which is a Fragment that
allows the user to add a new restaurant to the database. It inflates the AddRestaurantBinding layout
and sets up an onClickListener for the "Save" button. When the button is clicked, it retrieves the
values entered by the user, creates a new Restaurant object, and adds it to the database using the
RestaurantDao class. It then returns to the previous Fragment using popBackStack().
 */


package app.foodfinderapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.foodfinderapp.AddRestaurantViewModel
import app.foodfinderapp.dao.RestaurantDao
import app.foodfinderapp.dto.Restaurant
import app.foodfinderapp.databinding.AddRestaurantBinding

class AddRestaurantFragment : Fragment() {

    private lateinit var binding: AddRestaurantBinding
    private val restaurantDao = RestaurantDao()
    private lateinit var viewModel: AddRestaurantViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment using the AddRestaurantBinding layout.
        binding = AddRestaurantBinding.inflate(inflater, container, false)

        // Initialize the ViewModel
        viewModel = AddRestaurantViewModel()

        // Set up an onClickListener for the "Save" button.
        binding.buttonSave.setOnClickListener { addRestaurant() }

        return binding.root
    }

    /**
     * This method retrieves the values entered by the user, creates a new Restaurant object, and adds
     * it to the database using the RestaurantDao class. It then returns to the previous Fragment using
     * popBackStack().
     */

    private fun addRestaurant() {
        viewModel.name = binding.editTextName.text.toString()
        viewModel.category = binding.editTextCategory.text.toString()
        viewModel.hours = binding.editTextHours.text.toString()
        viewModel.contact = binding.editTextContact.text.toString()
        viewModel.address = binding.editTextAddress.text.toString()

        // Get the ID of the current user.
        val ownerId = viewModel.getCurrentUserId()

        // Create a new Restaurant object using the retrieved values.
        val restaurant = Restaurant(viewModel.restaurantID, viewModel.name, viewModel.category, viewModel.hours, viewModel.contact, viewModel.address, ownerId)

        // Add the new Restaurant object to the database using the RestaurantDao class.
        restaurantDao.addRestaurant(restaurant)

        // Return to the previous Fragment using popBackStack().
        activity?.supportFragmentManager?.popBackStack()
    }
}
