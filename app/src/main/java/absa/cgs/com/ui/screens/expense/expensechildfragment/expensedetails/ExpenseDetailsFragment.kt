package absa.cgs.com.ui.screens.expense.expensechildfragment.expensedetails

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.DeleteExpenseView
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.DeleteExpensepresenter
import absa.cgs.com.ui.screens.apis.readexpenseapicall.ReadExpensePresenter
import absa.cgs.com.ui.screens.apis.readexpenseapicall.ReadExpenseView
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseResponseModel
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.expense.ExpenseBaseActivity
import absa.cgs.com.ui.screens.expense.expensechildfragment.expensedetails.adapter.ExpenseDetailsAdapter
import absa.cgs.com.ui.screens.mainbaseactivity.OnListItemClickInterface
import absa.cgs.com.utils.CommonEnumUtils
import absa.cgs.com.utils.DialogUtils
import absa.cgs.com.utils.SingletonUtils
import absa.cgs.com.utils.enums.DialogEnum
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.expensedetailsfragment.*
import javax.inject.Inject

class ExpenseDetailsFragment : BaseFragment(), ExpenseBaseActivity.OnBackPressedListner, ReadExpenseView, DialogUtils.OnDialogPositiveListener, DeleteExpenseView {


    lateinit var expenseBaseActivity: ExpenseBaseActivity

    @Inject
    lateinit var readExpensePresenter: ReadExpensePresenter<ReadExpenseView>

    @Inject
    lateinit var deleteExpensepresenter: DeleteExpensepresenter<DeleteExpenseView>


    private var expenseDetailsAdapter: ExpenseDetailsAdapter? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
        readExpensePresenter.attachView(activity!!, this)
        deleteExpensepresenter.attachView(activity!!, this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.expensedetailsfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseBaseActivity = activity as ExpenseBaseActivity
        init()
    }


    override fun init() {
        onClickListener()
        recyclerViewInit()

    }


    private fun recyclerViewInit() {
        val mLayoutManager = LinearLayoutManager(activity)
        expenseDetailsRcv?.layoutManager = mLayoutManager
        expenseDetailsRcv?.itemAnimator = DefaultItemAnimator()
    }

    private fun onClickListener() {
        addExpenseActionButtonFb.setOnClickListener {
            expenseBaseActivity.changeFragment(1)
        }
    }

    override fun onBackPressed(): Boolean {
        activity!!.finish()
        return true
    }

    override fun onSuccessReadExpenseResponse(readExpenseResponseModel: ReadExpenseResponseModel) {

        expenseDetailsAdapter = ExpenseDetailsAdapter(activity!!, readExpenseResponseModel!!, object : OnListItemClickInterface {
            override fun OnSelectedItemClickListener(title: String, position: Int) {
                expenseDetailsOnClickListener(title, position, readExpenseResponseModel)
            }
        }
        )
        expenseDetailsRcv?.adapter = expenseDetailsAdapter
        expenseDetailsAdapter!!.notifyDataSetChanged()
    }

    override fun onFailureReadExpenseResponse(error: String) {

    }

    override fun expenseDetailsOnClickListener(eventString: String, position: Int, readExpenseResponseModel: ReadExpenseResponseModel) {

        when (eventString) {
            CommonEnumUtils.VIEW.toString() -> {
                showToastLong(readExpenseResponseModel.product_details[position].expense_comment)
            }

            CommonEnumUtils.DELETE.toString() -> {
                SingletonUtils.instance.expenseId = readExpenseResponseModel.product_details[position].expense_id
                dialogUtils.showAlertDialog(activity!!, activity!!.resources.getString(R.string.DialogDeleteString), "", "", DialogEnum.DELETE.toString(), this)
            }
        }
    }


    override fun getUserID(): String {
        return SingletonUtils.instance.userId
    }

    override fun getSearchKeyword(): String {
        return ""
    }

    override fun getPageCount(): String {
        return "0"
    }

    override fun getFromDate(): String {
        return ""
    }

    override fun getToDate(): String {
        return ""
    }

    override fun onResume() {
        super.onResume()
        hitExpenseDetailsCall()
    }

    override fun hitExpenseDetailsCall() {
        progressLoadingBar()
        readExpensePresenter.readExpenseData()
    }

    override fun onDialogPositivePressed(dialog: Dialog, enumString: String) {

        when (enumString) {
            DialogEnum.DELETE.toString() -> {
                progressLoadingBar()
                deleteExpensepresenter.postDeleteExpenseApiCall()
            }
        }

    }

    override fun onSuccessDeleteExpenseResponse() {

    }

    override fun onFailureDeleteExpenseResponse(error: String) {

    }


    override fun getExpenseID(): String {
        return SingletonUtils.instance.expenseId
    }
}