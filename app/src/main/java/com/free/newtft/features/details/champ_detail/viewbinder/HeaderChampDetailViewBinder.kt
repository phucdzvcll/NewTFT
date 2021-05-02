package com.free.newtft.features.details.champ_detail.viewbinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.free.common_jvm.mapper.Mapper
import com.free.domain.entities.ChampDetailEntity
import com.free.newtft.R
import com.free.newtft.databinding.ItemHeaderDetailChampBinding
import com.free.newtft.features.details.champ_detail.viewmodel.ChampDetailViewModel

class HeaderChampDetailViewBinder(private val itemClick: ItemCLick) :
    ItemViewBinder<HeaderChampDetailViewBinder.HeaderModel, HeaderChampDetailViewBinder.HeaderChampDetailHolder>() {
    val itemClickLiveData = MutableLiveData<HeaderModel>()

    class HeaderChampDetailHolder(val binding: ItemHeaderDetailChampBinding) :
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
        val items: List<Item>
    ) {
        data class Item(
            val name: String,
            val imgUrl: String
        )
    }

    override fun onBindViewHolder(holder: HeaderChampDetailHolder, item: HeaderModel) {
        holder.bind(headerModel = item)

        holder.binding.item1.setOnClickListener {
            itemClick.onLick(item.items[0].name)
        }
        holder.binding.item2.setOnClickListener {
            itemClick.onLick(item.items[1].name)
        }
        holder.binding.item3.setOnClickListener {
            itemClick.onLick(item.items[2].name)
        }
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): HeaderChampDetailHolder {
        val itemBinding = ItemHeaderDetailChampBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
        return HeaderChampDetailHolder(itemBinding)
    }

    class HeaderModelMapper() : Mapper<ChampDetailViewModel.DetailChampModel, HeaderModel>() {
        override fun map(input: ChampDetailViewModel.DetailChampModel): HeaderModel {
            return HeaderModel(
                coverImgUrl = input.coverImgUrl,
                imgUrl = input.imgUrl,
                cost = input.cost,
                items = mapItem(input.items)
            )
        }

        private fun mapItem(list: List<ChampDetailEntity.Item>): List<HeaderModel.Item> {
            val listItemUrl = mutableListOf<HeaderModel.Item>()
            list.forEach {
                listItemUrl.add(
                    HeaderModel.Item(
                        name = it.name,
                        imgUrl = it.imgUrl
                    )
                )
            }
            return listItemUrl
        }
    }

    interface ItemCLick {
        fun onLick(name: String)
    }

}