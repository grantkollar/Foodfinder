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

        binding = AddRestaurantBinding.inflate(inflater, container, false)

        viewModel = AddRestaurantViewModel()

        binding.buttonSave.setOnClickListener { addRestaurant() }

        return binding.root
    }

    private fun addRestaurant() {
        viewModel.name = binding.editTextName.text.toString()
        viewModel.category = binding.editTextCategory.text.toString()
        viewModel.hours = binding.editTextHours.text.toString()
        viewModel.contact = binding.editTextContact.text.toString()
        viewModel.address = binding.editTextAddress.text.toString()

        // 获取当前用户的 ID
        val ownerId = viewModel.getCurrentUserId()

        val restaurant = Restaurant("", viewModel.name, viewModel.category, viewModel.hours, viewModel.contact, viewModel.address, ownerId)

        restaurantDao.addRestaurant(restaurant)

        // 返回上一个 Fragment
        activity?.supportFragmentManager?.popBackStack()
    }
}
