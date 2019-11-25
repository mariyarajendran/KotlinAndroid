package absa.cgs.com.screens.kotlinplayground

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.screens.base.BaseActivity
import absa.cgs.com.screens.homefragment.AccountFragment
import absa.cgs.com.screens.homefragment.HomeFragment
import absa.cgs.com.utils.CommonUtils
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.widget.AdapterView
import android.widget.ListView
import java.util.ArrayList


class MainActivity : BaseActivity(), MainView {


    private var commonUtils = CommonUtils(this)
    private val presenterMain = MainPresenter(this, MainInteractor())

    private var mToolbar: Toolbar? = null
    private var mListView: ListView? = null
    private var drawerLayout: DrawerLayout? = null
    private var mNavigationItems: ArrayList<String>? = null
    private var dashboardDrawerListAdapter: DrawerListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)
        presenterMain.validateFirstTrigger("samples", "")

        init()
    }


    private fun init() {
        mToolbar = findViewById(R.id.dashboard_toolbar)
        mListView = findViewById(R.id.dashboard_lv_drawer)
        setSupportActionBar(mToolbar)
        addDrawerArrayData()
        drawerLayout = findViewById(R.id.dashboard_drawer_layout)
        setDrawerLayout()
        mToolbar?.title = "Shop"
        loadFragment(HomeFragment())
        val navigation = findViewById<BottomNavigationView>(R.id.content_dashboard_bottomnavigation_view)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private fun addDrawerArrayData() {
        mNavigationItems = ArrayList()
        mNavigationItems?.add("Call")
        mNavigationItems?.add("Favorite")
        mNavigationItems?.add("Search")
    }

    private fun setDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
                this@MainActivity, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout?.setDrawerListener(toggle)
        toggle.syncState()
        dashboardDrawerListAdapter = DrawerListAdapter(this@MainActivity, mNavigationItems!!)
        mListView?.adapter = dashboardDrawerListAdapter
        mListView?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment
        when (item.itemId) {
            R.id.bottom_navigation_item_home -> {
                mToolbar?.title = "Shop"
                fragment = HomeFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_item_email -> {
                mToolbar?.title = ("My Gifts")
                fragment = AccountFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_item_search -> {
                mToolbar?.title = ("Cart")
                fragment = HomeFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_item_account -> {
                mToolbar?.title = ("Profile")
                fragment = AccountFragment()
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
