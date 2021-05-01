package com.free.newtft.features.main.recommend_teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.free.newtft.R
import com.free.newtft.databinding.ItemChampRecommendBinding

class AdapterChampsRecommend :
    RecyclerView.Adapter<AdapterChampsRecommend.ChampsRecommendViewHolder>() {

    val itemClickLiveData = MutableLiveData<Champion>()

    private val listChamp = mutableListOf<ViewBinderModel>()

    class ChampsRecommendViewHolder(private val binding: ItemChampRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewBinder: ViewBinderModel) {
            binding.itemViewBinder = itemViewBinder
            when (itemViewBinder.champ.cost) {
                1 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gray)
                2 -> binding.imgChamp.setBackgroundResource(R.drawable.border_green)
                3 -> binding.imgChamp.setBackgroundResource(R.drawable.border_blue)
                4 -> binding.imgChamp.setBackgroundResource(R.drawable.border_pink)
                5 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gold)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampsRecommendViewHolder {
        return ChampsRecommendViewHolder(
            ItemChampRecommendBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChampsRecommendViewHolder, position: Int) {
        holder.bind(listChamp[position])
    }

    override fun getItemCount() = listChamp.size

    fun addData(teamsRecommend: AdapterTeamRecommend.TeamsRecommend) {
        listChamp.clear()
        listChamp.addAll(mapper(teamsRecommend))
        notifyDataSetChanged()
    }

    private fun mapper(teamsRecommend: AdapterTeamRecommend.TeamsRecommend): MutableList<ViewBinderModel> {
        val list = mutableListOf<ViewBinderModel>()
        teamsRecommend.champions.forEach {
            list.add(
                ViewBinderModel(
                    Champion(
                        id = it.id,
                        name = it.name,
                        imgUrl = it.imgUrl,
                        cost = it.cost,
                        isThreeStars = it.isThreeStars,
                        items = maItems(it.items)
                    ), itemClickLiveData = itemClickLiveData
                )
            )
        }
        return list
    }

    private fun maItems(items: List<String>): List<Champion.Item> {
        val listItems = mutableListOf<Champion.Item>()
        items.forEach {
            listItems.add(
                Champion.Item(
                    imgUrl = "https://rerollcdn.com/items/$it.png",
                    isVisible = (it != "")

                )
            )
        }
        return listItems
    }

    data class Champion(
        val isThreeStars: Boolean,
        val id: String,
        val name: String,
        val cost: Int,
        val imgUrl: String,
        val items: List<Item>
    ) {
        data class Item(
            val imgUrl: String,
            val isVisible: Boolean
        )
    }

    data class ViewBinderModel(
        val champ: Champion,
        val itemClickLiveData: MutableLiveData<Champion>
    ) {
        fun onClick() {
            itemClickLiveData.value = champ
        }
    }
}