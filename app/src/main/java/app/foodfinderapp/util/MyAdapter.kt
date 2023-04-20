package app.foodfinderapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.foodfinderapp.R
import app.foodfinderapp.dto.Restaurant

class MyAdapter (private val restaurantList : ArrayList<Restaurant>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_restaurant,
        parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")

        return restaurantList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val bodyTextView: TextView = itemView.findViewById(R.id.bodyTextView)
    }
}