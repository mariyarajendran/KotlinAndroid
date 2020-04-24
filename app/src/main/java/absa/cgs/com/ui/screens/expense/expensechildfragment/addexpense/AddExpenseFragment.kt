package absa.cgs.com.ui.screens.expense.expensechildfragment.addexpense

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.addexpenseapicall.AddExpenseView
import absa.cgs.com.ui.screens.apis.addexpenseapicall.AddExpensepresenter
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.expense.ExpenseBaseActivity
import absa.cgs.com.utils.SingletonUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.addexpensefragment.*
import javax.inject.Inject

class AddExpenseFragment : BaseFragment(), AddExpenseView, ExpenseBaseActivity.OnBackPressedListner {


    @Inject
    lateinit var addExpensepresenter: AddExpensepresenter<AddExpenseView>

    lateinit var expenseBaseActivity: ExpenseBaseActivity


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
        addExpensepresenter.attachView(activity!!, this)
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.addexpensefragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    override fun init() {
        expenseBaseActivity = activity as ExpenseBaseActivity
        onClickListeners()
    }


    override fun onSuccessAddExpenseResponse(message: String) {

    }

    override fun onFailureAddExpenseResponse(error: String) {

    }

    override fun getExpenseAmount(): String {
        return commonUtils.cutNull(addExpenseExpenseAmountEd.text.toString())
    }

    override fun getExpenseComment(): String {
        return commonUtils.cutNull(addExpenseExpenseCommentEd.text.toString())

    }

    override fun getExpenseType(): String {
        return commonUtils.cutNull(addExpenseExpenseTypeEd.text.toString())
    }

    override fun getExpenseUserDate(): String {
        return commonUtils.cutNull(addExpenseExpenseDateEd.text.toString())
    }

    override fun getUserID(): String {
        return SingletonUtils.instance.userId
    }

    override fun onClickListeners() {
        addExpenseSaveBtn.setOnClickListener {
            addExpensepresenter.validateAddExpense()
        }
    }

    override fun postAddExpenseData() {
        progressLoadingBar()
        addExpensepresenter.postAddExpenseApiCall()
    }

    override fun navigateToGetExpenseDetails() {
        val args = Bundle()
        expenseBaseActivity.changeFragment(0, args)
    }

    override fun onBackPressed(): Boolean {
        val args = Bundle()
        expenseBaseActivity.changeFragment(0, args)
        return false
    }

    override fun clearAllExpenseDetails() {
        addExpenseExpenseAmountEd.text = null
        addExpenseExpenseCommentEd.text = null
        addExpenseExpenseTypeEd.text = null
        addExpenseExpenseDateEd.text = null
    }


}