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
import app.foodfinderapp.databinding.ActivityMyCouponBinding

class MyCouponActivity : BaseActivity() {
    private val bagList:ArrayList<CouponInfo> = ArrayList<CouponInfo>()
    private lateinit var binding:ActivityMyCouponBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCouponBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setContentView(R.layout.activity_my_coupon)
        binding.toolbarCoupon.setOnClickListener{ onBackPressed()}

        initDate()
        val layoutManager = LinearLayoutManager(this)
        binding.couponRecycler.layoutManager = layoutManager
        val adapter = CouponAdapter(bagList)
        binding.couponRecycler.adapter = adapter
    }

    private class CouponViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image :ImageView = view.findViewById(R.id.image1)
        val bigTitle : TextView = view.findViewById(R.id.big_title)
        val time : TextView = view.findViewById(R.id.time)
        val price : TextView = view.findViewById(R.id.price)
        val text : TextView = view.findViewById(R.id.test_two)
    }

    private class CouponAdapter(val bagList: List<CouponInfo>): RecyclerView.Adapter<CouponViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_coupon,parent,false)
            return CouponViewHolder(view)
        }

        override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
            val info = bagList[position]
            holder.image.setImageResource(info.imageData)
            holder.bigTitle.text = info.bigTitle
            holder.time.text = info.Time
            holder.price.text = info.price
            holder.text.text = info.text
        }

        override fun getItemCount(): Int {
            return bagList.size
        }
    }



    private fun initDate() {
        bagList.add(CouponInfo(R.drawable.orders, "Universal Coupon","2023.12.30","25","Available over 50."))
        bagList.add(CouponInfo(R.drawable.orders, "Universal Coupon","2023.12.30","25","Available over 50."))
    }
}