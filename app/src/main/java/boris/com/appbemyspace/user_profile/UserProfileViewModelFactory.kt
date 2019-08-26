package boris.com.appbemyspace.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserProfileViewModelFactory  (
private val token : String, val username :String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            return UserProfileViewModel(token,username) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}