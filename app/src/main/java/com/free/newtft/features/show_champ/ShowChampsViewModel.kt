package com.free.newtft.features.show_champ

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.common_jvm.exception.Failure
import com.free.domain.entities.ListChampEntity
import com.free.domain.usecases.base.UseCaseParams
import com.free.domain.usecases.show_champ.GetListChampsUseCase
import com.toast.comico.vn.common_jvm.functional.Either
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowChampsViewModel(
    private val getListChampsUseCase: GetListChampsUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {
    var jobListChamps: Job? = null
    val listChampsLiveData: MutableLiveData<ListChampEntity> = MutableLiveData()

    private fun getListChamps() {
        jobListChamps?.cancel()
        jobListChamps = viewModelScope.launch(appDispatchers.main) {
            val itemResult: Either<Failure, ListChampEntity> =
                withContext(appDispatchers.io) {
                    getListChampsUseCase.execute(UseCaseParams.Empty)
                }
            itemResult.either({ failure -> //do something
            }, { result -> listChampsLiveData.value = result })
        }
    }

    init {
        getListChamps()
    }
}