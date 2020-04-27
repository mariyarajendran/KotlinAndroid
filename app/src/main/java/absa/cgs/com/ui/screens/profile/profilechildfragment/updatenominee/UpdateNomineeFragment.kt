package absa.cgs.com.ui.screens.profile.profilechildfragment.updatenominee

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.getnomineerelation.GetNomineeRelationPresenter
import absa.cgs.com.ui.screens.apis.getnomineerelation.NomineeRelationView
import absa.cgs.com.ui.screens.apis.getnomineerelation.model.NomineeRelationResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfilePresenter
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfileView
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.UpdateNomineePresenter
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.UpdateNomineeView
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.profile.ProfileBaseActivity
import absa.cgs.com.utils.SingletonUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.updatenomineefragment.*
import javax.inject.Inject

class UpdateNomineeFragment : BaseFragment(), ProfileBaseActivity.OnBackPressedListner, ReadProfileView, UpdateNomineeView, NomineeRelationView {


    lateinit var profileBaseActivity: ProfileBaseActivity

    @Inject
    lateinit var readProfilePresenter: ReadProfilePresenter<ReadProfileView>


    @Inject
    lateinit var updateNomineePresenter: UpdateNomineePresenter<UpdateNomineeView>

    @Inject
    lateinit var getNomineeRelationPresenter: GetNomineeRelationPresenter<NomineeRelationView>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
        readProfilePresenter.attachView(activity!!, this)
        updateNomineePresenter.attachView(activity!!, this)
        getNomineeRelationPresenter.attachView(activity!!, this)
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.updatenomineefragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    override fun init() {
        profileBaseActivity = activity as ProfileBaseActivity
        onClickListeners()

    }

    override fun onBackPressed(): Boolean {
        activity!!.finish()
        return true
    }

    override fun onSuccessReadProfileResponse(readProfileResponseModel: ReadProfileResponseModel) {
        updateNomineeNameEd.setText(commonUtils.cutNull(readProfileResponseModel.nominee_details.nominee_name))
        updateNomineeMobileNumberEd.setText(commonUtils.cutNull(readProfileResponseModel.nominee_details.nominee_phone_number))
        updateNomineeAddressEd.setText(commonUtils.cutNull(readProfileResponseModel.nominee_details.nominee_address))
        updateNomineeRelationShipEd.setText(commonUtils.cutNull(readProfileResponseModel.nominee_details.nominee_relation_nominee))
    }

    override fun onFailureReadProfileResponse(error: String) {

    }


    override fun onClickListeners() {
        super.onClickListeners()
        updateProfileSaveBtn.setOnClickListener {
            updateNomineePresenter.validateNomineeDatas()
        }
    }

    override fun hitGetProfileCall() {
        progressLoadingBar()
        readProfilePresenter.readUserProfileData()
    }


    override fun getUserID(): String {
        return SingletonUtils.instance.userId
    }

    override fun onResume() {
        super.onResume()
        hitGetProfileCall()
        hitGetNomineeRelationCall()
    }

    override fun getUserId(): String {
        return SingletonUtils.instance.userId
    }

    override fun getNomineeName(): String {
        return updateNomineeNameEd.text.toString()
    }

    override fun getNomineePhoneNumber(): String {
        return updateNomineeMobileNumberEd.text.toString()
    }

    override fun getNomineeAddress(): String {
        return updateNomineeAddressEd.text.toString()
    }

    override fun getNomineeRelations(): String {
        return updateNomineeRelationShipEd.text.toString()
    }

    override fun navigateToMainScreen() {
        activity!!.finish()
    }

    override fun postNomineeData() {
        progressLoadingBar()
        updateNomineePresenter.postUpdateNomineeApiCall()
    }

    override fun onSuccessGetNomineeResponse(nomineeRelationResponseModel: NomineeRelationResponseModel) {

    }

    override fun onFailureGetNomineeResponse(error: String) {

    }

    override fun hitGetNomineeRelationCall() {
        getNomineeRelationPresenter.getNomineeRelationsData()
    }
}