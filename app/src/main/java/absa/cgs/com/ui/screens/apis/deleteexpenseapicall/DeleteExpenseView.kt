package absa.cgs.com.ui.screens.apis.deleteexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface DeleteExpenseView : BaseMvpView {

    fun onSuccessDeleteExpenseResponse()
    fun onFailureDeleteExpenseResponse(error: String)
    fun hitExpenseDetailsCall()


    fun getExpenseID(): String
}