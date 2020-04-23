package absa.cgs.com.ui.screens.apis.addexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface AddExpenseView : BaseMvpView {

    fun onSuccessAddExpenseResponse(message: String)
    fun onFailureAddExpenseResponse(error: String)

    fun getExpenseAmount(): String
    fun getExpenseComment(): String
    fun getExpenseType(): String
    fun getExpenseUserDate(): String
    fun getUserID(): String

    fun postAddExpenseData()

    fun navigateToGetExpenseDetails()

    fun clearAllExpenseDetails()


}