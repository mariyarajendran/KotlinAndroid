package absa.cgs.com.ui.screens.register

import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.DialogUtils
import javax.inject.Inject


class RegistrationPresenter<View : RegistrationView> @Inject constructor(var commonUtils: CommonUtils,
                                                                         var dialogUtils: DialogUtils) : BasePresenter<View>(), RegistrationPresenterListener<View> {


    fun showGenderDialog() {
        dialogUtils.radioButtonAlertDialog()
    }


}