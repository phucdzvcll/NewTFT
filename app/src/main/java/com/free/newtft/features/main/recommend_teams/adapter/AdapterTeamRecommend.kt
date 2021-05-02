package com.free.newtft.features.main.recommend_teams.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.common_jvm.extension.createImgUrl
import com.free.domain.entities.ChampDialogEntity
import com.free.domain.entities.TeamsRecommendEntity
import com.free.newtft.databinding.ItemTeamRecommendBinding

class AdapterTeamRecommend(val owner: LifecycleOwner) :
    RecyclerView.Adapter<AdapterTeamRecommend.TeamRecommendViewHolder>() {

    val itemClickLiveData = MutableLiveData<AdapterChampsRecommend.Champion>()

    private val listTeamsRecommend = mutableListOf<TeamsRecommend>()

    class TeamRecommendViewHolder(private val binding: ItemTeamRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teamsRecommend: TeamsRecommend) {
            binding.team = teamsRecommend
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamRecommendViewHolder {
        val itemBinding = ItemTeamRecommendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val adapterChampsRecommend = AdapterChampsRecommend()

        adapterChampsRecommend.itemClickLiveData.observe(owner, {
            itemClickLiveData.value = it
        })

        itemBinding.champRecyclerView.adapter = adapterChampsRecommend
        itemBinding.champRecyclerView.layoutManager =
            GridLayoutManager(itemBinding.champRecyclerView.context, 4)
        return TeamRecommendViewHolder(itemBinding)
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
                    listTraits = mapTrait(it.listTraits)
                )
            )
        }

        val comparator =
            Comparator.comparingInt { team: TeamsRecommend -> if (team.rank.startsWith("S")) 0 else 1 }
        listTeamsRecommend.sortBy { it.rank }
        listTeamsRecommend.sortWith(comparator)
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
                    cost = it.cost,
                    isThreeStars = it.isThreeStars,
                    items = mapItems(it.items)
                )
            )
        }
        return listResult
    }

    private fun mapItems(list: List<TeamsRecommendEntity.Champ.Item>): MutableList<TeamsRecommend.Champions.Item> {
        val items = mutableListOf<TeamsRecommend.Champions.Item>()
        list.forEach {
            items.add(
                TeamsRecommend.Champions.Item(
                    name = it.name,
                    imgUrl = it.imgUrl
                )
            )
        }
        return items
    }

    private fun mapTrait(champsEntity: List<TeamsRecommendEntity.Trait>): List<TeamsRecommend.Trait> {
        val listResult = mutableListOf<TeamsRecommend.Trait>()
        champsEntity.forEach {
            listResult.add(
                TeamsRecommend.Trait(
                    name = it.name,
                    imgUrl = it.imgUrl,
                    amount = it.amountTraits,
                    style = it.style
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
            (recyclerView.adapter as AdapterChampsRecommend).addData(teamsRecommend)
        }

        @BindingAdapter("dataTraits")
        @JvmStatic
        fun loadTraits(
            recyclerView: RecyclerView,
            listTraits: List<TeamsRecommend.Trait>
        ) {
            val adapterTraitsTeamRecommend = AdapterTraitsTeamRecommend()
            adapterTraitsTeamRecommend.addData(listTraits)
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 6)
            recyclerView.adapter = adapterTraitsTeamRecommend
        }

        @BindingAdapter("setRank")
        @JvmStatic
        fun setRank(
            textView: TextView,
            rank: String
        ) {
            when (rank) {
                "S" -> textView.setTextColor(Color.YELLOW)
                "A" -> textView.setTextColor(Color.RED)
                "B" -> textView.setTextColor(Color.BLUE)
                "C" -> textView.setTextColor(Color.GREEN)
                else -> textView.setTextColor(Color.GRAY)
            }
            textView.text = rank
        }
    }

    data class TeamsRecommend(
        val id: Int,
        val rank: String,
        val champions: List<Champions>,
        val listTraits: List<Trait>
    ) {
        data class Champions(
            val isThreeStars: Boolean,
            val id: String,
            val name: String,
            val imgUrl: String,
            val cost: Int,
            val items: List<Item>
        ) {
            data class Item(
                val name: String,
                val imgUrl: String
            )
        }

        data class Trait(
            val style: String,
            val name: String,
            val amount: Int,
            val imgUrl: String
        )
    }
}