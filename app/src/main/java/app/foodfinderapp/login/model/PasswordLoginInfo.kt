package app.foodfinderapp.login.model

class PasswordLoginInfo(var code: String,val user:DetailUser){
    class DetailUser (val user: User, val token:String, val status:Int)
}