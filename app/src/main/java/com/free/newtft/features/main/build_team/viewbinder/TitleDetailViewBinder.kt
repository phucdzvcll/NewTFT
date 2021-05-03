package com.free.newtft.features.main.build_team.viewbinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.free.common_jvm.mapper.Mapper
import com.free.domain.entities.ChampOfTeamBuilderEntity
import com.free.newtft.databinding.TitleDetailTeamBuilderBinding

class TitleDetailViewBinder :
    ItemViewBinder<TitleDetailViewBinder.TitleModel, TitleDetailViewBinder.TitleDetailViewHolder>() {

    class TitleDetailViewHolder(val binding: TitleDetailTeamBuilderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(titleModel: TitleModel) {
            binding.viewBinderModel = titleModel
        }
    }

    override fun onBindViewHolder(holder: TitleDetailViewHolder, item: TitleModel) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): TitleDetailViewHolder {
        val itemBinding = TitleDetailTeamBuilderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TitleDetailViewHolder(itemBinding)
    }

    data class TitleModel(
        val name: String,
        val imgUrl: String
    )

    class TitleDetailBuilderMapper : Mapper<ChampOfTeamBuilderEntity.Trait, TitleModel>() {
        override fun map(input: ChampOfTeamBuilderEntity.Trait): TitleModel {
            return TitleModel(
                name = input.name,
                imgUrl = input.imgUrl
            )
        }

    }

}