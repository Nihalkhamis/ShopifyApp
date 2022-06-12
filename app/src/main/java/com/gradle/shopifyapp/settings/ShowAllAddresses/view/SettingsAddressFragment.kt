package com.gradle.shopifyapp.settings.ShowAllAddresses.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentSettingsAddressBinding
import com.gradle.shopifyapp.model.Addresse
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.settings.ShowAllAddresses.viewmodel.SettingsAddressViewModel
import com.gradle.shopifyapp.settings.ShowAllAddresses.viewmodel.SettingsAddressViewModelFactory
import com.gradle.shopifyapp.settings.addAddress.view.OnAddressItemClickListener
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class SettingsAddressFragment : Fragment(), OnAddressItemClickListener {

    private var _binding: FragmentSettingsAddressBinding? = null

    private val binding get() = _binding!!

    lateinit var settingsAddressAdapter: SettingsAddressAdapter
    lateinit var vmFactory: SettingsAddressViewModelFactory
    lateinit var settingsAddressViewModel: SettingsAddressViewModel

    lateinit var preference: MyPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmFactory = SettingsAddressViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )

        preference = MyPreference.getInstance(requireContext())!!

        settingsAddressViewModel = ViewModelProvider(this, vmFactory).get(SettingsAddressViewModel::class.java)

        setAdapter()
        setUpSwipe()


        settingsAddressViewModel.liveDataAddressesList.observe(viewLifecycleOwner){
            Log.d("TAG", "setAdapter: returned addresses: ${it.customer!!.addresses}")
            settingsAddressAdapter.setAddresses(it.customer!!.addresses!!)
        }

        settingsAddressViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressbar.visibility = View.VISIBLE
            } else {
                binding.progressbar.visibility = View.GONE
            }
        })

        binding.backBtn.setOnClickListener {
            findNavController(this)?.navigate(R.id.fragmentToMain)
        }

        binding.addAddressFloatBtn.setOnClickListener {
            findNavController(this)?.navigate(R.id.fragmentToAddAddress)
        }

    }

    private fun setAdapter(){

        settingsAddressAdapter = SettingsAddressAdapter(requireContext(), ArrayList(), this)
        binding.addressesRV.adapter = settingsAddressAdapter

        settingsAddressViewModel.getAllAddresses(requireContext(), preference.getData(Constants.USERID)!!)

    }

    private fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }

    override fun onClick(address: Addresse) {

    }

    private fun setUpSwipe() {
        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                //helps me to move something in recyclerView, takes 2 params the first one about the movement of the component up or down and the second one about the movement to right or left
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedProductPosition =
                        viewHolder.adapterPosition //position of swiped item in recyclerView
                    val swipedProduct: Addresse = settingsAddressAdapter.getAddressId(swipedProductPosition)!!
                    settingsAddressViewModel.deleteAddress(requireContext(), preference.getData(Constants.USERID)!!, swipedProduct.id.toString())

                }
            }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.addressesRV)
    }
}