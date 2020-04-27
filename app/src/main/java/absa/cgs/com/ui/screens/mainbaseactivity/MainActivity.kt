package absa.cgs.com.ui.screens.mainbaseactivity

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.logoutapicall.LogoutPresenter
import absa.cgs.com.ui.screens.apis.logoutapicall.LogoutView
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfilePresenter
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfileView
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.apis.updateimagesapicall.UpdateImagePresenter
import absa.cgs.com.ui.screens.apis.updateimagesapicall.UpdateImageView
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.customer.CustomerFragment
import absa.cgs.com.ui.screens.profile.ProfileFragment
import absa.cgs.com.ui.screens.dashboard.DashboardFragment
import absa.cgs.com.ui.screens.expense.ExpenseBaseActivity
import absa.cgs.com.ui.screens.mainbaseactivity.Model.NavigationDataModel
import absa.cgs.com.ui.screens.mainbaseactivity.adapter.DrawerListAdapter
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
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_kotlin_play_ground.*
import kotlinx.android.synthetic.main.content_dashboardscreen.*
import kotlinx.android.synthetic.main.updatebankfragment.*
import javax.inject.Inject
import javax.inject.Singleton
import androidx.drawerlayout.widget.DrawerLayout
import android.view.View
import com.bumptech.glide.load.resource.bitmap.Rotate
import java.io.File
import java.io.IOException
import java.util.*


class MainActivity : BaseActivity(), MainView, DialogUtils.OnDialogPositiveListener, LogoutView, ReadProfileView, UpdateImageView, DialogUtils.OnDialogListeners {


    private var dashboardDrawerListAdapter: DrawerListAdapter? = null

    @Inject
    lateinit var readProfilePresenter: ReadProfilePresenter<ReadProfileView>


    @Inject
    lateinit var mainPresenter: MainPresenter<MainView>

    @Inject
    lateinit var logoutPresenter: LogoutPresenter<LogoutView>

    @Inject
    lateinit var dialogUtils: DialogUtils

    @Inject
    lateinit var updateImagePresenter: UpdateImagePresenter<UpdateImageView>

    private val GALLERY = 1
    private val CAMERA = 2

    lateinit var mTempCameraPhotoFile: File

