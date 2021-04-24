package com.free.newtft.features.show_champ

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.free.common_jvm.extension.createImgUrl
import com.free.common_jvm.extension.defaultZero
import com.free.domain.entities.ChampsListEntity
import com.free.newtft.R
import com.free.newtft.databinding.ItemChampBinding

class AdapterShowChamps : RecyclerView.Adapter<AdapterShowChamps.ShowChampViewHolder>() {

    private val listChamps = mutableListOf<Champ>()

    class ShowChampViewHolder(private val binding: ItemChampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(champ: Champ) {
            binding.champ = champ
            when (champ.cost) {
                1 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gray)
                2 -> binding.imgChamp.setBackgroundResource(R.drawable.border_green)
                3 -> binding.imgChamp.setBackgroundResource(R.drawable.border_blue)
                4 -> binding.imgChamp.setBackgroundResource(R.drawable.border_pink)
                5 -> binding.imgChamp.setBackgroundResource(R.drawable.border_gold)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowChampViewHolder {

        return ShowChampViewHolder(
            ItemChampBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShowChampViewHolder, position: Int) {
        holder.bind(listChamps[position])
    }

    override fun getItemCount() = listChamps.size.defaultZero()


    fun setupData(champsListEntity: ChampsListEntity) {
        listChamps.clear()
        champsListEntity.listChamps.forEach { champ ->
            listChamps.add(
                Champ(
                    id = champ.id,
                    cost = champ.cost,
                    name = champ.name,
                    imgUrl = champ.name.createImgUrl()
                )
            )
        }
        listChamps.sortBy { it.cost }
        notifyDataSetChanged()
    }

    data class Champ(
        val id: String,
        val name: String,
        val cost: Int,
        val imgUrl: String
    )
}