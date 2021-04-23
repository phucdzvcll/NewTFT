package com.free.newtft.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.free.common_android.BaseFragment
import com.free.newtft.databinding.FragmentBuildBinding


class BuildFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBuildBinding.inflate(inflater, container, false).root
    }

    companion object {
        const val screenName: String = "build"
        fun newInstant(): BuildFragment {
            return BuildFragment()
        }
    }
}