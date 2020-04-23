package absa.cgs.com.ui.screens.mainbaseactivity


import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.ui.screens.mainbaseactivity.Model.NavigationDataModel
import javax.inject.Inject


class MainPresenter<View : MainView> @Inject constructor(var mainInteractor: MainInteractor) : BasePresenter<View>(), MainPresenterListener<View>, MainInteractor.onFirstEventTriggerListener {

    // var mNavigationItems: ArrayList<String>? = null

    private var navigationDataModelList: List<NavigationDataModel>? = null

    override fun getDataFromServer() {
        val mainRequest = MainRequest(0, "")
        mainInteractor.firstTriggerEvent(mainRequest, this)
    }

    override fun onSuccessInteractListener(message: String) {
        getBaseMvpVieww().onSuccessResponse(message)
    }

    override fun onFailureInteractListener(error: String) {
        getBaseMvpVieww().onFailureResponse(error)
    }

    fun addDrawerArrayData() {
        navigationDataModelList = listOf(
                NavigationDataModel("Reports"),
                NavigationDataModel("Expense"),
                NavigationDataModel("Online Transactions"),
                NavigationDataModel("Collection Agents"),
                NavigationDataModel("Customer Queries"),
                NavigationDataModel("Language"),
                NavigationDataModel("Forgot Password"),
                NavigationDataModel("Privacy Policy"),
                NavigationDataModel("Logout"))
        getBaseMvpVieww().addNavigationDrawerArrayData(navigationDataModelList!!)
    }


}