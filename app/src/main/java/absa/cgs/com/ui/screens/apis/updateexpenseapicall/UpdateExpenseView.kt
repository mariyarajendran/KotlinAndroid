package absa.cgs.com.ui.screens.apis.updateexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface UpdateExpenseView : BaseMvpView {

    fun onSuccessAddExpenseResponse(message: String)
    fun onFailureAddExpenseResponse(error: String)

    fun getExpenseAmount(): String
    fun getExpenseComment(): String
    fun getExpenseType(): String
    fun getExpenseUserDate(): String
    fun getExpenseID(): String
    fun setAllExpenseData()
    fun setExpeneEnable()
    fun setExpenseDisable()
    fun expenseSetOnClickListener()
    fun navigateToGetExpenseDetails()

    fun postEditExpenseData()

}