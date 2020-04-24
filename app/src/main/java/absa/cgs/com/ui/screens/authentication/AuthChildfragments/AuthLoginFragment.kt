package absa.cgs.com.ui.screens.authentication.AuthChildfragments

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.loginapicall.LoginPresenter
import absa.cgs.com.ui.screens.apis.loginapicall.LoginView
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.method.PasswordTransformationMethod
import kotlinx.android.synthetic.main.authloginfragment.*
import javax.inject.Inject


class AuthLoginFragment : BaseFragment(), LoginView {


    @Inject
    lateinit var loginPresenter: LoginPresenter<LoginView>


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
        loginPresenter.attachView(activity!!, this)
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

    override fun init() {
        TextEditAuthLoginPasswordEdt.transformationMethod = PasswordTransformationMethod()
        loginOnPressedListener()

    }

    private fun loginOnPressedListener() {
        AuthLoginBtn.setOnClickListener {
            loginPresenter.validateLoginData()
        }
    }

    override fun navigateMainScreen() {
        activity?.finish()
        navigationRoutes(MainActivity::class.java)
    }


    override fun postLoginData() {
        progressLoadingBar()
        loginPresenter.postLoginData()
    }

    override fun getMobileNumber(): String {
        return commonUtils.cutNull(TextEditAuthLoginPhoneNumberEdt.text.toString().trim())
    }

    override fun getPassword(): String {
        return commonUtils.cutNull(TextEditAuthLoginPasswordEdt.text.toString().trim())
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.detachView()
    }

    override fun onClickListeners() {

    }

}