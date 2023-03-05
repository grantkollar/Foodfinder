package app.foodfinderapp.login.model

data class LocationDetail (val id:Int,val userId: Int, val name: String, val gender: String,
                           val phone: String,
                           val latitude:String, val longitude:String, val province:String,
                           val city:String, val district:String,
                           val street:String, val detailAddr:String, val defaultAddr:Int,
                           val status:Int, val addTime:String)