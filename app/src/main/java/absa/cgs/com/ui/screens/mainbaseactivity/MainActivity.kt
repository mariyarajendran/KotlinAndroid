package absa.cgs.com.ui.screens.mainbaseactivity

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.customer.CustomerFragment
import absa.cgs.com.ui.screens.profile.ProfileFragment
import absa.cgs.com.ui.screens.dashboard.DashboardFragment
import absa.cgs.com.ui.screens.mainbaseactivity.Model.NavigationDataModel
import absa.cgs.com.utils.CommonUtils
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_kotlin_play_ground.*
import kotlinx.android.synthetic.main.content_dashboardscreen.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {


    private var dashboardDrawerListAdapter: DrawerListAdapter? = null


    @Inject
    lateinit var mainPresenter: MainPresenter<MainView>

    @Inject
    lateinit var commonUtils: CommonUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)
        init()
    }


    private fun init() {
        activityComponent().inject(this)
        mainPresenter.attachView(this, this)
        mainPresenter.getDataFromServer()
        setSupportActionBar(dashboardToolbar)
        supportActionBar?.title = this.resources.getString(R.string.bottom_nav_customer)
        mainPresenter.addDrawerArrayData()
        loadFragment(CustomerFragment())
        contentDashboardBottomnavigationView.selectedItemId = (R.id.bottomNavigationItemCustomer)
        contentDashboardBottomnavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

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
            }
        }
        )
        val mLayoutManager = LinearLayoutManager(applicationContext)
        dashboardRvDrawer?.setLayoutManager(mLayoutManager)
        dashboardRvDrawer?.setItemAnimator(DefaultItemAnimator())
        dashboardRvDrawer?.adapter = dashboardDrawerListAdapter

    }


    override fun onSuccessResponse(message: String) {
        commonUtils.showToastSmall(message)
    }

    override fun onFailureResponse(error: String) {
        commonUtils.showToastSmall(error)
    }

    override fun getStringCheck(): String {
        return "Hi Check Hello"
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }
}
