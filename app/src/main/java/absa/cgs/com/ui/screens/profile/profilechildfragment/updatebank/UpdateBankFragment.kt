package absa.cgs.com.ui.screens.profile.profilechildfragment.updatebank


import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfilePresenter
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfileView
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.apis.updatebankapicall.UpdateBankPresenter
import absa.cgs.com.ui.screens.apis.updatebankapicall.UpdateBankView
import absa.cgs.com.ui.screens.apis.updateimagesapicall.UpdateImagePresenter
import absa.cgs.com.ui.screens.apis.updateimagesapicall.UpdateImageView
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.profile.ProfileBaseActivity
import absa.cgs.com.utils.CommonEnumUtils
import absa.cgs.com.utils.DialogUtils
import absa.cgs.com.utils.ImageCompression
import absa.cgs.com.utils.SingletonUtils
import absa.cgs.com.utils.enums.DialogEnum
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.updatebankfragment.*
import java.io.IOException
import javax.inject.Inject
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import android.os.StrictMode


class UpdateBankFragment : BaseFragment(), ProfileBaseActivity.OnBackPressedListner, ReadProfileView, UpdateBankView, UpdateImageView, DialogUtils.OnDialogListeners {


    lateinit var profileBaseActivity: ProfileBaseActivity

    @Inject
    lateinit var readProfilePresenter: ReadProfilePresenter<ReadProfileView>

    @Inject
    lateinit var updateBankPresenter: UpdateBankPresenter<UpdateBankView>


    @Inject
    lateinit var updateImagePresenter: UpdateImagePresenter<UpdateImageView>

    private var readProfileResponseModel: ReadProfileResponseModel? = null

    private val GALLERY = 1
    private val CAMERA = 2

    lateinit var currentPhotoPath: String
    lateinit var mTempCameraPhotoFile: File

