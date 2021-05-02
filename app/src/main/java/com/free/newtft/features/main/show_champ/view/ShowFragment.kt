package com.free.newtft.features.main.show_champ.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.free.common_android.BaseFragment
import com.free.common_android.navigation.NavigateAction
import com.free.domain.entities.ChampsEntity
import com.free.newtft.databinding.FragmentShowBinding
import com.free.newtft.features.details.DetailActivity
import com.free.newtft.features.main.show_champ.adapter.AdapterShowChamps
import com.free.newtft.features.main.show_champ.viewmodel.ShowChampsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ShowFragment : BaseFragment() {
    private lateinit var fragmentShowBinding: FragmentShowBinding
    private val showChampsViewModel: ShowChampsViewModel by viewModel()
    private val adapterShowChamps = AdapterShowChamps()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showChampsViewModel.getListChamps()
        fragmentShowBinding = FragmentShowBinding.inflate(inflater, container, false)
        fragmentShowBinding.showChampsViewModel = showChampsViewModel
        fragmentShowBinding.lifecycleOwner = viewLifecycleOwner
        return fragmentShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterShowChamps.championLiveData.observe(viewLifecycleOwner, { champ ->
            navigateTo(ShowChampNavigateToAction(champ.id, DetailActivity.ItemType.CHAMP))
        })
        fragmentShowBinding.rcvShowChamp.adapter = adapterShowChamps
    }

    object Biding {
        @BindingAdapter("data")
        @JvmStatic
        fun loadData(
            recyclerView: RecyclerView,
            champsEntity: List<ChampsEntity>
        ) {
            (recyclerView.adapter as AdapterShowChamps).setupData(champsEntity)
        }
    }

    companion object {
        const val screenName: String = "show"
        fun newInstant(): ShowFragment {
            return ShowFragment()
        }
    }

    data class ShowChampNavigateToAction(
        val id: String,
        val itemType: DetailActivity.ItemType
    ) : NavigateAction.ToAction()
}