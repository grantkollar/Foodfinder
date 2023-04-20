package app.foodfinderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import app.foodfinderapp.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var recyclerView: RecyclerView
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val dataBase = FirebaseFirestore.getInstance()
    private val collectionRef = dataBase.collection("restaurants")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fetchRestaurant()

        return root

    }
    private fun fetchRestaurant(){
        collectionRef.get()
            .addOnSuccessListener { documents ->
                val dataList = mutableListOf<HashMap<String, Any>>()
                for (document in documents) {
                    // Get the data as a HashMap
                    val data = document.data
                    dataList.add(data as HashMap<String, Any>)
                }
                // Create an adapter and set it on the RecyclerView
               /* val adapter = MyAdapter(dataList)
                recyclerView.adapter = adapter*/
                println(dataList[2])
                Toast.makeText(context, "Yes.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "No.", Toast.LENGTH_SHORT).show()
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}