    var imageTagString: String = ""
    var imageBase64String: String = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
        readProfilePresenter.attachView(activity!!, this)
        updateBankPresenter.attachView(activity!!, this)
        updateImagePresenter.attachView(activity!!, this)
        hitGetProfileCall()


    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.updatebankfragment, container, false)
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
        this.readProfileResponseModel = readProfileResponseModel
        updateBankAccountNoEd.setText(commonUtils.cutNull(readProfileResponseModel.bank_details.bank_account_number))
        updateBankIfscCodeEd.setText(commonUtils.cutNull(readProfileResponseModel.bank_details.bank_ifsc_code))
        updateBankAccountHolderNameEd.setText(commonUtils.cutNull(readProfileResponseModel.bank_details.bank_act_holder_name))

        handlePanStatus(commonUtils.cutNull(readProfileResponseModel.bank_details.pan_image_status))
        handleAddressStatus(commonUtils.cutNull(readProfileResponseModel.bank_details.address_proof_status))
    }

    override fun onFailureReadProfileResponse(error: String) {

    }

    override fun onClickListeners() {
        super.onClickListeners()
        updateProfileSaveBtn.setOnClickListener {
            updateBankPresenter.validateBankDatas()
        }

        updateBankPanCardPhotoViewImg.setOnClickListener {
            val args = Bundle()
            args.putString(CommonEnumUtils.PAN.toString(), commonUtils.cutNull(readProfileResponseModel!!.bank_details.pan_image))
            profileBaseActivity.changeFragment(3, args)
        }

        updateBankAddressProofPhotoViewImg.setOnClickListener {
            val args = Bundle()
            args.putString(CommonEnumUtils.ADDRESS.toString(), commonUtils.cutNull(readProfileResponseModel!!.bank_details.address_proof_image))
            profileBaseActivity.changeFragment(3, args)
        }

        updatePanCardUploadImg.setOnClickListener {
            imageTagString = CommonEnumUtils.PAN.toString()
            showImageUploadDialog()
        }

        updateBankAddressProofPhotoUploadImg.setOnClickListener {
            imageTagString = CommonEnumUtils.ADDRESS.toString()
            showImageUploadDialog()
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
    }

    private fun enableColorForPanDocument() {
        updateBankPanCardHintTv.setTextColor(activity!!.resources.getColor(R.color.colorGreen))
        updateBankPanCardPhotoHintTv.setTextColor(activity!!.resources.getColor(R.color.colorGreen))
        updateBankPanCardPhotoViewImg.setColorFilter(ContextCompat.getColor(activity!!, R.color.colorGreen))
        updatePanCardUploadImg.setColorFilter(ContextCompat.getColor(activity!!, R.color.colorGreen))
    }

    private fun enableColorForAddressDocument() {
        updateBankAddressProofHintTv.setTextColor(activity!!.resources.getColor(R.color.colorGreen))
        updateBankAddressProofHintFullTv.setTextColor(activity!!.resources.getColor(R.color.colorGreen))
        updateBankAddressProofPhotoViewImg.setColorFilter(ContextCompat.getColor(activity!!, R.color.colorGreen))
        updateBankAddressProofPhotoUploadImg.setColorFilter(ContextCompat.getColor(activity!!, R.color.colorGreen))
    }

    private fun disableColorForPanDocument() {
        updateBankPanCardHintTv.setTextColor(activity!!.resources.getColor(R.color.colorSecondartDark))
        updateBankPanCardPhotoHintTv.setTextColor(activity!!.resources.getColor(R.color.colorSecondartDark))
        updateBankPanCardPhotoViewImg.setColorFilter(ContextCompat.getColor(activity!!, R.color.colorSecondartDark))
        updatePanCardUploadImg.setColorFilter(ContextCompat.getColor(activity!!, R.color.colorSecondartDark))
    }

    private fun disableColorForAddressDocument() {
        updateBankAddressProofHintTv.setTextColor(activity!!.resources.getColor(R.color.colorSecondartDark))
        updateBankAddressProofHintFullTv.setTextColor(activity!!.resources.getColor(R.color.colorSecondartDark))
        updateBankAddressProofPhotoViewImg.setColorFilter(ContextCompat.getColor(activity!!, R.color.colorSecondartDark))
        updateBankAddressProofPhotoUploadImg.setColorFilter(ContextCompat.getColor(activity!!, R.color.colorSecondartDark))
    }

    override fun getUserId(): String {
        return SingletonUtils.instance.userId
    }

    override fun getAccountNumber(): String {
        return updateBankAccountNoEd.text.toString()
    }

    override fun getIfscCode(): String {
        return updateBankIfscCodeEd.text.toString()
    }

    override fun getAccountHolderName(): String {
        return updateBankAccountHolderNameEd.text.toString()
    }

    override fun navigateToMainScreen() {
        activity!!.finish()
    }

    override fun postBankData() {
        progressLoadingBar()
        updateBankPresenter.postUpdateBankApiCall()
    }


    override fun getImage(): String {
        return imageBase64String
    }

    override fun getImageTag(): String {
        return imageTagString
    }

    override fun postImageData() {
        progressLoadingBar()
        updateImagePresenter.postUpdateImageApiCall()
    }

    private fun handlePanStatus(status: String) {
        when (status) {
            "Y" -> {
                enableColorForPanDocument()
            }

            "N" -> {
                disableColorForPanDocument()
            }
            else -> {
                disableColorForPanDocument()
            }
        }
    }

    private fun handleAddressStatus(status: String) {
        when (status) {
            "Y" -> {
                enableColorForAddressDocument()
            }

            "N" -> {
                disableColorForAddressDocument()
            }
            else -> {
                disableColorForAddressDocument()
            }
        }
    }

    override fun showImageUploadDialog() {
        dialogUtils.showImageUploadDialog(activity!!, DialogEnum.GALLERY.toString(), this)
    }


    override fun onCameraPressedListener(dialog: Dialog, enumString: String) {
        chooseImageFromCamera()
    }

    override fun onGalleryPressedListener(dialog: Dialog, enumString: String) {
        chooseImageFromGallery()
    }

    override fun chooseImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    override fun chooseImageFromCamera() {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity!!.packageManager) != null) {
            val exportDir = File(Environment.getExternalStorageDirectory(), "TempFolder")
            if (!exportDir.exists()) {
                exportDir.mkdirs()
            }

            val fileName = "/" + UUID.randomUUID().toString().replace("-", "") + ".jpg"
            mTempCameraPhotoFile = File(exportDir, fileName)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTempCameraPhotoFile))
            startActivityForResult(takePictureIntent, CAMERA)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY) {
                if (data != null) {
                    var contentURI: Uri
                    var file: File
                    try {
                        contentURI = data.data!!
                        file = File(commonUtils.getRealPathFromURI(context!!, contentURI))

                        var imagePaths: String
                        val task = ImageCompression(context)
                        task.execute(file.path)
                        imagePaths = task.get()
                        imageBase64String = imagePaths
                        postImageData()
                    } catch (e: IOException) {
                        e.printStackTrace()

                    }

                }
            } else if (requestCode == CAMERA) {
                try {
                    var contentURI: Uri
                    contentURI = Uri.fromFile(mTempCameraPhotoFile)
                    mTempCameraPhotoFile = File(commonUtils.getRealPathFromURI(context!!, contentURI))

                    var imagePaths: String
                    val task = ImageCompression(context)
                    task.execute(mTempCameraPhotoFile.path)
                    imagePaths = task.get()
                    imageBase64String = imagePaths
                    postImageData()

                } catch (e: IOException) {
                    e.printStackTrace()

                }
            }
        }

    }


}