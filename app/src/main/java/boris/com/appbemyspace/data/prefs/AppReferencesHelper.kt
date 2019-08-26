package boris.com.appbemyspace.data.prefs

interface AppReferencesHelper {

    fun saveCurrentUserToken(token:String)
    fun getCurrentUserToken():String?
    fun saveUserName(userName: String)
    fun getUsername():String?
    fun saveUserState(state: Boolean)
    fun getUserState(): Boolean
}