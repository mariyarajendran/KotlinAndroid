package absa.cgs.com.ui.screens.register.model

import absa.cgs.com.di.annotation.PerActivity
import javax.inject.Inject

@PerActivity
class ChargePojo @Inject constructor() {


    var radioButtonTitleText: String = ""
        get() = field
        set(value) {
            field = value
        }
    var chargeAmount: String = ""
        get() = field
        set(value) {
            field = value
        }
    var chargerHintTextToDisplay: String = ""
        get() = field
        set(value) {
            field = value
        }
}