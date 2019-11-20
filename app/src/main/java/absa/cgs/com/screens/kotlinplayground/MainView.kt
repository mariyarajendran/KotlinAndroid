package absa.cgs.com.screens.kotlinplayground

interface MainView {
    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)
}