    var imageTagString: String = ""
    var imageBase64String: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)
        init()
    }

    override fun init() {
        activityComponent().inject(this)
        mainPresenter.attachView(this, this)
        logoutPresenter.attachView(this, this)
        readProfilePresenter.attachView(this, this)
        updateImagePresenter.attachView(this, this)
        initSessionVaraiables()
        onClickListeners()
        setSupportActionBar(dashboardToolbar)
        supportActionBar?.title = this.resources.getString(R.string.bottom_nav_customer)
        mainPresenter.addDrawerArrayData()
        loadFragment(CustomerFragment())
        contentDashboardBottomnavigationView.selectedItemId = (R.id.bottomNavigationItemCustomer)
        contentDashboardBottomnavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        hitGetProfileCall()
    }

    override fun initSessionVaraiables() {
        getUserIdFronSession()
        getAuthTokenFromSession()
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment
        when (item.itemId) {
            R.id.bottomNavigationItemDashboard -> {
                dashboardToolbar?.title = this.resources.getString(R.string.bottom_nav_dashboard)
                fragment = DashboardFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottomNavigationItemCustomer -> {
                dashboardToolbar?.title = this.resources.getString(R.string.bottom_nav_customer)
                fragment = CustomerFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottomNavigationItemSearch -> {
                dashboardToolbar?.title = this.resources.getString(R.string.bottom_nav_search)
                fragment = DashboardFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottomNavigationItemProfile -> {
                dashboardToolbar?.title = this.resources.getString(R.string.bottom_nav_profile)
                fragment = ProfileFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentDashboardFramelayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun addNavigationDrawerArrayData(navigationDataArray: List<NavigationDataModel>) {
        val toggle = ActionBarDrawerToggle(
                this@MainActivity, dashboard_drawer_layout, dashboardToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        dashboard_drawer_layout?.setDrawerListener(toggle)
        toggle.syncState()
        toggle.drawerArrowDrawable.setColor(resources.getColor(R.color.colorWhite))
        dashboardDrawerListAdapter = DrawerListAdapter(this@MainActivity, navigationDataArray!!, object : OnListItemClickInterface {
            override fun OnSelectedItemClickListener(title: String, position: Int) {
                navigationDrawerOnClick(position, title)
            }
        }
        )
        val mLayoutManager = LinearLayoutManager(applicationContext)
        dashboardRvDrawer?.setLayoutManager(mLayoutManager)
        dashboardRvDrawer?.setItemAnimator(DefaultItemAnimator())
        dashboardRvDrawer?.adapter = dashboardDrawerListAdapter


        dashboard_drawer_layout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {

            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerStateChanged(newState: Int) {

            }
        })


    }


    override fun onSuccessResponse(message: String) {

    }

    override fun onFailureResponse(error: String) {

    }

    override fun getStringCheck(): String {
        return "Hi Check Hello"
    }


    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }

    fun navigationDrawerOnClick(position: Int, title: String) {
        when (position) {

            0 -> {
                dashboard_drawer_layout.closeDrawers()
                navigationRoutes(ProfileBaseActivity::class.java, CommonEnumUtils.PROFILE.toString())
            }

            1 -> {
                dashboard_drawer_layout.closeDrawers()
                navigationRoutes(ProfileBaseActivity::class.java, CommonEnumUtils.NOMINEE.toString())
            }

            2 -> {
                dashboard_drawer_layout.closeDrawers()
                navigationRoutes(ExpenseBaseActivity::class.java)
            }

            3 -> {
                dashboard_drawer_layout.closeDrawers()
                navigationRoutes(ProfileBaseActivity::class.java, CommonEnumUtils.BANK.toString())
            }

            10 -> {
                dashboard_drawer_layout.closeDrawers()
                dialogUtils.showAlertDialog(this, this.resources.getString(R.string.DialogLogoutString), "", "", DialogEnum.Logout.toString(), this)
            }
        }
    }

    override fun onDialogPositivePressed(dialog: Dialog, enumString: String) {

        when (enumString) {
            DialogEnum.Logout.toString() -> {
                postLogout()
            }
        }

    }

    override fun onSuccessLogoutResponse(message: String) {

    }

    override fun onFailureLogoutResponse(error: String) {

    }

    override fun getUserID(): String {
        return SingletonUtils.instance.userId
    }

    override fun postLogout() {
        progressLoadingBar()
        logoutPresenter.logoutApiCall()
    }

    override fun naviageToLoginScreen() {
        finish()
        navigationRoutes(AuthenticationBaseActivity::class.java)
    }

    override fun onSuccessReadProfileResponse(readProfileResponseModel: ReadProfileResponseModel) {
        Glide.with(this)
                .load(commonUtils.cutNull(SingletonUtils.instance.IMAGE_URL + readProfileResponseModel.user_details.user_profile_img))
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.profileplaceholder)
                .error(R.drawable.profileplaceholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageviewProfilePhoto)

        Glide.with(this)
                .load(commonUtils.cutNull(SingletonUtils.instance.IMAGE_URL + readProfileResponseModel.user_details.user_banner_img))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.banner_image)
                .error(R.drawable.banner_image)
                .into(mainBannerImg)


    }

    override fun onFailureReadProfileResponse(error: String) {

    }

    override fun onClickListeners() {
        super.onClickListeners()
        imageviewProfilePhoto.setOnClickListener {
            imageTagString = CommonEnumUtils.PROFILE.toString()
            showImageUploadDialog()
        }

        mainBannerImg.setOnClickListener {
            imageTagString = CommonEnumUtils.BANNER.toString()
            showImageUploadDialog()
        }
    }

    override fun hitGetProfileCall() {
        progressLoadingBar()
        readProfilePresenter.readUserProfileData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun showImageUploadDialog() {
        dialogUtils.showImageUploadDialog(this, DialogEnum.GALLERY.toString(), this)
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
        if (takePictureIntent.resolveActivity(this.packageManager) != null) {
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
                        file = /*File(data.data!!.path)*/File(commonUtils.getRealPathFromURI(this, contentURI))

                        var imagePaths: String
                        val task = ImageCompression(this)
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
                    mTempCameraPhotoFile = File(commonUtils.getRealPathFromURI(this, contentURI))

                    var imagePaths: String
                    val task = ImageCompression(this)
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

    override fun getUserId(): String {
        return SingletonUtils.instance.userId
    }

    override fun navigateToMainScreen() {

    }


}
