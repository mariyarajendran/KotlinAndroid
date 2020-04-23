package absa.cgs.com.utils

import absa.cgs.com.di.annotation.PerActivity
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CommonUtils @Inject constructor(private val context: Context, private val sessionUtils: SessionUtils) {


    fun showToastSmall(message: String) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    fun showToastLong(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun cutNull(format: Any?): String {
        val cut: String
        if (format == null) {
            cut = ""
        } else if (format.toString().trim { it <= ' ' }.equals("", ignoreCase = true)) {
            cut = ""
        } else if (format.toString().trim { it <= ' ' }.equals("null", ignoreCase = true)) {
            cut = ""
        } else {
            cut = format.toString()
        }
        return cut
    }

    fun putLoginSessionData(loginResponseModel: LoginResponseModel): String {
        val gson = Gson()
        val jsonString = gson.toJson(loginResponseModel)
        return jsonString
    }

    fun getLoginSessionData(): LoginResponseModel {
        val gson = Gson()
        val loginResponseModel: LoginResponseModel
        loginResponseModel = gson.fromJson(sessionUtils.loginSessionData, LoginResponseModel::class.java)
        return loginResponseModel
    }

}