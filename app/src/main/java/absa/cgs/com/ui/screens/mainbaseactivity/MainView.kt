package absa.cgs.com.ui.screens.mainbaseactivity

import absa.cgs.com.ui.screens.base.BaseMvpView
import absa.cgs.com.ui.screens.mainbaseactivity.Model.NavigationDataModel
import java.util.ArrayList

interface MainView : BaseMvpView {
    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)
    fun getStringCheck(): String
    fun addNavigationDrawerArrayData(navigationDataArray: List<NavigationDataModel>)
    fun initSessionVaraiables()

}