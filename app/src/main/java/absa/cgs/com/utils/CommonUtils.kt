package absa.cgs.com.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast

class CommonUtils(val context: Context) {


    fun showToastSmall(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun navigationRoutes(context: Context, routingClass: Class<*>) {
        val intent = Intent(context, routingClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun showToastLong(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


}