package app.foodfinderapp.login.model

data class User (
    var id: String, var username:String, val email:String, val password:String, val salt:String,
    val userPhoto:String, val phone:String, val state:Int, val joinTime:String,
    val lastLogin:String, val tag:Int, val token:String)