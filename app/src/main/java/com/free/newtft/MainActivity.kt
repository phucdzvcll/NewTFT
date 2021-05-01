package com.free.newtft

import android.os.Bundle
import com.free.common_android.BaseActivity
import com.free.common_android.navigation.NavigateAction
import com.free.common_android.replaceFragment
import com.free.newtft.features.details.DetailActivity
import com.free.newtft.features.main.recommend_teams.RecommendFragment
import com.free.newtft.features.main.show_champ.ShowFragment
import com.free.newtft.view.MainFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = MainFragment.newInstance()
        replaceFragment(fragment, R.id.frameHome)
    }

    override fun onNavigateTo(action: NavigateAction.ToAction) {
        super.onNavigateTo(action)
        when (action) {
            is ShowFragment.ShowChampNavigateToAction -> openDetailActivity(
                action.id,
                action.itemType
            )
            is RecommendFragment.TeamRecommendAction -> openDetailActivity(
                action.id,
                action.itemType
            )
        }
    }

    private fun openDetailActivity(id: String, itemType: DetailActivity.ItemType) {
        startActivity(DetailActivity.newIntent(this, id, itemType))
    }
}