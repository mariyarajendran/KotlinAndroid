package absa.cgs.com.ui.screens.register

import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.ui.screens.mainbaseactivity.Model.NavigationDataModel
import absa.cgs.com.ui.screens.mainbaseactivity.OnListItemClickInterface
import absa.cgs.com.ui.screens.mainbaseactivity.adapter.DrawerListAdapter
import absa.cgs.com.ui.screens.register.adapter.BoxDetailAdapter
import absa.cgs.com.ui.screens.register.callbacks.OnItemDeleteCallBack
import absa.cgs.com.ui.screens.register.model.BoxDetailsDataModel
import absa.cgs.com.ui.screens.register.model.RadioButtonDataModel
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.DialogUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_kotlin_play_ground.*
import javax.inject.Inject


class RegistrationPresenter<View : RegistrationView> @Inject constructor(var commonUtils: CommonUtils,
                                                                         var dialogUtils: DialogUtils) : BasePresenter<View>(), RegistrationPresenterListener<View>, DialogUtils.onRadioButtonEventListener, OnItemDeleteCallBack {


    private var radioButtonDataModelList: List<RadioButtonDataModel>? = null
    private var boxDetailAdapter: BoxDetailAdapter? = null
    val boxDetailsDataModel: MutableList<BoxDetailsDataModel> = ArrayList()
    fun showHomeDialog() {
        radioButtonDataModelList = listOf(
                RadioButtonDataModel("Home"),
                RadioButtonDataModel("Home"),
                RadioButtonDataModel("Owned"),
                RadioButtonDataModel("Rental")
        )
        dialogUtils.radioButtonAlertDialog(radioButtonDataModelList!!, this)
    }

    fun showBillTypeDialog() {
        radioButtonDataModelList = listOf(
                RadioButtonDataModel("Bill"),
                RadioButtonDataModel("Bill Type"),
                RadioButtonDataModel("Prepaid"),
                RadioButtonDataModel("Postpaid")
        )
        dialogUtils.radioButtonAlertDialog(radioButtonDataModelList!!, this)
    }

    fun showBoxTypeDialog() {
        radioButtonDataModelList = listOf(
                RadioButtonDataModel("Box"),
                RadioButtonDataModel("Box Type"),
                RadioButtonDataModel("HD"),
                RadioButtonDataModel("SD")
        )
        dialogUtils.radioButtonAlertDialog(radioButtonDataModelList!!, this)
    }

    override fun onRadioTitleListener(radioButtonListDataModel: List<RadioButtonDataModel>, title: String) {
        getBaseMvpVieww().onRadioButtonClickedListener(radioButtonListDataModel, title)
    }

    fun addBoxDetails(recyclerViewBoxDetails: RecyclerView) {
        boxDetailsDataModel.add(BoxDetailsDataModel(getBaseMvpVieww().getBoxName(), getBaseMvpVieww().getBoxNumber(), getBaseMvpVieww().getBoxType(), getBaseMvpVieww().getSecurityDeposite()))
        boxDetailAdapter?.notifyNewData(boxDetailsDataModel)
    }

    fun setBoxDetailRecyclerAdapter(recyclerViewBoxDetails: RecyclerView) {
        setRecyclerView(recyclerViewBoxDetails)
    }


    private fun setRecyclerView(recyclerViewBoxDetails: RecyclerView) {
        boxDetailAdapter = BoxDetailAdapter(getActivityy(), boxDetailsDataModel, this)
        val mLayoutManager = LinearLayoutManager(getActivityy())
        recyclerViewBoxDetails.setLayoutManager(mLayoutManager)
        recyclerViewBoxDetails.setItemAnimator(DefaultItemAnimator())
        recyclerViewBoxDetails.adapter = boxDetailAdapter
    }

    override fun onItemDeleteListener(position: Int) {
        boxDetailsDataModel.removeAt(position)
        boxDetailAdapter?.notifyNewData(boxDetailsDataModel)
    }


}