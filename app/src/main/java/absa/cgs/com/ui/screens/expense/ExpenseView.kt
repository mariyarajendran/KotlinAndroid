package absa.cgs.com.ui.screens.expense

import absa.cgs.com.ui.screens.base.BaseMvpView

interface ExpenseView : BaseMvpView {

    fun setActionBarTitle(string: Int)
    fun changeFragment(position: Int)

}