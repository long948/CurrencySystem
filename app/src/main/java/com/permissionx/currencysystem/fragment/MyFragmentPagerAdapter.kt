package com.block.xjfkchain.ui.fragment


import android.text.Html
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by jingbin on 2016/12/6.
 */

class MyFragmentPagerAdapter : FragmentStatePagerAdapter {
    override fun getCount()= mFragment?.size ?: 3

    private var mFragment: List<Fragment>?=null
    private var mTitleList: List<String>?=null
    private val mRegisteredFragments = SparseArray<Fragment>()

//    val count: Int
//        get() = mFragment?.size ?: 3

    /**
     * 主页使用
     */
    constructor(fm: FragmentManager) : super(fm) {}

    /**
     * 接收首页传递的标题
     */
    constructor(
        fm: FragmentManager,
        fragment: List<Fragment>,
        titleList: List<String>
    ) : super(fm) {
        this.mFragment = fragment
        this.mTitleList = titleList
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        mRegisteredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem( container: ViewGroup, position: Int, `object`: Any) {
        mRegisteredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    fun getRegisteredFragment(position: Int): Fragment {
        return mRegisteredFragments.get(position)
    }

    /**
     * 应该是创建Fragment的地方
     * https://mp.weixin.qq.com/s/MOWdbI5IREjQP1Px-WJY1Q
     */
    override fun getItem(position: Int): Fragment {
        return mFragment!![position]
    }

    /**
     * 首页显示title，每日推荐等..
     * 若有问题，移到对应单独页面
     */
    override fun getPageTitle(position: Int): CharSequence {
        return if (mTitleList != null && position < mTitleList!!.size) {
            Html.fromHtml(mTitleList!![position])
        } else {
            ""
        }
    }
}
