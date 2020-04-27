package absa.cgs.com.utils

import javax.inject.Singleton


class SingletonUtils {

    private object HOLDER {
        val INSTANCE = SingletonUtils()
    }

    companion object {
        val instance: SingletonUtils by lazy { HOLDER.INSTANCE }
    }

    var authToken = ""
    var userId = ""
    var expenseId = ""
    var expenseAmount = ""
    var expenseComment = ""
    var expensetype = ""
    var expenseDate = ""
    val IMAGE_URL = "http://192.168.43.147/cablesoft/api/"
}