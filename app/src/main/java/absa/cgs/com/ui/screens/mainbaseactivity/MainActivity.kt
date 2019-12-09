package absa.cgs.com.ui.screens.mainbaseactivity

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.customer.CustomerFragment
import absa.cgs.com.ui.screens.profile.ProfileFragment
import absa.cgs.com.ui.screens.dashboard.DashboardFragment
import absa.cgs.com.utils.CommonUtils
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {


    private val presenterMain = MainPresenter(this, MainInteractor())

    private var mToolbar: Toolbar? = null
    private var mRecyclerView: RecyclerView? = null
    private var drawerLayout: DrawerLayout? = null
    private var mNavigationItems: ArrayList<String>? = null
    private var dashboardDrawerListAdapter: DrawerListAdapter? = null

    @Inject
    lateinit var commonUtils: CommonUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)
        init()
        //presenterMain.validateFirstTrigger("samples", "")
    }


    private fun init() {
        activityComponent().inject(this)
        mToolbar = findViewById(R.id.dashboard_toolbar)
        mRecyclerView = findViewById(R.id.dashboard_rv_drawer)
        setSupportActionBar(mToolbar)
        supportActionBar?.title = this.resources.getString(R.string.bottom_nav_customer)
        addDrawerArrayData()
        drawerLayout = findViewById(R.id.dashboard_drawer_layout)
        setDrawerLayout()
        loadFragment(CustomerFragment())
        val navigation = findViewById<BottomNavigationView>(R.id.content_dashboard_bottomnavigation_view)
        navigation.selectedItemId = (R.id.bottom_navigation_item_customer)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    private fun addDrawerArrayData() {
        mNavigationItems = ArrayList()
        mNavigationItems?.add("Reports")
        mNavigationItems?.add("Onile Transactions")
        mNavigationItems?.add("Collection Agents")
        mNavigationItems?.add("Customer Queries")
        mNavigationItems?.add("Language")
        mNavigationItems?.add("Forgot Password")
        mNavigationItems?.add("Privacy Policy")
        mNavigationItems?.add("Logout")

    }

    private fun setDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
                this@MainActivity, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout?.setDrawerListener(toggle)
        toggle.syncState()
        toggle.drawerArrowDrawable.setColor(resources.getColor(R.color.colorWhite))
        dashboardDrawerListAdapter = DrawerListAdapter(this@MainActivity, mNavigationItems!!, object : OnListItemClickInterface {
            override fun OnSelectedItemClickListener(title: String, position: Int) {
            }
        }
        )
        val mLayoutManager = LinearLayoutManager(applicationContext)
        mRecyclerView?.setLayoutManager(mLayoutManager)
        mRecyclerView?.setItemAnimator(DefaultItemAnimator())
        mRecyclerView?.adapter = dashboardDrawerListAdapter

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment
        when (item.itemId) {
            R.id.bottom_navigation_item_dashboard -> {
                mToolbar?.title = this.resources.getString(R.string.bottom_nav_dashboard)
                fragment = DashboardFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_item_customer -> {
                mToolbar?.title = this.resources.getString(R.string.bottom_nav_customer)
                fragment = CustomerFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_item_search -> {
                mToolbar?.title = this.resources.getString(R.string.bottom_nav_search)
                fragment = DashboardFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_item_profile -> {
                mToolbar?.title = this.resources.getString(R.string.bottom_nav_profile)
                fragment = ProfileFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_dashboard_framelayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onSuccessResponse(message: String) {
        commonUtils.showToastSmall(message)
    }

    override fun onFailureResponse(error: String) {
        commonUtils.showToastSmall(error)
    }
}
