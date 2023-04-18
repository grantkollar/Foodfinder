package app.foodfinderapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.foodfinderapp.databinding.RestaurantItemBinding
import app.foodfinderapp.dto.Restaurant

//Used to bind the data in the restaurant list to the RecyclerView (Search results are displayed using RecyclerView)
class RestaurantAdapter(private var restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {

        val binding = RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantViewHolder(binding)
    }

    //Bind data to ViewHolder
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurant = restaurantList[position]
        holder.bind(currentRestaurant)
    }

    override fun getItemCount() = restaurantList.size

    //ViewHolder, used to bind restaurant data to the layout file
    inner class RestaurantViewHolder(private val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.restaurantName.text = restaurant.name
            binding.restaurantAddress.text = restaurant.address
            // another if need
        }
    }

    //Update the data source and refresh the list
    fun setDataList(newList: List<Restaurant>) {
        restaurantList = newList.toMutableList()
    }

}
