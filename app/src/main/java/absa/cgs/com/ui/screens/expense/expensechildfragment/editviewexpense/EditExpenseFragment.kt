package absa.cgs.com.ui.screens.expense.expensechildfragment.editviewexpense

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.UpdateExpensePresenter
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.UpdateExpenseView
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.expense.ExpenseBaseActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.editexpensefragment.*
import javax.inject.Inject

class EditExpenseFragment : BaseFragment(), UpdateExpenseView, ExpenseBaseActivity.OnBackPressedListner {
    
    lateinit var expenseBaseActivity: ExpenseBaseActivity
    var expenseId: String? = ""
    var editAndCancelBool: Boolean? = true

    @Inject
    lateinit var updateExpensePresenter: UpdateExpensePresenter<UpdateExpenseView>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
        updateExpensePresenter.attachView(activity!!, this)

    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        fragmentComponent().inject(this)
        return inflater.inflate(R.layout.editexpensefragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    override fun init() {
        expenseBaseActivity = activity as ExpenseBaseActivity
        setAllExpenseData()
        setExpenseDisable()
        expenseSetOnClickListener()
    }

    override fun onSuccessAddExpenseResponse(message: String) {

    }

    override fun onFailureAddExpenseResponse(error: String) {

    }

    override fun getExpenseAmount(): String {
        return commonUtils.cutNull(editExpenseExpenseAmountEd.text.toString())
    }

    override fun getExpenseComment(): String {
        return commonUtils.cutNull(editExpenseExpenseCommentEd.text.toString())
    }

    override fun getExpenseType(): String {
        return commonUtils.cutNull(editExpenseExpenseTypeEd.text.toString())
    }

    override fun getExpenseUserDate(): String {
        return commonUtils.cutNull(editExpenseExpenseDateEd.text.toString())
    }

    override fun getExpenseID(): String {
        return expenseId!!
    }

    override fun onBackPressed(): Boolean {
        var args = Bundle()
        expenseBaseActivity.changeFragment(0, args)
        return false
    }

    override fun setAllExpenseData() {
        if (arguments != null) {
            var expenseAmount = arguments!!.getString("expenseAmount")
            var expenseComment = arguments!!.getString("expenseComment")
            var expensetype = arguments!!.getString("expensetype")
            var expenseDate = arguments!!.getString("expenseDate")
            expenseId = arguments!!.getString("expenseId")

            if (expenseAmount != null) {
                editExpenseExpenseAmountEd.setText(commonUtils.cutNull(expenseAmount))
            }
            if (expenseComment != null) {
                editExpenseExpenseCommentEd.setText(commonUtils.cutNull(expenseComment))
            }
            if (expensetype != null) {
                editExpenseExpenseTypeEd.setText(commonUtils.cutNull(expensetype))
            }
            if (expenseDate != null) {
                editExpenseExpenseDateEd.setText(commonUtils.cutNull(expenseDate))
            }

        }
    }

    override fun onResume() {
        super.onResume()
        setAllExpenseData()
    }

    override fun expenseSetOnClickListener() {
        editExpenseEditTv.setOnClickListener {
            when (editAndCancelBool) {
                true -> {
                    editExpenseEditTv.text = (resources.getString(R.string.ExpenseCancelString))
                    setExpeneEnable()
                    editAndCancelBool = false
                }

                false -> {
                    editExpenseEditTv.text = (resources.getString(R.string.ExpenseEditString))
                    setExpenseDisable()
                    editAndCancelBool = true
                }

            }
        }

        editExpenseSaveBtn.setOnClickListener {
            updateExpensePresenter.validateEditExpense()
        }
    }


    override fun setExpeneEnable() {
        editExpenseExpenseAmountEd.isEnabled = true
        editExpenseExpenseCommentEd.isEnabled = true
        editExpenseExpenseTypeEd.isEnabled = true
        editExpenseExpenseDateEd.isEnabled = true
        editExpenseSaveBtn.visibility = View.VISIBLE
        expenseBaseActivity.setActionBarTitle(R.string.EditExpenseDetailsString)
        editExpenseHintTv.text = resources.getText(R.string.EditExpenseDetailsString)

    }

    override fun setExpenseDisable() {
        editExpenseExpenseAmountEd.isEnabled = false
        editExpenseExpenseCommentEd.isEnabled = false
        editExpenseExpenseTypeEd.isEnabled = false
        editExpenseExpenseDateEd.isEnabled = false
        editExpenseSaveBtn.visibility = View.GONE
        expenseBaseActivity.setActionBarTitle(R.string.ExpenseDetailsString)
        editExpenseHintTv.text = resources.getText(R.string.ExpenseDetailsString)
    }

    override fun navigateToGetExpenseDetails() {
        val args = Bundle()
        expenseBaseActivity.changeFragment(0, args)
    }

    override fun postEditExpenseData() {
        progressLoadingBar()
        updateExpensePresenter.postUpdateExpenseApiCall()
    }
}




