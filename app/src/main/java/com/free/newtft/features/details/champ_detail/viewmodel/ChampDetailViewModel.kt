package com.free.newtft.features.details.champ_detail.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.usecases.show_champ.GetChampDetailUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChampDetailViewModel(
    private val appDispatchers: AppDispatchers,
    private val getChampDetailUseCase: GetChampDetailUseCase
) : BaseViewModel() {

    fun getChampDetail(id: String) {
        viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.main) {
                getChampDetailUseCase.execute(GetChampDetailUseCase.GetChampDetailUseCaseParam(id))
            }
            result.either({

            },{
                Log.d("success","hehe")
            })
        }
    }

}