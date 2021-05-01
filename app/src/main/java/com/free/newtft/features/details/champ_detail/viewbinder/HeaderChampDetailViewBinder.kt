package com.free.newtft.features.details.champ_detail.viewbinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.free.common_jvm.extension.createImgUrl
import com.free.common_jvm.mapper.Mapper
import com.free.domain.entities.ChampDetailEntity
import com.free.newtft.R
import com.free.newtft.databinding.ItemHeaderDetailChampBinding
import com.free.newtft.features.details.champ_detail.viewmodel.ChampDetailViewModel
import java.util.*

class HeaderChampDetailViewBinder :
    ItemViewBinder<HeaderChampDetailViewBinder.HeaderModel, HeaderChampDetailViewBinder.HeaderChampDetailHolder>() {
    class HeaderChampDetailHolder(private val binding: ItemHeaderDetailChampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(headerModel: HeaderModel) {
            binding.header = headerModel
            when (headerModel.cost) {
                1 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gray)
                2 -> binding.imgChamp.setBackgroundResource(R.drawable.border_green)
                3 -> binding.imgChamp.setBackgroundResource(R.drawable.border_blue)
                4 -> binding.imgChamp.setBackgroundResource(R.drawable.border_pink)
                5 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gold)
            }
        }
    }

    data class HeaderModel(
        val coverImgUrl: String,
        val imgUrl: String,
        val cost: Int,
        val items: List<String>
    )

    override fun onBindViewHolder(holder: HeaderChampDetailHolder, item: HeaderModel) {
        holder.bind(headerModel = item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): HeaderChampDetailHolder {
        return HeaderChampDetailHolder(
            ItemHeaderDetailChampBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    class HeaderModelMapper() : Mapper<ChampDetailViewModel.DetailChampModel, HeaderModel>() {
        override fun map(input: ChampDetailViewModel.DetailChampModel): HeaderModel {
            return HeaderModel(
                coverImgUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${input.name}_0.jpg",
                imgUrl = input.name.createImgUrl(),
                cost = input.cost,
                items = mapItem(input.items)
            )
        }

        private fun mapItem(list: List<String>): List<String> {
            val listItemUrl = mutableListOf<String>()
            list.forEach {
                listItemUrl.add("https://rerollcdn.com/items/$it.png")
            }
            return listItemUrl
        }
    }

}