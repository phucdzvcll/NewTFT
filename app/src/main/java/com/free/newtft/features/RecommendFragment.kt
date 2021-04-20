package com.free.newtft.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.free.common_android.BaseFragment
import com.free.newtft.databinding.FragmentRecommendBinding


class RecommendFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentRecommendBinding.inflate(inflater, container, false).root
    }

    companion object {
        const val screenName: String = "recommend"
        fun newInstant(): RecommendFragment {
            return RecommendFragment()
        }
    }
}