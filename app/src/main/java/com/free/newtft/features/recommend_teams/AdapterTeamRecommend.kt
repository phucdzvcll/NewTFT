package com.free.newtft.features.recommend_teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.common_jvm.extension.createImgUrl
import com.free.domain.entities.TeamsRecommendEntity
import com.free.newtft.databinding.ItemTeamRecommendBinding

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
                    champions = mapChampion(it.listChamps)
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

    object Binding {
        @BindingAdapter("data")
        @JvmStatic
        fun load(
            recyclerView: RecyclerView,
            teamsRecommend: TeamsRecommend
        ) {
            val adapterChampsRecommend = AdapterChampsRecommend()
            adapterChampsRecommend.addData(teamsRecommend)
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 5)
            recyclerView.adapter = adapterChampsRecommend
        }
    }

    data class TeamsRecommend(
        val id: Int,
        val rank: String,
        val champions: List<Champions>
    ) {
        data class Champions(
            val id: String,
            val name: String,
            val imgUrl: String,
            val cost: Int
        )
    }
}