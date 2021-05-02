package com.free.newtft.features.details.champ_detail.viewbinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.free.common_jvm.mapper.Mapper
import com.free.domain.entities.TraitDetailEntity
import com.free.newtft.R
import com.free.newtft.databinding.ItemChampOfTraitDetailBinding
import com.free.newtft.databinding.ItemTraitDetailChampBinding

class TraitsDetailViewBinder(val lifecycle: LifecycleOwner) :
    ItemViewBinder<TraitsDetailViewBinder.TraitDetailModel, TraitsDetailViewBinder.TraitsDetailViewHolder>() {

    val itemClickLiveData: MutableLiveData<TraitDetailModel.Champion> = MutableLiveData()

    class TraitsDetailViewHolder(val binding: ItemTraitDetailChampBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TraitDetailModel) {
            binding.traitDetailModel = model
        }

    }


    override fun onBindViewHolder(holder: TraitsDetailViewHolder, item: TraitDetailModel) {
        holder.bind(item)


    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): TraitsDetailViewHolder {
        val itemBinding = ItemTraitDetailChampBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val adapter = ChampAdapter()
        adapter.itemClickLiveData.observe(lifecycle, {
            itemClickLiveData.value = it
        })
        itemBinding.champOfTraitRecyclerView.layoutManager =
            GridLayoutManager(itemBinding.champOfTraitRecyclerView.context, 7)
        itemBinding.champOfTraitRecyclerView.adapter = adapter
        return TraitsDetailViewHolder(itemBinding)
    }

    class TraitDetailMapper : Mapper<TraitDetailEntity, TraitDetailModel>() {
        override fun map(input: TraitDetailEntity): TraitDetailModel {
            return TraitDetailModel(
                name = input.name,
                innate = input.innate,
                description = input.description,
                imgUrl = input.imgUrl,
                listSets = mapSets(input.listSets),
                listChamps = mapChampions(input.listChamps)
            )
        }

        private fun mapChampions(listChampsEntity: List<TraitDetailEntity.Champion>): MutableList<TraitDetailModel.Champion> {
            val listChamps = mutableListOf<TraitDetailModel.Champion>()
            listChampsEntity.forEach {
                listChamps.add(
                    TraitDetailModel.Champion(
                        id = it.id,
                        name = it.name,
                        cost = it.cost,
                        imgUrl = it.imgUrl
                    )
                )
            }
            return listChamps
        }

        private fun mapSets(listChampsEntity: List<TraitDetailEntity.Set>): MutableList<TraitDetailModel.Set> {
            val listSets = mutableListOf<TraitDetailModel.Set>()
            listChampsEntity.forEach {
                listSets.add(
                    TraitDetailModel.Set(
                        min = it.min.toString(),
                        style = it.style,
                        active = it.active,
                        isVisible = true
                    )
                )
            }
            if (listSets.size < 4) {
                val maxSize = 4 - listSets.size
                for (i in 1..maxSize) {
                    listSets.add(
                        TraitDetailModel.Set(
                            min = "",
                            active = "",
                            isVisible = false,
                            style = ""
                        )
                    )
                }
            }
            return listSets
        }
    }


    data class TraitDetailModel(
        val name: String,
        val innate: String,
        val description: String,
        val imgUrl: String,
        val listChamps: List<Champion>,
        val listSets: List<Set>
    ) {

        fun isVisibleInnate(): Boolean {
            return innate != ""
        }

        data class Champion(
            val id: String,
            val name: String,
            val imgUrl: String,
            val cost: Int
        )

        data class Set(
            val min: String,
            val style: String,
            val active: String,
            val isVisible: Boolean
        )
    }

    object Binding {
        @BindingAdapter("loadChampion")
        @JvmStatic
        fun loadChamp(
            recyclerView: RecyclerView,
            listChamps: List<TraitDetailModel.Champion>
        ) {
            (recyclerView.adapter as ChampAdapter).addData(listChamps)
        }
    }

    class ChampAdapter : RecyclerView.Adapter<ChampAdapter.ChampViewHolder>() {

        val itemClickLiveData = MutableLiveData<TraitDetailModel.Champion>()

        private val listChamps = mutableListOf<ViewBinderModel>()

        class ChampViewHolder(private val binding: ItemChampOfTraitDetailBinding) :
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

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampViewHolder {
            return ChampViewHolder(
                ItemChampOfTraitDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ChampViewHolder, position: Int) {
            holder.bind(listChamps[position])
        }

        override fun getItemCount() = listChamps.size

        fun addData(list: List<TraitDetailModel.Champion>) {
            listChamps.clear()
            listChamps.addAll(map(list))
            notifyDataSetChanged()
        }

        private fun map(list: List<TraitDetailModel.Champion>): List<ViewBinderModel> {
            val listViewBinderModel = mutableListOf<ViewBinderModel>()
            list.forEach {
                listViewBinderModel.add(
                    ViewBinderModel(
                        champ = it,
                        itemClickLiveData = itemClickLiveData
                    )
                )
            }
            return listViewBinderModel
        }

        data class ViewBinderModel(
            val champ: TraitDetailModel.Champion,
            val itemClickLiveData: MutableLiveData<TraitDetailModel.Champion>
        ) {
            fun onClick() {
                itemClickLiveData.value = champ
            }
        }
    }
}