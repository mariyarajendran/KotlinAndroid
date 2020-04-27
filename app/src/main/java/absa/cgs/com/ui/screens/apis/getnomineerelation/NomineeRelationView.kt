package absa.cgs.com.ui.screens.apis.getnomineerelation

import absa.cgs.com.ui.screens.apis.getnomineerelation.model.NomineeRelationResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.base.BaseMvpView

interface NomineeRelationView : BaseMvpView {
    fun onSuccessGetNomineeResponse(nomineeRelationResponseModel: NomineeRelationResponseModel)
    fun onFailureGetNomineeResponse(error: String)

    fun hitGetNomineeRelationCall()
}
