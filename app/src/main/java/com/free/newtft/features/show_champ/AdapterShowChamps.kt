package com.free.newtft.features.show_champ

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.free.common_jvm.extension.defaultZero
import com.free.domain.entities.ChampsListEntity
import com.free.newtft.databinding.ItemChampBinding

class AdapterShowChamps : RecyclerView.Adapter<AdapterShowChamps.ShowChampViewHolder>() {

    private val listChamps = mutableListOf<Champ>()

    class ShowChampViewHolder(private val binding: ItemChampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(champ: Champ) {
            binding.champ = champ
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
                    imgUrl = champ.imgUrl,
                    name = champ.name
                )
            )
        }
        notifyDataSetChanged()
    }

    data class Champ(
        val id: Int,
        val name: String,
        val cost: Int,
        val imgUrl: String
    )
}