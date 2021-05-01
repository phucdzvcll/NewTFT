package com.free.newtft.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.free.common_android.BaseFragment
import com.free.newtft.databinding.FragmentWaponBinding


class WeaponFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentWaponBinding.inflate(inflater, container, false).root
    }

    companion object {
        const val screenName: String = "weapon"
        fun newInstant(): WeaponFragment {
            return WeaponFragment()
        }
    }
}