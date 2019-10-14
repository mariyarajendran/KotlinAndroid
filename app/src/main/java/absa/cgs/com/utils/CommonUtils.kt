package absa.cgs.com.utils

import android.content.Context
import android.widget.Toast

class CommonUtils(val context: Context) {


    fun showToastSmall(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}