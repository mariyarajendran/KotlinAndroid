package absa.cgs.com.utils

import absa.cgs.com.di.annotation.PerActivity
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import javax.inject.Inject
import javax.inject.Singleton

@PerActivity
class CommonUtils @Inject constructor(private val activity: Activity) {


    fun showToastSmall(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun navigationRoutes(routingClass: Class<*>) {
        val intent = Intent(activity, routingClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)
    }

    fun showToastLong(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }


}