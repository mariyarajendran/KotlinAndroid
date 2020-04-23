package absa.cgs.com.ui.screens.apis.readexpenseapicall

import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseResponseModel
import absa.cgs.com.ui.screens.base.BaseMvpView

interface ReadExpenseView : BaseMvpView {
    fun onSuccessReadExpenseResponse(readExpenseResponseModel: ReadExpenseResponseModel)
    fun onFailureReadExpenseResponse(error: String)

    fun getUserID(): String
    fun getSearchKeyword(): String
    fun getPageCount(): String
    fun getFromDate(): String
    fun getToDate(): String

    fun hitExpenseDetailsCall()
    fun expenseDetailsOnClickListener(eventString: String, position: Int, readExpenseResponseModel: ReadExpenseResponseModel)


}
