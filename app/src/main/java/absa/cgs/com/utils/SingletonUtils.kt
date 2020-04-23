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
}