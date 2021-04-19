package com.free.common_android

import androidx.fragment.app.Fragment
import com.free.common_android.navigation.NavigateAction
import com.free.common_android.navigation.NavigateViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment : Fragment() {
    private val navigateViewModel by sharedViewModel<NavigateViewModel>()

    fun navigateTo(action: NavigateAction.ToAction) {
        navigateViewModel.navigateTo(action)
    }

    fun navigateBack() {
        navigateViewModel.navigateBack()
    }
}