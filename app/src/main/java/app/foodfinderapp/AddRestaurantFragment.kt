package app.foodfinderapp;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import app.foodfinderapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import app.foodfinderapp.dto.Restaurant
import app.foodfinderapp.databinding.AddRestaurantBinding
import app.foodfinderapp.databinding.FragmentProfileBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddRestaurantFragment : Fragment() {

    private lateinit var binding: AddRestaurantBinding
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    : View? {

        db = FirebaseFirestore.getInstance()

        binding = AddRestaurantBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener { addRestaurant() }

        return binding.root
    }

    private fun addRestaurant() {
        val name = binding.editTextName.text.toString()
        val category = binding.editTextCategory.text.toString()
        val hours = binding.editTextHours.text.toString()
        val contact = binding.editTextContact.text.toString()
        val address = binding.editTextAddress.text.toString()

        val restaurant = Restaurant("", name, category, hours, contact, address)

        db.collection("restaurants")
            .document(name)
            .set(restaurant)
            .addOnSuccessListener {
                activity?.supportFragmentManager?.popBackStack()
            }
            .addOnFailureListener {
                // Handle errors here
            }
    }

}
