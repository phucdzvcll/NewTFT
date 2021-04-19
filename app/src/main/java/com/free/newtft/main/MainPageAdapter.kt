package com.free.newtft.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.free.newtft.main.view.BuildFragment
import com.free.newtft.main.view.LoginFragment
import com.free.newtft.main.view.RecommendFragment
import com.free.newtft.main.view.ShowFragment

class MainPageAdapter(val fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val itemPages = mutableListOf<ItemPage>()

    override fun getCount() = itemPages.size

    fun getItemPage(position: Int): ItemPage? {
        return itemPages.getOrNull(position)
    }

    override fun getItem(position: Int): Fragment {
        val page = itemPages[position]
        return when (page.itemType) {
            ItemType.Show -> ShowFragment.newInstant()
            ItemType.Recommend -> RecommendFragment.newInstant()
            ItemType.Build -> BuildFragment.newInstant()
            ItemType.Login -> LoginFragment.newInstant()
        }
    }

    fun addPage(itemPage: ItemPage) {
        itemPages.add(itemPage)
    }

    data class ItemPage(val title: String, val iconResId: Int, val itemType: ItemType)

    enum class ItemType {
        Show,
        Recommend,
        Build,
        Login
    }
}