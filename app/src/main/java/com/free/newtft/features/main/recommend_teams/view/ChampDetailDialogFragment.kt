package com.free.newtft.features.main.recommend_teams.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.DialogFragment
import com.free.newtft.R
import com.free.newtft.databinding.DialogDetailChampBinding
import com.free.newtft.features.main.recommend_teams.viewmodel.ChampDialogShareViewModel
import com.free.newtft.features.main.recommend_teams.viewmodel.ChampDialogViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ChampDetailDialogFragment : DialogFragment() {

    lateinit var binding: DialogDetailChampBinding
    private val champDialogViewModel: ChampDialogViewModel by viewModel()
    private val champDialogShareViewModel: ChampDialogShareViewModel by sharedViewModel()
    private var champId: String = ""

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
        initView()
    }

    private fun initView() {
        binding.btnClickMore.setOnClickListener {
            champDialogShareViewModel.itemClickLiveData.value = champId
            dialog?.dismiss()
        }
        binding.dismissDialog.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun initData() {
        arguments?.let {
            champId = it.getString(CHAMP_ID).toString()
            champDialogViewModel.getChampDialog(champId)
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

    object Binding {
        @BindingAdapter("border")
        @JvmStatic
        fun setBorder(
            imageView: ImageView,
            cost: Int
        ) {
            when (cost) {
                1 -> imageView.setBackgroundResource(R.drawable.border_gray)
                2 -> imageView.setBackgroundResource(R.drawable.border_green)
                3 -> imageView.setBackgroundResource(R.drawable.border_blue)
                4 -> imageView.setBackgroundResource(R.drawable.border_pink)
                5 -> imageView.setBackgroundResource(R.drawable.border_gold)
            }
        }
    }

}