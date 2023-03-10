package app.foodfinderapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.foodfinderapp.login.model.PasswordLoginArgs
import app.foodfinderapp.login.model.UserArgs
import app.foodfinderapp.login.repository.Repository
//参考书本P545、619
class UserViewModel: ViewModel() {
    private val codeLiveData = MutableLiveData<String>()

    private val phoneLoginLiveData = MutableLiveData<UserArgs>()

    private val locationLiveData = MutableLiveData<String>()

    private val passwordLoginLiveData = MutableLiveData<PasswordLoginArgs>()



    //验证码
    val backInfo = Transformations.switchMap(codeLiveData){ code ->
        Repository.sendCode(code)
    }


    fun resultStatus(phone: String) {
        codeLiveData.value = phone
    }

//    手机登录注册

    val phoneLiveData = Transformations.switchMap(phoneLoginLiveData){ loginPhone ->
        Repository.userLogin(loginPhone.Phone, loginPhone.code, loginPhone.cookie)
    }


    fun resultPhoneCode(phone: String, email: String, code: String, cookie: String) {
        phoneLoginLiveData.value = UserArgs(phone, email, code, cookie)
    }

    // 地址
    val locationData = Transformations.switchMap(locationLiveData){ token ->
        Repository.getLocation(token)
    }

    fun resultLocation(token: String){
        locationLiveData.value = token
    }

    //密码登录
    val passwordLogin = Transformations.switchMap(passwordLoginLiveData){ passwordLogin ->
        Repository.getPasswordLogin(passwordLogin.Phone, passwordLogin.password)
    }

    fun resultPasswordLocation(phone: String, password: String, Email: String){
        passwordLoginLiveData.value = PasswordLoginArgs(phone, password, Email)
    }

    //修改信息
//    fun reviseAddress = Transformations.switchMap(reviseData){  reviseData->
//        Repository.reviseAddress(reviseData.message,reviseData.code)
//    }
//
//    fun resultReviseAddress(){
//        reviseData.value =
//    }







}