package com.free.newtft.features.main.recommend_teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.common_android.BaseFragment
import com.free.common_android.navigation.NavigateAction
import com.free.domain.entities.TeamsRecommendEntity
import com.free.newtft.databinding.FragmentRecommendBinding
import com.free.newtft.features.details.DetailActivity
import com.free.newtft.features.main.recommend_teams.champ_detail_dialog.ChampDetailDialogFragment
import com.free.newtft.features.main.recommend_teams.viewmodel.ChampDialogShareViewModel
import com.free.newtft.features.main.recommend_teams.viewmodel.TeamsRecommendViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class RecommendFragment : BaseFragment() {
    private lateinit var fragmentRecommendBinding: FragmentRecommendBinding
    private val teamsRecommendViewModel: TeamsRecommendViewModel by viewModel()
    private val champDialogShareViewModel: ChampDialogShareViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        teamsRecommendViewModel.getListTeamsRecommend()
        fragmentRecommendBinding = FragmentRecommendBinding.inflate(inflater, container, false)
        fragmentRecommendBinding.lifecycleOwner = viewLifecycleOwner
        fragmentRecommendBinding.teamsRecommendViewModel = teamsRecommendViewModel
        return fragmentRecommendBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        champDialogShareViewModel.itemClickLiveData.observe(viewLifecycleOwner, {
            navigateTo(TeamRecommendAction(it, DetailActivity.ItemType.CHAMP))
        })
    }

    private fun initView() {
        val adapterTeamRecommend = AdapterTeamRecommend(this)
        adapterTeamRecommend.itemClickLiveData.observe(viewLifecycleOwner, {
            val dialog = ChampDetailDialogFragment.newInstant(it.id)
            dialog.show(childFragmentManager, "ChampDetailDialogFragment")
        })
        fragmentRecommendBinding.recyclerView.layoutManager =
            LinearLayoutManager(fragmentRecommendBinding.recyclerView.context)
        fragmentRecommendBinding.recyclerView.adapter = adapterTeamRecommend
    }

    data class TeamRecommendAction(
        val id: String,
        val itemType: DetailActivity.ItemType
    ) : NavigateAction.ToAction()

    object Biding {
        @BindingAdapter("loadData")
        @JvmStatic
        fun load(
            recyclerView: RecyclerView,
            teamsRecommendEntity: List<TeamsRecommendEntity>
        ) {
            (recyclerView.adapter as AdapterTeamRecommend).addData(teamsRecommendEntity)
        }
    }

    companion object {
        const val screenName: String = "recommend"
        fun newInstant(): RecommendFragment {
            return RecommendFragment()
        }
    }
}