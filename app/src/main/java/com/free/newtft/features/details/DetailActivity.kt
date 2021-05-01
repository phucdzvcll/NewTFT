package com.free.newtft.features.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.free.common_android.BaseActivity
import com.free.common_android.navigation.NavigateAction
import com.free.common_android.replaceFragment
import com.free.newtft.R
import com.free.newtft.features.details.champ_detail.view.ChampDetailFragment
import com.free.newtft.features.main.show_champ.ShowFragment

class DetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent?.let {
            when (it.getSerializableExtra(ITEM_TYPE) as ItemType) {
                ItemType.CHAMP -> replaceFragment(
                    ChampDetailFragment.newInstance(it.getStringExtra(CHAMP_ID).toString()),
                    R.id.frameDetail
                )
                else -> {
                    Toast.makeText(this, "hey", Toast.LENGTH_SHORT)
                }
            }
        }
    }

    override fun onNavigateTo(action: NavigateAction.ToAction) {
        super.onNavigateTo(action)
        when (action) {
            is ChampDetailFragment.ChampOfTraitDetailAction -> openDetailActivity(
                action.id,
                action.itemType
            )
        }
    }

    private fun openDetailActivity(id: String, itemType: ItemType) {
        startActivity(newIntent(this, id, itemType))
    }

    companion object {
        val CHAMP_ID = "champ_id"
        val ITEM_TYPE = "item_type"

        fun newIntent(context: Context, id: String, itemType: ItemType): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CHAMP_ID, id)
            intent.putExtra(ITEM_TYPE, itemType)
            return intent
        }
    }

    enum class ItemType {
        CHAMP,
        ITEM
    }
}