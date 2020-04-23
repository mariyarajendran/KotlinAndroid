package absa.cgs.com.ui.screens.base

import absa.cgs.com.kotlinplayground.R
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

interface BaseMvpView {
    fun hideProgressLoadingDialog()
    fun navigationRoutes(routingClass: Class<*>)
    fun showToastShort(message: String)
    fun showToastLong(message: String)
    fun progressLoadingBar(): ProgressDialog

    fun onClickListeners()
    fun init()

    fun getAuthTokenFromSession(): String

    fun getUserIdFronSession(): String


}