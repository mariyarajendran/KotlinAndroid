package absa.cgs.com.ui.screens.apis.addexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface IAddExpenseListener<View : AddExpenseView> : BaseMvpPresenter<View> {
    fun postAddExpenseApiCall()
}