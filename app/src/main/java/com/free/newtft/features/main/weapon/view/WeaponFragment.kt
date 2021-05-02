package com.free.newtft.features.main.weapon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.free.common_android.BaseFragment
import com.free.domain.entities.WeaponEntity
import com.free.newtft.databinding.FragmentWaponBinding
import com.free.newtft.features.main.weapon.adapter.WeaponAdapter
import com.free.newtft.features.main.weapon.viewmodel.WeaponViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class WeaponFragment : BaseFragment() {

    private lateinit var binding: FragmentWaponBinding
    private val weaponViewModel: WeaponViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        weaponViewModel.getListWeapon(false)
        binding = FragmentWaponBinding.inflate(inflater, container, false)
        binding.viewModel = weaponViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val adapter = WeaponAdapter()
        binding.recyclerviewWeapon.layoutManager =
            GridLayoutManager(binding.recyclerviewWeapon.context, 3)
        binding.recyclerviewWeapon.adapter = adapter
    }

    object Binding {
        @BindingAdapter("loadWeapon")
        @JvmStatic
        fun loadWeapon(
            recyclerView: RecyclerView,
            listWeapon: List<WeaponEntity>
        ) {
            (recyclerView.adapter as WeaponAdapter).addData(listWeapon)
        }
    }

    companion object {
        const val screenName: String = "weapon"
        fun newInstant(): WeaponFragment {
            return WeaponFragment()
        }
    }
}