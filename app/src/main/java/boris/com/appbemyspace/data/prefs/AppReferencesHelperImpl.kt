package boris.com.appbemyspace.data.prefs

import android.content.Context

class AppReferencesHelperImpl (context:Context): AppReferencesHelper {


    private val NAME = "BEMYSPACE_APP_VALUES"
    private val MODE = Context.MODE_PRIVATE

    private val PREF_KEY_CURRENT_USER_TOKEN = "PREF_KEY_CURRENT_USER_TOKEN"
    private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
    private val PREF_KEY_CURRENT_USER_STATE = "PREF_KEY_CURRENT_USER_STATE"



    private var mPrefs = context.getSharedPreferences(NAME, MODE)
    private var editor = mPrefs.edit()

    override fun saveCurrentUserToken(token: String) {
        editor.putString(PREF_KEY_CURRENT_USER_TOKEN,token)
        editor.commit()
    }

    override fun getCurrentUserToken(): String? {
        return  mPrefs.getString(PREF_KEY_CURRENT_USER_TOKEN,null)    }

    override fun saveUserName(userName: String) {
        editor.putString(PREF_KEY_CURRENT_USER_NAME,userName)
        editor.commit()    }

    override fun getUsername(): String? {
        return  mPrefs.getString(PREF_KEY_CURRENT_USER_NAME,null)
    }


    override fun saveUserState(state: Boolean) {
        editor.putBoolean(PREF_KEY_CURRENT_USER_STATE, state)
    }

    override fun getUserState(): Boolean {
        return mPrefs.getBoolean(PREF_KEY_CURRENT_USER_STATE, false)
    }

}