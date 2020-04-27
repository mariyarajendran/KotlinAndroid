package absa.cgs.com.ui.screens.apis.getnomineerelation

import absa.cgs.com.ui.screens.base.BaseMvpPresenter


interface INomineeRelationListener<View : NomineeRelationView> : BaseMvpPresenter<View> {

    fun getNomineeRelationsData()

}
