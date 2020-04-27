package absa.cgs.com.ui.screens.profile.profilechildfragment.updateprofile

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfilePresenter
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfileView
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.UpdateProfilePresenter
import absa.cgs.com.ui.screens.apis.updateprofileapicall.UpdateProfileView
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.profile.ProfileBaseActivity
import absa.cgs.com.utils.SingletonUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.updateprofilefragment.*
import javax.inject.Inject

class UpdateProfileFragment : BaseFragment(), ProfileBaseActivity.OnBackPressedListner, ReadProfileView, UpdateProfileView {

    lateinit var profileBaseActivity: ProfileBaseActivity

    @Inject
    lateinit var readProfilePresenter: ReadProfilePresenter<ReadProfileView>

    @Inject
    lateinit var updateProfilePresenter: UpdateProfilePresenter<UpdateProfileView>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
        readProfilePresenter.attachView(activity!!, this)
        updateProfilePresenter.attachView(activity!!, this)
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.updateprofilefragment, container, false)
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
        updateProfileMobileEd.setText(commonUtils.cutNull(readProfileResponseModel.user_details.user_mobile_number))
        updateProfileOfficeMobileEd.setText(commonUtils.cutNull(readProfileResponseModel.user_details.user_office_number))
        updateProfileOwnerNameEd.setText(commonUtils.cutNull(readProfileResponseModel.user_details.user_ower_name))
        updateProfileAgencyNameEd.setText(commonUtils.cutNull(readProfileResponseModel.user_details.user_agency_name))
        updateProfileAddressEd.setText(commonUtils.cutNull(readProfileResponseModel.user_details.user_address))
        updateProfileGstEd.setText(commonUtils.cutNull(readProfileResponseModel.user_details.user_gst_number))
    }

    override fun onFailureReadProfileResponse(error: String) {

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
    }


    override fun onClickListeners() {
        updateProfileSaveBtn.setOnClickListener {
            updateProfilePresenter.validateProfileDatas()
        }
    }

    override fun onSuccessUpdateProfileResponse(message: String) {

    }

    override fun onFailureUpdateProfileResponse(error: String) {

    }

    override fun getUserId(): String {
        return SingletonUtils.instance.userId
    }


    override fun getUserMobileNumber(): String {
        return updateProfileMobileEd.text.toString()
    }

    override fun getUserAddress(): String {
        return updateProfileAddressEd.text.toString()
    }


    override fun getUserOfficeNumber(): String {
        return updateProfileOfficeMobileEd.text.toString()
    }

    override fun getUserOwnerName(): String {
        return updateProfileOwnerNameEd.text.toString()
    }

    override fun getUserAgencyName(): String {
        return updateProfileAgencyNameEd.text.toString()
    }

    override fun getUserGstNumber(): String {
        return updateProfileGstEd.text.toString()
    }

    override fun postUpdateProfileData() {
        progressLoadingBar()
        updateProfilePresenter.postUpdateProfileApiCall()
    }

    override fun navigateToMainScreen() {
        activity!!.finish()
    }


}