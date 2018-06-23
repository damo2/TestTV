package com.wr.tv

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wr.comic.MainComicFragment
import com.wr.comic.ui.fragment.PlusOneFragment
import com.wr.tv.adaper.MainAdapter
import com.wr.base.BaseActivity
import com.wr.base.router.RouteUtil
import com.wr.movie.MainMovieFragment
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouteUtil.APP_MAIN)
class MainActivity : BaseActivity() {
    var mAdapter: MainAdapter? = null
    val mFragmentList = listOf<Fragment>(MainComicFragment(), MainMovieFragment(), PlusOneFragment())
    val mTabImg = intArrayOf(R.drawable.ic_main_tab_comic, R.drawable.ic_main_tab_movie, R.drawable.ic_main_tab_set)
    val mTabColor = intArrayOf(R.color.comic, R.color.movie, R.color.set)


    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    init {

    }

    override fun initView() {
        super.initView()
        setViewPgerAdapter()
        setBottomTab()
    }

    override fun initValue() {
        super.initValue()
    }

    override fun initListener() {
        super.initListener()
        viewpager_main.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                nav_bottom_main.selectTab(position)
            }
        })
    }

    private fun setViewPgerAdapter() {
        val mTabText = arrayOf(applicationContext.getString(R.string.main_tab_comic),
                applicationContext.getString(R.string.main_tab_movie),
                applicationContext.getString(R.string.main_tab_set))
        mAdapter = MainAdapter(supportFragmentManager, mFragmentList, mTabText)
        viewpager_main.adapter = mAdapter
//        viewpager_main.offscreenPageLimit=mFragmentList.size
    }

    private fun setBottomTab() {
        //汉字是否一直显示:(false:显示选中的；true全部显示)
        nav_bottom_main.isWithText(true)
        //开启跑到左边
//        nav_bottom_main.activateTabletMode();
        //整体背景色 设置为false时icon和汉字显示颜色能用
        nav_bottom_main.isColoredBackground(true);
//        nav_bottom_main.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.movie))
        //去掉影子
        // bottomNavigationView.disableShadow()
        //绑定viewpager
        nav_bottom_main.setUpWithViewPager(viewpager_main, mTabColor, mTabImg)
    }
}
