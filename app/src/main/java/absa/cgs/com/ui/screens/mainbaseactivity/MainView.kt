package absa.cgs.com.ui.screens.mainbaseactivity

interface MainView {
    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)
}