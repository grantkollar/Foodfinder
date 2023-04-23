package app.foodfinderapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.foodfinderapp.databinding.RestaurantItemBinding
import app.foodfinderapp.dto.Restaurant

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class RestaurantAdapter(
    private var restaurantList: List<Restaurant>,
    private val listener: OnItemClickListener
) :
RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = RestaurantItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurant = restaurantList[position]
        holder.bind(currentRestaurant)

        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    override fun getItemCount() = restaurantList.size

    fun setDataList(newList: List<Restaurant>) {
        restaurantList = newList.toMutableList()
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(private val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.restaurantName.text = restaurant.name
            binding.restaurantAddress.text = restaurant.address
            binding.restaurantRating.rating = restaurant.rating
        }
    }
}

