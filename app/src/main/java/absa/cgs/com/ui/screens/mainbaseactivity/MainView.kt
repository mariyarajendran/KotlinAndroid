package absa.cgs.com.ui.screens.mainbaseactivity

import absa.cgs.com.ui.screens.base.BaseMvpView

interface MainView : BaseMvpView {
    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)

    fun getStringCheck(): String
}