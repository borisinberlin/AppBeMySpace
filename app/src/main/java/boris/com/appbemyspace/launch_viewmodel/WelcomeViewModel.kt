package boris.com.appbemyspace.launch_viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class WelcomeViewModel : ViewModel() {

    private val _eventGoToLogin = MutableLiveData<Boolean>()
    val eventGoToLogin: LiveData<Boolean>get() = _eventGoToLogin

    private val _eventGoToSignIn = MutableLiveData<Boolean>()
    val eventGoToSignIn: LiveData<Boolean>get() = _eventGoToSignIn



    fun goToLogin()
    {
        _eventGoToLogin.value = true

    }

    fun goToCreateAccount()
    {
        _eventGoToSignIn.value = true
    }


    override fun onCleared() {
        super.onCleared()
    }
}
