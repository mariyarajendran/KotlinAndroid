package absa.cgs.com.ui.screens.apis.updateexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface IUpdateExpenseListener<View : UpdateExpenseView> : BaseMvpPresenter<View> {
    fun postUpdateExpenseApiCall()
}