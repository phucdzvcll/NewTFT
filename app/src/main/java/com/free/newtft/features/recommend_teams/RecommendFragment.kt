package com.free.newtft.features.recommend_teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.common_android.BaseFragment
import com.free.domain.entities.TeamsRecommendEntity
import com.free.newtft.databinding.FragmentRecommendBinding
import org.koin.android.viewmodel.ext.android.viewModel


class RecommendFragment : BaseFragment() {
    private lateinit var fragmentRecommendBinding: FragmentRecommendBinding
    private val teamsRecommendViewModel: TeamsRecommendViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        teamsRecommendViewModel.getListTeamsRecommend()
        teamsRecommendViewModel.test()
        fragmentRecommendBinding = FragmentRecommendBinding.inflate(inflater, container, false)
        fragmentRecommendBinding.lifecycleOwner = viewLifecycleOwner
        fragmentRecommendBinding.teamsRecommendViewModel = teamsRecommendViewModel
        return fragmentRecommendBinding.root
    }

    object Biding {
        @BindingAdapter("loadData")
        @JvmStatic
        fun load(
            recyclerView: RecyclerView,
            teamsRecommendEntity: List<TeamsRecommendEntity>
        ) {
            val adapterTeamRecommend = AdapterTeamRecommend()
            adapterTeamRecommend.addData(teamsRecommendEntity)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapterTeamRecommend
        }
    }

    companion object {
        const val screenName: String = "recommend"
        fun newInstant(): RecommendFragment {
            return RecommendFragment()
        }
    }
}