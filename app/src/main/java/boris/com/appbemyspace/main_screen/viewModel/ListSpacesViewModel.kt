package boris.com.appbemyspace.main_screen.listSpaces.viewModel


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import boris.com.appbemyspace.data.model.SearchData
import boris.com.appbemyspace.data.model.SpaceInfo
import boris.com.appbemyspace.data.network.BeMyServiceApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ListSpacesViewModel (
        token : String): ViewModel(),Observable {

    private var viewModelJob  = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val token = token


    private var _placeList  = MutableLiveData<List<SpaceInfo>> ()
    val placelist : LiveData<List<SpaceInfo>> get () = _placeList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    private val _spaceNameList = MutableLiveData<List<String>> ()
    val spaceNameList : LiveData<List<String>> get() = _spaceNameList


    @Bindable
    val getSearchValue = MutableLiveData<String> ( )

    val searchData = SearchData("")

    init {

        getPlaces()
    }

    fun getPlaces ()
    {

        uiScope.launch {
            val places = BeMyServiceApiService.ApiService.retrofitService.getSpaceList("application/json",token,searchData)
            try {
                _placeList.value= places.await().payload
                _spaceNameList.value = placelist.value!!.map {
                    it.address
                }
                println("***************** "+ _placeList.value!!.get(0).address)

            }catch (t:Throwable)
            {
                _errorMessage.value = t.message
                println("***************** "+ t.message)

            }
        }
    }





    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }




}