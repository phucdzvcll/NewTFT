package com.free.newtft.features.main.recommend_teams.champ_detail_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.free.newtft.R
import com.free.newtft.databinding.DialogDetailChampBinding

class ChampDetailDialogFragment : DialogFragment() {

    lateinit var binding: DialogDetailChampBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDetailChampBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.layout_bg_dialog)
        return binding.root
    }

    companion object {

        fun newInstant() = ChampDetailDialogFragment()

    }

}