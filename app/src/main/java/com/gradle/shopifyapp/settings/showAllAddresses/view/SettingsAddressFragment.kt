package com.gradle.shopifyapp.settings.showAllAddresses.view

import android.app.AlertDialog
import android.content.Intent
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
import com.gradle.shopifyapp.MapsActivity
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentSettingsAddressBinding
import com.gradle.shopifyapp.model.Addresse
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.settings.SettingsActivity
import com.gradle.shopifyapp.settings.showAllAddresses.viewmodel.SettingsAddressViewModel
import com.gradle.shopifyapp.settings.showAllAddresses.viewmodel.SettingsAddressViewModelFactory
import com.gradle.shopifyapp.settings.addAddress.view.OnAddressItemClickListener
import com.gradle.shopifyapp.utils.Alert
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class SettingsAddressFragment : Fragment(), OnAddressItemClickListener {

    private var _binding: FragmentSettingsAddressBinding? = null

    private val binding get() = _binding!!

    lateinit var settingsAddressAdapter: SettingsAddressAdapter
    lateinit var vmFactory: SettingsAddressViewModelFactory
    lateinit var settingsAddressViewModel: SettingsAddressViewModel


    //for internet connection
    lateinit var connectionLiveData: ConnectionLiveData
    lateinit var dialog : AlertDialog

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


        //for internet connection
        connectionLiveData = ConnectionLiveData(requireContext())
        dialog = Alert.makeAlert(requireContext())

        connectionLiveData.observe(viewLifecycleOwner){
            if(it){
                dialog.dismiss()
                setAdapter()
                setUpSwipe()

                settingsAddressViewModel.liveDataAddressesList.observe(viewLifecycleOwner){
                    Log.d("TAG", "setAdapter: returned addresses: ${it.customer!!.addresses}")
                    Log.d("TAG", "setAdapter: returned addresses: $3")

                    settingsAddressAdapter.setAddresses(it.customer!!.addresses!!)
                }


                settingsAddressViewModel.loading.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        binding.progressbar.visibility = View.VISIBLE
                    } else {
                        binding.progressbar.visibility = View.GONE
                    }
                })


            }else{
                dialog.show()
            }
        }




        binding.backBtn.setOnClickListener {
            (requireActivity() as SettingsActivity).address = ""
//            findNavController(this)?.navigate(R.id.fragmentBack)
            findNavController(this)?.popBackStack(R.id.mainScreenFragment,false)
        }

        binding.addAddressFloatBtn.setOnClickListener {
           // findNavController(this)?.navigate(R.id.fragmentToAddAddress)
            var intent = Intent(requireContext(),MapsActivity::class.java)
            intent.putExtra(Constants.FROMSETTINGSFRAGMENT,"from settings")
            startActivity(intent)
            requireActivity().finish()
        }
    }


    override fun onDestroyView() {
        (requireActivity() as SettingsActivity).address = ""
        super.onDestroyView()
    }

    private fun setAdapter(){

        settingsAddressAdapter = SettingsAddressAdapter(requireContext(), ArrayList(), this)
        binding.addressesRV.adapter = settingsAddressAdapter
        Log.d("TAG", "setAdapter: returned addresses: $1")

        settingsAddressViewModel.getAllAddresses(requireContext(), preference.getData(Constants.USERID)!!)
        Log.d("TAG", "setAdapter: returned addresses: $2")

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
                    val swipedProduct: Addresse = settingsAddressAdapter.getAddressPosition(swipedProductPosition)!!
                    settingsAddressViewModel.deleteAddress(requireContext(), preference.getData(Constants.USERID)!!, swipedProduct.id.toString())
                    settingsAddressAdapter.deleteAddressByPosition(swipedProductPosition)

                }
            }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.addressesRV)
    }
}