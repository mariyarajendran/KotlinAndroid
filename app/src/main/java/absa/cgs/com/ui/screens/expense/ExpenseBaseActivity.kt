package absa.cgs.com.ui.screens.expense

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.expense.expensechildfragment.addexpense.AddExpenseFragment
import absa.cgs.com.ui.screens.expense.expensechildfragment.expensedetails.ExpenseDetailsFragment
import android.os.Bundle


class ExpenseBaseActivity : BaseActivity(), ExpenseView {


    private val fragmentManager = supportFragmentManager
    private val expenseDetailsFragment = ExpenseDetailsFragment()
    private val addExpenseFragment = AddExpenseFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_base)
        setActionBarTitle(R.string.ExpenseDeatilsString)
        init()
    }

    override fun init() {
        changeFragment(0)

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.expenseFragmentLayout)
        if (fragment !is OnBackPressedListner || !(fragment as OnBackPressedListner).onBackPressed()) {

        }
    }

    override fun setActionBarTitle(string: Int) {
        setTitle(string)
    }

    override fun changeFragment(position: Int) {
        when (position) {
            0 -> {
                setActionBarTitle(R.string.ExpenseDeatilsString)
                ///
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.expenseFragmentLayout, expenseDetailsFragment)
                fragmentTransaction.commit()
            }

            1 -> {
                setActionBarTitle(R.string.AddExpenseString)
                //
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.expenseFragmentLayout, addExpenseFragment)
                fragmentTransaction.commit()
            }
        }
    }

    interface OnBackPressedListner {
        fun onBackPressed(): Boolean
    }

}
