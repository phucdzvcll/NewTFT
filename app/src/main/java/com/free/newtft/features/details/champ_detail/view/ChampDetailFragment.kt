package com.free.newtft.features.details.champ_detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.free.common_android.BaseFragment
import com.free.newtft.databinding.FragmentChampDetailBinding
import com.free.newtft.features.details.champ_detail.viewmodel.ChampDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ChampDetailFragment : BaseFragment() {

    private lateinit var detailFragmentBinding: FragmentChampDetailBinding
    private val detailViewModel: ChampDetailViewModel by viewModel()
    private var champId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        champId = getChampIdFromBundle(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailFragmentBinding = FragmentChampDetailBinding.inflate(inflater, container, false)
        return detailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        champId?.let {
            detailViewModel.getChampDetail(it)
        }
    }

    companion object {
        private const val ID_EXTRA = "id"

        fun newInstance(champId: String): ChampDetailFragment {
            val fragment = ChampDetailFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, champId)
            fragment.arguments = bundle
            return fragment
        }

        fun getChampIdFromBundle(fragment: ChampDetailFragment): String? {
            fragment.arguments?.let {
                if (it.containsKey(ID_EXTRA)) {
                    return it.getString(ID_EXTRA)
                }
            }
            return null
        }
    }
}