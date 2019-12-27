package absa.cgs.com.ui.screens.register

import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.ui.screens.mainbaseactivity.Model.NavigationDataModel
import absa.cgs.com.ui.screens.register.model.RadioButtonDataModel
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.DialogUtils
import javax.inject.Inject


class RegistrationPresenter<View : RegistrationView> @Inject constructor(var commonUtils: CommonUtils,
                                                                         var dialogUtils: DialogUtils) : BasePresenter<View>(), RegistrationPresenterListener<View>, DialogUtils.onRadioButtonEventListener {

    private var radioButtonDataModelList: List<RadioButtonDataModel>? = null

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


}