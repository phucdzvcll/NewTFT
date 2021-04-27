package com.free.newtft.features.recommend_teams

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.common_jvm.extension.createImgUrl
import com.free.common_jvm.extension.defaultZero
import com.free.domain.entities.TeamsRecommendEntity
import com.free.newtft.R
import com.free.newtft.databinding.ItemTeamRecommendBinding
import java.util.*

class AdapterTeamRecommend : RecyclerView.Adapter<AdapterTeamRecommend.TeamRecommendViewHolder>() {

    private val listTeamsRecommend = mutableListOf<TeamsRecommend>()

    class TeamRecommendViewHolder(private val binding: ItemTeamRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamsRecommend: TeamsRecommend) {
            binding.team = teamsRecommend
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamRecommendViewHolder {
        return TeamRecommendViewHolder(
            ItemTeamRecommendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeamRecommendViewHolder, position: Int) {
        holder.bind(listTeamsRecommend[position])
    }

    override fun getItemCount() = listTeamsRecommend.size

    fun addData(listTeamsRecommendEntity: List<TeamsRecommendEntity>) {
        this.listTeamsRecommend.clear()
        this.listTeamsRecommend.addAll(mapper(listTeamsRecommendEntity))
        notifyDataSetChanged()
    }

    private fun mapper(listTeamsRecommendEntity: List<TeamsRecommendEntity>): List<TeamsRecommend> {
        val listTeamsRecommend = mutableListOf<TeamsRecommend>()
        listTeamsRecommendEntity.forEach {
            listTeamsRecommend.add(
                TeamsRecommend(
                    id = it.id,
                    rank = it.rank,
                    champions = mapChampion(it.listChamps),
                    listTraits = mapTraits(it.listTraits)
                )
            )
        }
        return listTeamsRecommend
    }

    private fun mapChampion(champsEntity: List<TeamsRecommendEntity.Champ>): List<TeamsRecommend.Champions> {
        val listResult = mutableListOf<TeamsRecommend.Champions>()
        champsEntity.forEach {
            listResult.add(
                TeamsRecommend.Champions(
                    id = it.id,
                    name = it.name,
                    imgUrl = it.name.createImgUrl(),
                    cost = it.cost
                )
            )
        }
        return listResult
    }

    private fun mapTraits(listTraitsEntity: List<TeamsRecommendEntity.Trait>): List<TeamsRecommend.Trait> {
        val listResult = mutableListOf<TeamsRecommend.Trait>()
        sortUsers(listTraitsEntity).forEach {
            val isVisible = it.style != "none"
            listResult.add(
                TeamsRecommend.Trait(
                    amount = if (isVisible) {
                        it.amountTraits.toString()
                    } else {
                        ""
                    },
                    isVisible = isVisible,
                    imgUrl = "https://rerollcdn.com/icons/" + it.name.toLowerCase(Locale.ROOT) + ".png",
                    name = it.name,
                    style = it.style
                )
            )
        }
        listResult.sortByDescending { it.isVisible }
        return listResult
    }

    private fun sortUsers(trait: List<TeamsRecommendEntity.Trait>): List<TeamsRecommendEntity.Trait> {
        val roles: HashMap<String, Int> = hashMapOf(
            "none" to 0,
            "bronze" to 1,
            "silver" to 2,
            "gold" to 3,
            "chromatic" to 4
        )
        val comparator =
            Comparator { o1: TeamsRecommendEntity.Trait, o2: TeamsRecommendEntity.Trait ->
                return@Comparator roles[o2.style].defaultZero() - roles[o1.style].defaultZero()
            }
        val copy = arrayListOf<TeamsRecommendEntity.Trait>().apply { addAll(trait) }
        copy.sortWith(comparator)
        return copy
    }

    object Binding {
        @BindingAdapter("data")
        @JvmStatic
        fun load(
            recyclerView: RecyclerView,
            teamsRecommend: TeamsRecommend
        ) {
            val adapterChampsRecommend = AdapterChampsRecommend()
            adapterChampsRecommend.addData(teamsRecommend)
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 8)
            recyclerView.adapter = adapterChampsRecommend
        }

        @BindingAdapter("setBackground")
        @JvmStatic
        fun background(
            constraintLayout: ConstraintLayout,
            style: String
        ) {
            when (style) {
                "bronze" -> constraintLayout.background = ContextCompat.getDrawable(
                    constraintLayout.context,
                    R.drawable.layout_bg_bzone
                )
                "silver" -> constraintLayout.background = ContextCompat.getDrawable(
                    constraintLayout.context,
                    R.drawable.layout_bg_silver
                )
                "gold" -> constraintLayout.background = ContextCompat.getDrawable(
                    constraintLayout.context,
                    R.drawable.layout_bg_gold
                )
                "chromatic" -> constraintLayout.background = ContextCompat.getDrawable(
                    constraintLayout.context,
                    R.drawable.layout_bg_chromatic
                )
            }
        }
    }

    data class TeamsRecommend(
        val id: Int,
        val rank: String,
        val champions: List<Champions>,
        val listTraits: List<Trait>
    ) {
        data class Champions(
            val id: String,
            val name: String,
            val imgUrl: String,
            val cost: Int
        )

        data class Trait(
            val style: String,
            val isVisible: Boolean,
            val name: String,
            val amount: String,
            val imgUrl: String
        )
    }
}