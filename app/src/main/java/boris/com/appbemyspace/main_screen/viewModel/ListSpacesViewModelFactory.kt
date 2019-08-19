package boris.com.appbemyspace.main_screen.listSpaces.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListSpacesViewModelFactory (
    private val token : String) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListSpacesViewModel::class.java)) {
                return ListSpacesViewModel(token) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
}