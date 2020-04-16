package absa.cgs.com.ui.screens.authentication.AuthChildfragments

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.readexpenseapicall.ReadExpensePresenter
import absa.cgs.com.ui.screens.apis.readexpenseapicall.ReadExpenseView
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.method.PasswordTransformationMethod
import kotlinx.android.synthetic.main.authloginfragment.*
import javax.inject.Inject


class AuthLoginFragment : BaseFragment(), ReadExpenseView {


    override fun showExpenseToast(message: String) {
        showToastLong(message)
    }

    override fun onSuccessReadExpenseResponse(message: String) {

    }

    override fun onFailureReadExpenseResponse(error: String) {

    }

    override fun getSearchKeyword(): String {
        return ""
    }

    override fun getPageCount(): String {

        return "0"
    }

    override fun getFromDate(): String {
        return ""
    }

    override fun getToDate(): String {
        return ""
    }


    override fun getUserID(): String {
        return "1"
    }

    @Inject
    lateinit var readExpensePresenter: ReadExpensePresenter<ReadExpenseView>


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
        readExpensePresenter.attachView(activity!!, this)
        readExpensePresenter.readExpenseData()
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.authloginfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        TextEditAuthLoginPasswordEdt.transformationMethod = PasswordTransformationMethod()
        loginOnPressedListener()
    }

    private fun loginOnPressedListener() {
        AuthLoginBtn.setOnClickListener {
            navigationRoutes(MainActivity::class.java)
        }
    }

}