package com.free.newtft.features.dialog_item.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.free.newtft.R
import com.free.newtft.databinding.DialogDetailWeaponBinding
import com.free.newtft.features.dialog_item.viewmodel.WeaponDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DialogItemDetailFragment : DialogFragment() {

    lateinit var binding: DialogDetailWeaponBinding
    private val weaponDetailViewModel: WeaponDetailViewModel by viewModel()
    private var nameWeapon = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDetailWeaponBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = weaponDetailViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initView() {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.layout_bg_dialog)
        binding.dismissDialog.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun initData() {
        arguments?.let {
            nameWeapon = it.getString(NAME_WEAPON_EXTRA).toString()
            weaponDetailViewModel.getWeaponDetail(nameWeapon)
        }
    }

    companion object {
        val NAME_WEAPON_EXTRA = "name"
        fun newInstant(name: String): DialogItemDetailFragment {
            val fragment = DialogItemDetailFragment()
            fragment.arguments = Bundle().apply {
                putString(NAME_WEAPON_EXTRA, name)
            }
            return fragment
        }
    }
}