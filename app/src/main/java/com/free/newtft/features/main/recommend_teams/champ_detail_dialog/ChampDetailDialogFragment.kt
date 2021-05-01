package com.free.newtft.features.main.recommend_teams.champ_detail_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.free.newtft.R
import com.free.newtft.databinding.DialogDetailChampBinding
import com.free.newtft.features.main.recommend_teams.viewmodel.ChampDialogViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ChampDetailDialogFragment : DialogFragment() {

    lateinit var binding: DialogDetailChampBinding
    private val champDialogViewModel: ChampDialogViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDetailChampBinding.inflate(inflater, container, false)
        binding.viewModel = champDialogViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        dialog?.window?.setBackgroundDrawableResource(R.drawable.layout_bg_dialog)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        arguments?.let {
            champDialogViewModel.getChampDialog(it.getString(CHAMP_ID).toString())
        }
    }

    companion object {
        val CHAMP_ID = "id"
        fun newInstant(id: String): ChampDetailDialogFragment {
            val fragment = ChampDetailDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(CHAMP_ID, id)
            }
            return fragment
        }

    }

}