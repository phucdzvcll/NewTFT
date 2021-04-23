package com.free.newtft.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.free.common_android.BaseFragment
import com.free.newtft.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentLoginBinding.inflate(inflater, container, false).root
    }

    companion object {
        const val screenName: String = "login"
        fun newInstant(): LoginFragment {
            return LoginFragment()
        }
    }
}