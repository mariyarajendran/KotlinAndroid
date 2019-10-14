package absa.cgs.com.kotlinplayground

interface MainView {
    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)
}