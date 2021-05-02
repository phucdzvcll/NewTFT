package com.free.newtft.features.main.weapon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.free.domain.entities.WeaponEntity
import com.free.newtft.databinding.ItemWeaponBinding

class WeaponAdapter : RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder>() {

    val itemClickLiveData = MutableLiveData<WeaponEntity>()

    private val listModels = mutableListOf<ViewBinderModel>()

    class WeaponViewHolder(private val binding: ItemWeaponBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewBinderModel: ViewBinderModel) {
            binding.viewBinderModel = viewBinderModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        return WeaponViewHolder(
            ItemWeaponBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        holder.bind(listModels[position])
    }

    fun addData(listWeapon: List<WeaponEntity>) {
        listModels.clear()
        listModels.addAll(mapper(listWeapon))
        notifyDataSetChanged()
    }

    private fun mapper(listWeapon: List<WeaponEntity>): MutableList<ViewBinderModel> {
        val list = mutableListOf<ViewBinderModel>()
        listWeapon.forEach {
            list.add(
                ViewBinderModel(
                    weapon = it,
                    itemClickLiveData = itemClickLiveData
                )
            )
        }
        return list
    }

    override fun getItemCount() = listModels.size
    data class ViewBinderModel(
        val weapon: WeaponEntity,
        val itemClickLiveData: MutableLiveData<WeaponEntity>
    ) {
        fun onClick() {
            itemClickLiveData.value = weapon
        }
    }
}