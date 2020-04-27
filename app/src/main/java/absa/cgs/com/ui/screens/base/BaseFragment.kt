package absa.cgs.com.ui.screens.base

import absa.cgs.com.MyApplication
import absa.cgs.com.di.component.DaggerFragmentComponent
import absa.cgs.com.di.component.FragmentComponent
import absa.cgs.com.di.module.FragmentModule
import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.DialogUtils
import absa.cgs.com.utils.SessionUtils
import absa.cgs.com.utils.SingletonUtils
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.custom_dialog_layout.*
import kotlinx.android.synthetic.main.custom_radio_group_dialog.*
import javax.inject.Inject


open class BaseFragment : Fragment(), BaseMvpView {


    override fun onClickListeners() {


    }

    override fun init() {

    }


    @Inject
    lateinit var commonUtils: CommonUtils

    @Inject
    lateinit var sessionUtils: SessionUtils

    @Inject
    lateinit var dialogUtils: DialogUtils

    var mProgressDialog: ProgressDialog? = null

    override fun showToastShort(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


    override fun showToastLong(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }


    private var mFragmentComponent: FragmentComponent? = null


    override fun hideProgressLoadingDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog == null
        }

//        if (mProgressDialog!!.isShowing) {
//            mProgressDialog!!.dismiss()
//        }

    }

    override fun navigationRoutes(routingClass: Class<*>) {
        val intent = Intent(activity, routingClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigationRoutes(routingClass: Class<*>, values: String) {
        val intent = Intent(activity, routingClass)
        intent.putExtra(values, values)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }


    fun fragmentComponent(): FragmentComponent {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(FragmentModule(this))
                    .applicationComponent(MyApplication.get(context!!).component)
                    .build()
        }
        return mFragmentComponent!!
    }

    override fun progressLoadingBar(): ProgressDialog {
        mProgressDialog = ProgressDialog(activity)
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