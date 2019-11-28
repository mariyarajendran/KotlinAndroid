package absa.cgs.com.screens.mainbaseactivity

interface MainView {
    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)
}