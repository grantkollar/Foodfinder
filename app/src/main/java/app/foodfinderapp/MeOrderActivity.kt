package app.foodfinderapp

import BaseActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.foodfinderapp.databinding.ActivityMeOrderBinding
import app.foodfinderapp.dto.Food
import kotlinx.coroutines.NonDisposableHandle.parent

class MeOrderActivity : BaseActivity(){
    private val foodList : ArrayList<FoodInfo> = ArrayList<FoodInfo>()
    private lateinit var binding : ActivityMeOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeOrderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setContentView(R.layout.activity_me_order)
        binding.toolbarOrder.setOnClickListener{ onBackPressed()}

        initData()
        val layoutManager = LinearLayoutManager(this)
        binding.orderRecyclerView.layoutManager = layoutManager
        val adapter = FoodAdapter(foodList)
        binding.orderRecyclerView.adapter = adapter

    }

    private class FoodViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.food_image)
        val name : TextView = view.findViewById(R.id.food_name)
        val status : TextView = view.findViewById(R.id.food_status)
        val price : TextView = view.findViewById(R.id.food_price)
        val amount : TextView = view.findViewById(R.id.food_amount)
    }


    private class FoodAdapter(val foodList:List<FoodInfo>): RecyclerView.Adapter<FoodViewHolder>() {

        override fun getItemCount(): Int {
            return foodList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_order,parent,false)
            return FoodViewHolder(view)

        }

        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
            val info = foodList[position]
            holder.image.setImageResource(info.food_image)
            holder.name.text = info.food_name
            holder.status.text = info.food_status
            holder.price.text = info.food_price
            holder.amount.text = info.food_amount
        }

    }

    private fun initData() {
        foodList.add(FoodInfo(R.drawable.icon_food_1, "Bread","Preparing","$5","1"))

    }
}