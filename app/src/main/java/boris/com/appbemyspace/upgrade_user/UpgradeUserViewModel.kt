package boris.com.appbemyspace.upgrade_user

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import boris.com.appbemyspace.data.model.Address
import boris.com.appbemyspace.data.model.EmptyResultDataModel
import boris.com.appbemyspace.data.model.Individual
import boris.com.appbemyspace.data.model.UserUpgradeBody
import boris.com.appbemyspace.data.network.BeMyServiceApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpgradeUserViewModel(val token: String, val username: String) : ViewModel(), Observable {


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _isUpgradeUser = MutableLiveData<EmptyResultDataModel>()
    val isUpgradeUser: LiveData<EmptyResultDataModel> get() = _isUpgradeUser


    @Bindable
    val getCityCountry = MutableLiveData<String>()

    @Bindable
    val getAddress = MutableLiveData<String>()

    @Bindable
    val getZipcode = MutableLiveData<String>()


    val getBirthDate = MutableLiveData<String>()


    fun upgradeApply() {
        println("HERE")
        uiScope.launch {

            val address = Address(
                getCityCountry.value!!.split(",").get(0),
                getCityCountry.value!!.split(",").get(1),
                getZipcode.value!!
            )
            val individual = Individual(address, getBirthDate.value!!)
            val userUpgradeUserInfo = UserUpgradeBody(individual, username)
            val upgradeUser = BeMyServiceApiService.ApiService.retrofitService.upgradeUser(
                "application/json", token, userUpgradeUserInfo
            )

            try {

                _isUpgradeUser.value = upgradeUser.await()
                println("********* " + isUpgradeUser.value!!.code)


            } catch (t: Throwable) {
                //_errorMessage.value = t.message
                println("***************** " + t.localizedMessage)
                println("***************** " + t.message)


            }
        }


    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}