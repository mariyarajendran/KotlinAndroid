package absa.cgs.com.ui.screens.apis.deleteexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface DeleteExpenseView : BaseMvpView {

    fun onSuccessDeleteExpenseResponse(message: String)
    fun onFailureDeleteExpenseResponse(error: String)


    fun getExpenseID(): String
}