package absa.cgs.com.utils

import absa.cgs.com.di.annotation.PerActivity
import android.app.Activity
import android.widget.Toast
import javax.inject.Inject


@PerActivity
class CommonUtils @Inject constructor(private val activity: Activity) {


    fun showToastSmall(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


    fun showToastLong(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }


}