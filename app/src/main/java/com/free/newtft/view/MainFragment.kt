package com.free.newtft.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.free.common_android.BaseFragment
import com.free.newtft.R
import com.free.newtft.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.item_tab_main.view.*

class MainFragment : BaseFragment() {
    private lateinit var mMainBinding: FragmentMainBinding
    private var mainPageAdapter: MainPageAdapter? = null
    private val tabs = mutableListOf<MainPageAdapter.ItemPage>()
    private var currentTab: MainPageAdapter.ItemType? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpViewPager()
        initTabLayout()
    }

    private fun initView() {
        mainPageAdapter = MainPageAdapter(childFragmentManager)
        initTabItems()
    }

    private fun initTabItems() {
        tabs.clear()
        tabs.add(
            MainPageAdapter.ItemPage(
                title = "Tướng",
                iconResId = R.drawable.selector_icon_tab_show,
                itemType = MainPageAdapter.ItemType.Show
            )
        )
        tabs.add(
            MainPageAdapter.ItemPage(
                title = "Gợi Ý",
                iconResId = R.drawable.selector_icon_tab_recommend,
                itemType = MainPageAdapter.ItemType.Recommend
            )
        )
        tabs.add(
            MainPageAdapter.ItemPage(
                title = "Dựng Đội Hình",
                iconResId = R.drawable.selector_icon_tab_build,
                itemType = MainPageAdapter.ItemType.Build
            )
        )
        tabs.add(
            MainPageAdapter.ItemPage(
                title = "Đăng Nhập",
                iconResId = R.drawable.selector_icon_tab_login,
                itemType = MainPageAdapter.ItemType.Login
            )
        )


    }

    private fun initTabLayout() {
        mMainBinding.tlMain.removeAllTabs()
        for (tab in tabs) {
            val tabView: TabLayout.Tab = mMainBinding.tlMain.newTab()
            val customTabView =
                LayoutInflater.from(activity).inflate(R.layout.item_tab_main, null, false)
            customTabView.txtTabHomeTitle.text = tab.title
            customTabView.imgTabHomeIcon.setImageResource(tab.iconResId)
            tabView.customView = customTabView
            mMainBinding.tlMain.addTab(tabView)
        }
        mMainBinding.tlMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //nothing do here
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //nothing do here
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { tabSelected ->
                    val index = tabSelected.position
                    changePage(index)
                }
            }
        })
    }

    private fun changePage(index: Int) {
        val itemPage = mainPageAdapter?.getItemPage(index)
        if (itemPage != null) {
            currentTab = itemPage.itemType
            switchViewPagerPage(index)
        }
    }

    private fun switchViewPagerPage(index: Int) {
        mMainBinding.viewPager.currentItem = index
    }

    private fun setUpViewPager() {
        for (tab in tabs) {
            mainPageAdapter?.addPage(tab)
        }

        mMainBinding.viewPager.adapter = mainPageAdapter
        if (mainPageAdapter?.count != null) {
            mMainBinding.viewPager.offscreenPageLimit = mainPageAdapter?.count!!
        }
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}