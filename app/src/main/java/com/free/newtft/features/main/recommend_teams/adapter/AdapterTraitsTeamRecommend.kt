package com.free.newtft.features.main.recommend_teams.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.free.common_jvm.extension.defaultZero
import com.free.newtft.R
import com.free.newtft.databinding.ItemTraitRecommendBinding
import java.util.*

class AdapterTraitsTeamRecommend :
    RecyclerView.Adapter<AdapterTraitsTeamRecommend.TraitsTeamRecommendViewHolder>() {

    private val listTraits = mutableListOf<Trait>()

    class TraitsTeamRecommendViewHolder(val binding: ItemTraitRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trait: Trait) {
            binding.trait = trait
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TraitsTeamRecommendViewHolder {
        return TraitsTeamRecommendViewHolder(
            ItemTraitRecommendBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TraitsTeamRecommendViewHolder, position: Int) {
        holder.bind(listTraits[position])
    }

    override fun getItemCount() = listTraits.size

    fun addData(listTraits: List<AdapterTeamRecommend.TeamsRecommend.Trait>) {
        this.listTraits.clear()
        this.listTraits.addAll(mapTraits(listTraits))
        notifyDataSetChanged()
    }

    private fun mapTraits(listTraitsEntity: List<AdapterTeamRecommend.TeamsRecommend.Trait>): List<Trait> {
        val listResult = mutableListOf<Trait>()
        sortTraits(listTraitsEntity).forEach {
            if (it.style != "none") {
                listResult.add(
                    Trait(
                        amount = it.amount.toString(),
                        imgUrl = "https://rerollcdn.com/icons/" + it.name.toLowerCase(Locale.ROOT) + ".png",
                        name = it.name,
                        style = it.style
                    )
                )
            }
        }
        return listResult
    }

    private fun sortTraits(trait: List<AdapterTeamRecommend.TeamsRecommend.Trait>): List<AdapterTeamRecommend.TeamsRecommend.Trait> {
        val roles: HashMap<String, Int> = hashMapOf(
            "none" to 0,
            "bronze" to 1,
            "silver" to 2,
            "gold" to 3,
            "chromatic" to 4
        )
        val comparator =
            Comparator { o1: AdapterTeamRecommend.TeamsRecommend.Trait, o2: AdapterTeamRecommend.TeamsRecommend.Trait ->
                return@Comparator roles[o2.style].defaultZero() - roles[o1.style].defaultZero()
            }
        val copy = arrayListOf<AdapterTeamRecommend.TeamsRecommend.Trait>().apply { addAll(trait) }
        copy.sortWith(comparator)
        return copy
    }

    object Binding {
        @BindingAdapter("setBackground")
        @JvmStatic
        fun setBackground(
            linearLayout: LinearLayout,
            style: String
        ) {
            when (style) {
                "bronze" -> linearLayout.background = ContextCompat.getDrawable(
                    linearLayout.context,
                    R.drawable.layout_bg_bzone
                )
                "silver" -> linearLayout.background = ContextCompat.getDrawable(
                    linearLayout.context,
                    R.drawable.layout_bg_silver
                )
                "gold" -> linearLayout.background = ContextCompat.getDrawable(
                    linearLayout.context,
                    R.drawable.layout_bg_gold
                )
                "chromatic" -> linearLayout.background = ContextCompat.getDrawable(
                    linearLayout.context,
                    R.drawable.layout_bg_chromatic
                )
            }
        }
    }

    data class Trait(
        val style: String,
        val name: String,
        val amount: String,
        val imgUrl: String
    )
}