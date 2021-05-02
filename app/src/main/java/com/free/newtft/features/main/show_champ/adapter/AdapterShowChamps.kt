package com.free.newtft.features.main.show_champ.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.free.common_jvm.extension.createImgUrl
import com.free.common_jvm.extension.defaultZero
import com.free.domain.entities.ChampsEntity
import com.free.newtft.R
import com.free.newtft.databinding.ItemChampBinding

class AdapterShowChamps : RecyclerView.Adapter<AdapterShowChamps.ShowChampViewHolder>() {

    val championLiveData: MutableLiveData<Champ> = MutableLiveData()

    private val listChamps = mutableListOf<ViewBinderModel>()

    class ShowChampViewHolder(private val binding: ItemChampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewBinderModel: ViewBinderModel) {
            binding.viewBinderModel = viewBinderModel
            when (viewBinderModel.champ.cost) {
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
        holder.bind(
            ViewBinderModel(champ = listChamps[position].champ,
            itemClickLiveData = championLiveData
        )
        )
    }

    override fun getItemCount() = listChamps.size.defaultZero()


    fun setupData(champsEntity: List<ChampsEntity>) {
        listChamps.clear()
        champsEntity.forEach { champ ->
            listChamps.add(
                ViewBinderModel(
                    Champ(
                        id = champ.id,
                        cost = champ.cost,
                        name = champ.name,
                        imgUrl = champ.imgUrl
                    ),
                    itemClickLiveData = championLiveData
                )
            )
        }
        listChamps.sortBy { it.champ.cost }
        notifyDataSetChanged()
    }

    data class Champ(
        val id: String,
        val name: String,
        val cost: Int,
        val imgUrl: String
    )

    data class ViewBinderModel(
        val champ: Champ,
        val itemClickLiveData: MutableLiveData<Champ>
    ) {
        fun onItemClick() {
            itemClickLiveData.value = champ
        }
    }
}