package com.free.newtft.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.free.common_android.BaseFragment
import com.free.newtft.databinding.FragmentShowBinding

class ShowFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentShowBinding.inflate(inflater, container, false).root
    }

    companion object {
        const val screenName: String = "show"
        fun newInstant(): ShowFragment {
            return ShowFragment()
        }
    }
}