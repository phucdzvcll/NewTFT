package com.free.newtft.features.main.build_team.viewbinder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.free.common_jvm.mapper.Mapper
import com.free.domain.entities.ChampOfTeamBuilderEntity
import com.free.newtft.R
import com.free.newtft.databinding.ItemChampBuilderBinding

class ChampBuilderViewBinder :
    ItemViewDelegate<ChampOfTeamBuilderEntity, ChampBuilderViewBinder.ChampBuilderViewHolder>() {

    val itemClickLiveData = MutableLiveData<ChampOfTeamBuilderEntity>()

    class ChampBuilderViewHolder(val binding: ItemChampBuilderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(champModel: ViewBinderModel) {
            binding.champBuilder = champModel
        }

    }

    override fun onBindViewHolder(holder: ChampBuilderViewHolder, item: ChampOfTeamBuilderEntity) {
        holder.bind(ViewBinderModel(item, itemClickLiveData))
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup): ChampBuilderViewHolder {
        return ChampBuilderViewHolder(
            ItemChampBuilderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object Binding {
        @BindingAdapter("setBorderColor")
        @JvmStatic
        fun setBorderColor(
            imageView: ImageView,
            cost: Int
        ) {
            when (cost) {
                1 -> imageView.setBackgroundResource(R.drawable.border_gray)
                2 -> imageView.setBackgroundResource(R.drawable.border_green)
                3 -> imageView.setBackgroundResource(R.drawable.border_blue)
                4 -> imageView.setBackgroundResource(R.drawable.border_pink)
                5 -> imageView.setBackgroundResource(R.drawable.border_gold)
            }
        }
    }

    data class ViewBinderModel(
        val champ: ChampOfTeamBuilderEntity,
        val itemClickLiveData: MutableLiveData<ChampOfTeamBuilderEntity>
    ) {
        fun onClick() {
            itemClickLiveData.value = champ
        }
    }
}