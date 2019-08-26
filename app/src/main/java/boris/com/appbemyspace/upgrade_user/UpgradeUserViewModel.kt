package boris.com.appbemyspace.upgrade_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import boris.com.appbemyspace.data.model.EmptyResultDataModel
import boris.com.appbemyspace.data.network.BeMyServiceApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpgradeUserViewModel(token: String, username: String) : ViewModel() {


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val token = token
    val username = username

    private val _isUpgradeUser = MutableLiveData<EmptyResultDataModel>()
    val isUpgradeUser: LiveData<EmptyResultDataModel> get() = _isUpgradeUser


    fun upgradeUser(): Boolean {
        var result = false
        uiScope.launch {
            val upgadeUser = BeMyServiceApiService.ApiService.retrofitService.upgradeUser(
                "application/json",
                token,
                true,
                username
            )            //BeMyServiceApiService.ApiService.retrofitService.getSpaceList("application/json",token,searchData)
            try {

                _isUpgradeUser.value = upgadeUser.await()
                println("********* " + isUpgradeUser.value!!.code)
                result = true

            } catch (t: Throwable) {
                //_errorMessage.value = t.message
                println("***************** " + t.localizedMessage)
                println("***************** " + t.message)


            }
        }

        return result
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}