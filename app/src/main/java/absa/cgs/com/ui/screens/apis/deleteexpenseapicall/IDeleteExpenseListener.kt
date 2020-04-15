package absa.cgs.com.ui.screens.apis.deleteexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface IDeleteExpenseListener<View : DeleteExpenseView> : BaseMvpPresenter<View> {
    fun postDeleteExpenseApiCall()
}