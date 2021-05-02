package com.free.newtft.features.dialog_item.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.entities.RecipeEntity
import com.free.domain.entities.WeaponDetailEntity
import com.free.domain.usecases.weapon_detail.GetListRecipeUseCase
import com.free.domain.usecases.weapon_detail.GetWeaponDetailUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeaponDetailViewModel(
    private val appDispatchers: AppDispatchers,
    private val getListRecipeUseCase: GetListRecipeUseCase,
    private val getWeaponDetailUseCase: GetWeaponDetailUseCase
) : BaseViewModel() {

    val weaponDetailLiveData = MutableLiveData<DialogModel>()
    val isLoading = MutableLiveData<Boolean>()
    private var weaponDetailJob: Job? = null
    private var recipeJob: Job? = null
    private var dialogModel = createDialogModel()
    fun getWeaponDetail(name: String) {
        isLoading.value = true
        weaponDetailJob?.cancel()
        weaponDetailJob = viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.io) {
                getWeaponDetailUseCase.execute(GetWeaponDetailUseCase.WeaponDetailUseCaseParam(name))
            }
            result.either({

            }, {
                updateDialogModel(
                    dialogModel.copy(
                        weaponDetailEntity = it
                    )
                )
                getListRecipe(createListIdParam(it.id))
            })

        }
    }

    private fun getListRecipe(ids: List<Int>) {
        recipeJob?.cancel()
        recipeJob = viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.io) {
                getListRecipeUseCase.execute(GetListRecipeUseCase.GetListRecipeUseCaseParam(ids))
            }
            result.either({

            }, {
                updateDialogModel(
                    dialogModel.copy(
                        listRecipes = it
                    )
                )
                isLoading.value = false
            })
        }
    }

    private fun createListIdParam(id: Int): List<Int> {
        val ids = mutableListOf<Int>()
        if (id < 1000) {
            ids.add(id / 10)
            ids.add(id % 10)
        } else {
            val newId = id - 1000
            ids.add((newId / 10) + 1000)
            ids.add((newId % 10) + 1000)
        }
        return ids
    }

    private fun updateDialogModel(model: DialogModel) {
        dialogModel = model
        weaponDetailLiveData.value = dialogModel
    }

    private fun createDialogModel() = DialogModel(
        weaponDetailEntity = WeaponDetailEntity(
            id = 0,
            name = "",
            description = "",
            shadowBonus = "",
            isShadow = false,
            imgUrl = "",
            shadowPenalty = ""
        ), listOf()
    )

    data class DialogModel(
        val weaponDetailEntity: WeaponDetailEntity,
        val listRecipes: List<RecipeEntity>
    )

}