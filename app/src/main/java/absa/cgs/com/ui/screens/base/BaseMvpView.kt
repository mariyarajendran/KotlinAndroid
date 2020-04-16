package absa.cgs.com.ui.screens.base

interface BaseMvpView {
    fun showLoadingDialog()
    fun hideLoadingDialgo()
    fun navigationRoutes(routingClass: Class<*>)
    fun showToastShort(message: String)
    fun showToastLong(message: String)
}