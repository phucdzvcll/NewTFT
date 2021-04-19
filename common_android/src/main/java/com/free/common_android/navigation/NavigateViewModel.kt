package com.free.common_android.navigation

import androidx.lifecycle.MutableLiveData
import com.free.common_android.BaseViewModel

class NavigateViewModel : BaseViewModel() {

    val navigateLiveData = MutableLiveData<NavigateAction>()

    fun navigateBack() {
        navigateLiveData.value = NavigateAction.BackAction
    }

    fun navigateTo(toNavigation: NavigateAction.ToAction) {
        navigateLiveData.value = toNavigation
    }

}