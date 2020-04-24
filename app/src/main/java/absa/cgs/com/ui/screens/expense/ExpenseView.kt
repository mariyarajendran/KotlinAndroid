package absa.cgs.com.ui.screens.expense

import absa.cgs.com.ui.screens.base.BaseMvpView
import android.os.Bundle

interface ExpenseView : BaseMvpView {

    fun setActionBarTitle(string: Int)
    fun changeFragment(position: Int, bundle: Bundle)

}