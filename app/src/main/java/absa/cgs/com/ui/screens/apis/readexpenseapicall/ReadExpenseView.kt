package absa.cgs.com.ui.screens.apis.readexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface ReadExpenseView : BaseMvpView {
    fun onSuccessReadExpenseResponse(message: String)
    fun onFailureReadExpenseResponse(error: String)

    fun getUserID(): String
    fun getSearchKeyword(): String
    fun getPageCount(): String
    fun getFromDate(): String
    fun getToDate(): String
}
