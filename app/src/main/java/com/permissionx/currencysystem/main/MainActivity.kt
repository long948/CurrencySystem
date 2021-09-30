package com.permissionx.currencysystem.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.permissionx.currencysystem.R
import com.permissionx.currencysystem.base.BaseMvpActivity
import com.permissionx.currencysystem.fragment.HomeFragment
import com.permissionx.currencysystem.fragment.MineFragment
import com.permissionx.currencysystem.fragment.ProductFragment
import com.permissionx.currencysystem.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :BaseMvpActivity<MainContract.IPresenter>(),MainContract.IView,
    BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }
    private val na= mutableListOf(R.id.navigation1,R.id.navigation2,R.id.navigation3)
    override fun onPageSelected(position: Int) {
        navigation.selectedItemId=na[position]
    }

    private val fgList= mutableListOf(
        HomeFragment(),
        ProductFragment(),
        MineFragment()
    )

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation1->{
                setTabColor(0)
                    return true
            }
            R.id.navigation2->{
                setTabColor(1)
                return true
            }
            R.id.navigation3->{
                setTabColor(2)
                return true
            }
        }
        return false
    }

    private fun setTabColor(index: Int) {
        vp_main.currentItem=index
    }

    override fun getLayoutId()=R.layout.activity_main

    override fun registerPresenter()=MainPresenter::class.java


    override fun initView() {
        navigation.setOnNavigationItemSelectedListener (this)
        navigation.itemIconTintList=null
        val mMainAdapter=MainFragmentAdapter(supportFragmentManager,fgList)
        vp_main.adapter=mMainAdapter
        vp_main.offscreenPageLimit=3
        vp_main.addOnPageChangeListener(this)
        setTabColor(0)
        navigation.labelVisibilityMode=LabelVisibilityMode.LABEL_VISIBILITY_LABELED

    }
    override fun showErrorMsg(msg: String) {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }


}

class MainFragmentAdapter(fm:FragmentManager,private val mFragments:List<Fragment>)
    :FragmentPagerAdapter(fm){
    override fun getItem(position: Int)=mFragments[position]

    override fun getCount()=mFragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return ""
    }
}
