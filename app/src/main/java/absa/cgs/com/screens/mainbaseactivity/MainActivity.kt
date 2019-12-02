package absa.cgs.com.screens.mainbaseactivity

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.screens.base.BaseActivity
import absa.cgs.com.screens.customerfragment.CustomerFragment
import absa.cgs.com.screens.profilefragment.ProfileFragment
import absa.cgs.com.screens.dashboardfragment.DashboardFragment
import absa.cgs.com.utils.CommonUtils
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        mToolbar?.title = this.resources.getString(R.string.bottom_nav_dashboard)
        loadFragment(DashboardFragment())
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
        toggle.drawerArrowDrawable.setColor(resources.getColor(R.color.colorWhite))
        dashboardDrawerListAdapter = DrawerListAdapter(this@MainActivity, mNavigationItems!!)
        mListView?.adapter = dashboardDrawerListAdapter
        mListView?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id -> }
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
