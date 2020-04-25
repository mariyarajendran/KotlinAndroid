package absa.cgs.com.ui.screens.base

import absa.cgs.com.MyApplication
import absa.cgs.com.di.component.ActivityComponent
import absa.cgs.com.di.component.DaggerActivityComponent
import absa.cgs.com.di.module.ActivityModule
import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.DialogUtils
import absa.cgs.com.utils.SessionUtils
import absa.cgs.com.utils.SingletonUtils
import absa.cgs.com.utils.enums.DialogEnum
import android.app.Dialog
import android.app.ProgressDialog

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.custom_dialog_layout.*
import kotlinx.android.synthetic.main.custom_radio_group_dialog.*
import javax.inject.Inject


open class BaseActivity : AppCompatActivity(), BaseMvpView {


    override fun onClickListeners() {

    }

    override fun init() {

    }


    private var mActivityComponent: ActivityComponent? = null

    @Inject
    lateinit var commonUtils: CommonUtils

    @Inject
    lateinit var sessionUtils: SessionUtils

//    @Inject

//    lateinit var dialogUtils: DialogUtils

    var mProgressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onStop() {
        super.onStop()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    override fun hideProgressLoadingDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog == null
        }

        if (mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }


    override fun navigationRoutes(routingClass: Class<*>) {
        val intent = Intent(this, routingClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        this.startActivity(intent)
    }

    override fun navigationRoutes(routingClass: Class<*>, values: String) {
        val intent = Intent(this, routingClass)
        intent.putExtra(values, values)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        this.startActivity(intent)
    }


    fun activityComponent(): ActivityComponent {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(ActivityModule(this))
                    .applicationComponent(MyApplication.get(this).component)
                    .build()
        }
        return mActivityComponent!!
    }


    override fun showToastShort(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun showToastLong(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun progressLoadingBar(): ProgressDialog {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.show()
        if (mProgressDialog!!.window != null) {
            mProgressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        mProgressDialog!!.setContentView(R.layout.progress_dialog)
        mProgressDialog!!.isIndeterminate = true
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        return mProgressDialog!!
    }


    override fun getAuthTokenFromSession(): String {
        SingletonUtils.instance.authToken =
                commonUtils.cutNull(commonUtils.getLoginSessionData().user_details.user_access_token)
        return SingletonUtils.instance.authToken
    }

    override fun getUserIdFronSession(): String {
        SingletonUtils.instance.userId =
                commonUtils.cutNull(commonUtils.getLoginSessionData().user_details.user_id)
        return SingletonUtils.instance.userId
    }


}