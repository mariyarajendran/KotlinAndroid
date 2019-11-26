package absa.cgs.com.screens.MainBaseActivity

interface MainView {
    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)
}