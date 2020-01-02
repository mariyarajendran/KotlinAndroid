package absa.cgs.com.ui.screens.register

import absa.cgs.com.ui.screens.base.BaseMvpView
import absa.cgs.com.ui.screens.register.model.RadioButtonChargeModel
import absa.cgs.com.ui.screens.register.model.RadioButtonDataModel

interface RegistrationView : BaseMvpView {

    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)

    fun showDialogMaleOrFemale()

    fun onRadioButtonClickedListener(radioButtonListDataModel: List<RadioButtonDataModel>, title: String)

    fun onRadioButtonChargeListener(radioButtonListDataModel: List<RadioButtonDataModel>, radioButtonChargeModel: RadioButtonChargeModel)


    //boxDetails
    fun getBoxName(): String

    fun getBoxNumber(): String
    fun getBoxType(): String
    fun getSmartCardNo(): String


}