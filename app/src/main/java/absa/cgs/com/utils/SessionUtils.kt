package absa.cgs.com.utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionUtils @Inject constructor(private val preference: SharedPreferences) {


    companion object {
        private val IS_FIRST_RUN_PREF = Pair("loginSessionDataModel", "")
    }


    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    private inline fun SharedPreferences.delete(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.clear()
        editor.apply()
    }


    var loginSessionData: String?
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preference.getString(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        // custom setter to save a preference back to preferences file
        set(value) = preference.edit {
            it.putString(IS_FIRST_RUN_PREF.first, value)
        }

    fun deleteLoginSession() {
        preference.getString(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        preference.delete { }
    }
}