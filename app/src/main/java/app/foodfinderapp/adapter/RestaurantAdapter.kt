/*

This package is used to store adapter files, which are responsible for binding data to views in the app
*/
package app.foodfinderapp.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.foodfinderapp.databinding.RestaurantItemBinding
import app.foodfinderapp.dto.Restaurant

/*

This class is used to bind the data in the restaurant list to the RecyclerView.

The RecyclerView is used to display search results.
*/
class RestaurantAdapter(private var restaurantList: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    /*

    This method is responsible for creating and inflating the view holder for the RecyclerView.
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {

        val binding = RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantViewHolder(binding)
    }

    /*

    This method is used to bind data to the ViewHolder and display it in the RecyclerView.
    */
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurant = restaurantList[position]
        holder.bind(currentRestaurant)
    }
    /*

    This method returns the number of items in the RecyclerView.
    */
    override fun getItemCount() = restaurantList.size
    /*

    This inner class is used to create a ViewHolder, which is responsible for binding restaurant data to the layout file.
    */
    inner class RestaurantViewHolder(private val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*

        This method is used to bind restaurant data to the layout file.
        */
        fun bind(restaurant: Restaurant) {
            binding.restaurantName.text = restaurant.name
            binding.restaurantAddress.text = restaurant.address
// add other necessary bindings
        }
    }
    /*

    This method is used to update the data source and refresh the list.
    */
    fun setDataList(newList: List<Restaurant>) {
        restaurantList = newList.toMutableList()
        notifyDataSetChanged()
    }
}