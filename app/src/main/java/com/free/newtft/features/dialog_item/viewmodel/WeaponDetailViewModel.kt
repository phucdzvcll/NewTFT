package com.free.newtft.features.dialog_item.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.entities.WeaponDetailEntity
import com.free.domain.usecases.weapon_detail.GetWeaponDetailUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeaponDetailViewModel(
    private val appDispatchers: AppDispatchers,
    private val getWeaponDetailUseCase: GetWeaponDetailUseCase
) : BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val weaponDetailLiveData = MutableLiveData<WeaponDetailEntity>()
    private var job: Job? = null

    fun getWeaponDetail(name: String) {
        isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.io) {
                getWeaponDetailUseCase.execute(GetWeaponDetailUseCase.WeaponDetailUseCaseParam(name))
            }
            result.either({

            }, {
                weaponDetailLiveData.value = it
                isLoading.value = false
            })
        }
    }


}