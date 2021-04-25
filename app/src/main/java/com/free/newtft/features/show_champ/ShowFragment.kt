package com.free.newtft.features.show_champ

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.common_android.BaseFragment
import com.free.domain.entities.ChampsEntity
import com.free.newtft.databinding.FragmentShowBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ShowFragment : BaseFragment() {
    private lateinit var fragmentShowBinding: FragmentShowBinding
    private val showChampsViewModel: ShowChampsViewModel by viewModel()

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

    object Biding {
        @BindingAdapter("data")
        @JvmStatic
        fun loadData(
            recyclerView: RecyclerView,
            champsEntity: List<ChampsEntity>
        ) {
            val adapterShowChamps = AdapterShowChamps()
            adapterShowChamps.setupData(champsEntity)
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 5)
            recyclerView.adapter = adapterShowChamps
        }
    }

    companion object {
        const val screenName: String = "show"
        fun newInstant(): ShowFragment {
            return ShowFragment()
        }
    }
}