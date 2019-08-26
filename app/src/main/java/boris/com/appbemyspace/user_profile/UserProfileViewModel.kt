package boris.com.appbemyspace.user_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import boris.com.appbemyspace.data.model.UserInfo
import boris.com.appbemyspace.data.network.BeMyServiceApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserProfileViewModel(token: String, username :String) : ViewModel() {

    private val _setUsername = MutableLiveData<String>()
    val setUsername : LiveData<String> get() = _setUsername

    private val _setName = MutableLiveData<String>()
    val setName : LiveData<String> get() = _setName

    private val _setEmail = MutableLiveData<String> ()
    val setEmail : LiveData<String> get() = _setEmail

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo :LiveData<UserInfo> get() = _userInfo

    private var viewModelJob  = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val token = token
    val username = username

    init {
        getUserInfo()
    }

    fun getUserInfo()
    {
        uiScope.launch {
            val user =   BeMyServiceApiService.LoginApiService.loginRetrofitService.retriveUser("application/json",token,username)            //BeMyServiceApiService.ApiService.retrofitService.getSpaceList("application/json",token,searchData)
            try {
                _userInfo.value= user.await().user
                _setEmail.value = _userInfo.value!!.email
                _setName.value= _userInfo.value!!.firstName
                _setUsername.value=_userInfo.value!!.username
                println("***************** "+ _userInfo.value!!.email)

            }catch (t:Throwable)
            {
                //_errorMessage.value = t.message
                println("***************** "+ t.localizedMessage)
                println("***************** "+ t.message)

            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}