package com.free.newtft.features.main.build_team.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.free.common_android.BaseFragment
import com.free.domain.entities.ChampOfTeamBuilderEntity
import com.free.newtft.databinding.FragmentBuildBinding
import com.free.newtft.features.main.build_team.viewbinder.ChampBuilderViewBinder
import com.free.newtft.features.main.build_team.viewbinder.TitleDetailViewBinder
import com.free.newtft.features.main.build_team.viewmodel.TeamBuilderViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class BuildFragment : BaseFragment() {

    private val teamBuilderViewModel: TeamBuilderViewModel by viewModel()
    private lateinit var binding: FragmentBuildBinding
    private val adapterBuilder = MultiTypeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        teamBuilderViewModel.getListChampBuilder()
        binding = FragmentBuildBinding.inflate(inflater, container, false)
        binding.viewModel = teamBuilderViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        activity?.let {
            val gridLayoutManager = GridLayoutManager(activity, 7)
            val spanCountLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapterBuilder.items.getOrNull(position)) {
                        is TitleDetailViewBinder.TitleModel -> 7
                        is ChampBuilderViewBinder.ViewBinderModel -> 1
                        else -> 1
                    }
                }

            }
            gridLayoutManager.spanSizeLookup = spanCountLookup
            binding.recyclerViewBuilder.layoutManager = gridLayoutManager

            val titleDetailViewBinder = TitleDetailViewBinder()
            adapterBuilder.register(titleDetailViewBinder)

            val champBuilderViewBinder = ChampBuilderViewBinder()
            adapterBuilder.register(champBuilderViewBinder)

            binding.recyclerViewBuilder.adapter = adapterBuilder
        }
    }

    object Binding {
        @BindingAdapter("loadData")
        @JvmStatic
        fun loadData(
            recyclerView: RecyclerView,
            listChampBuilder: List<ChampOfTeamBuilderEntity>
        ) {
            val titleDetailBuilderMapper = TitleDetailViewBinder.TitleDetailBuilderMapper()

            val items = mutableListOf<Any>()
            val listTrait = mutableListOf<ChampOfTeamBuilderEntity.Trait>()

            listChampBuilder.forEach { champ ->
                listTrait.addAll(champ.traits)
            }
            listTrait.distinct().forEach { trait ->
                items.add(titleDetailBuilderMapper.map(trait))
                listChampBuilder.forEach { champ ->
                    champ.traits.forEach {
                        if (it == trait) {
                            items.add(champ)
                        }
                    }
                }
            }
            recyclerView.adapter?.notifyDataSetChanged()
            (recyclerView.adapter as MultiTypeAdapter).items = items

        }
    }

    companion object {
        const val screenName: String = "build"
        fun newInstant(): BuildFragment {
            return BuildFragment()
        }
    }
}