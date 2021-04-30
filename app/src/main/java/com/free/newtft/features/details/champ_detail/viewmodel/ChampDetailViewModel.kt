package com.free.newtft.features.details.champ_detail.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.entities.TraitDetailEntity
import com.free.domain.usecases.show_champ.GetChampDetailUseCase
import com.free.domain.usecases.show_champ.GetTraitsDetailUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChampDetailViewModel(
    private val appDispatchers: AppDispatchers,
    private val getTraitsDetailUseCase: GetTraitsDetailUseCase,
    private val getChampDetailUseCase: GetChampDetailUseCase
) : BaseViewModel() {

    private var detailChampModel = createDetailChampModel()
    val champDetailLiveData: MutableLiveData<DetailChampModel> = MutableLiveData()

    var champDetailJob: Job? = null
    fun getChampDetail(id: String) {
        updateData(detailChampModel)
        champDetailJob?.cancel()
        champDetailJob = viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.main) {
                getChampDetailUseCase.execute(GetChampDetailUseCase.GetChampDetailUseCaseParam(id))
            }
            result.either({

            }, {
                updateData(
                    detailChampModel.copy(
                        id = it.id,
                        name = it.name,
                        items = it.items,
                        cost = it.cost
                    )
                )
                getTraitsDetail(it.traits)
            })
        }
    }

    private fun getTraitsDetail(traits: List<String>) {
        viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.main) {
                getTraitsDetailUseCase.execute(
                    GetTraitsDetailUseCase.GetTraitsDetailUseCaseParams(
                        traits
                    )
                )
            }
            result.either({
                Log.d("x", "hehe")
            }, {
                updateData(detailChampModel.copy(traits = it))
            })
        }
    }

    private fun updateData(model: DetailChampModel) {
        detailChampModel = model
        champDetailLiveData.value = model
    }

    private fun createDetailChampModel() = DetailChampModel(
        id = "",
        name = "",
        items = listOf(),
        cost = 0,
        traits = listOf()
    )

    data class DetailChampModel(
        val id: String,
        val cost: Int,
        val name: String,
        val items: List<String>,
        val traits: List<TraitDetailEntity>
    )

}