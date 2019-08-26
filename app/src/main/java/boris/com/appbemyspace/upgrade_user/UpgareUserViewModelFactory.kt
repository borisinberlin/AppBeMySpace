package boris.com.appbemyspace.upgrade_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpgareUserViewModelFactory(private val token: String, val username: String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpgradeUserViewModel::class.java)) {
            return UpgradeUserViewModel(token, username) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}