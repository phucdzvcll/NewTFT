package com.free.newtft.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.free.newtft.features.BuildFragment
import com.free.newtft.features.LoginFragment
import com.free.newtft.features.recommend_teams.RecommendFragment
import com.free.newtft.features.show_champ.ShowFragment

class MainPageAdapter(private val fragmentManager: FragmentManager) : FragmentPagerAdapter(
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