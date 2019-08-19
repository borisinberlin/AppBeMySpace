package boris.com.appbemyspace.launch.login

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import boris.com.appbemyspace.data.model.LoggingUser
import boris.com.appbemyspace.data.model.currentUser
import boris.com.appbemyspace.data.network.BeMyServiceApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

 open class LoginViewModel : ViewModel(), Observable {


     private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

     @Bindable
    val getUsername = MutableLiveData<String>()

    @Bindable
    val getPassword = MutableLiveData<String>()


    private val _currentUser = MutableLiveData<currentUser>()
    val currentUser: LiveData<currentUser>
        get() = _currentUser

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob+Dispatchers.Main)


    fun login()
    {

        if(getUsername.value!=null && getPassword.value!=null)
        {
            val userBody = LoggingUser(getUsername.value!!,getPassword.value!!)
           coroutineScope.launch {
                val loggedUser = BeMyServiceApiService.LoginApiService.loginRetrofitService.login(userBody)
                try {
                    _currentUser.value= loggedUser.await()

                }catch (t:Throwable)
                {
                    _errorMessage.value = t.message

                }
            }

        }else
        {
            _errorMessage.value = "REQUIRED USERNAME AND PASSWORD!"
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

     override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
         callbacks.add(callback)
     }

     override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
         callbacks.remove(callback)
     }

 }