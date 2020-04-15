package absa.cgs.com.ui.screens.apis.readexpenseapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter


interface IReadExpenseListener<View : ReadExpenseView> : BaseMvpPresenter<View> {

    fun readExpenseData()

}
