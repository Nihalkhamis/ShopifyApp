package com.gradle.shopifyapp.address.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentAddressBinding
import com.gradle.shopifyapp.model.Addresse
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.payment.view.OrderConfirmationFragment
import com.gradle.shopifyapp.payment.view.PaymentActivity
import com.gradle.shopifyapp.settings.ShowAllAddresses.view.SettingsAddressAdapter
import com.gradle.shopifyapp.settings.ShowAllAddresses.viewmodel.SettingsAddressViewModel
import com.gradle.shopifyapp.settings.ShowAllAddresses.viewmodel.SettingsAddressViewModelFactory
import com.gradle.shopifyapp.settings.addAddress.view.OnAddressItemClickListener
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference

class AddressFragment : Fragment(), OnAddressItemClickListener {
    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    lateinit var settingsAddressAdapter: SettingsAddressAdapter
    lateinit var vmFactory: SettingsAddressViewModelFactory
    lateinit var settingsAddressViewModel: SettingsAddressViewModel

    lateinit var preference: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
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

        settingsAddressViewModel =
            ViewModelProvider(this, vmFactory).get(SettingsAddressViewModel::class.java)

        setAdapter()
        setUpSwipe()
        settingsAddressViewModel.liveDataAddressesList.observe(viewLifecycleOwner) {
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

        //(requireActivity() as PaymentActivity).

        binding.backBtn.setOnClickListener {
           // findNavController(this)?.navigate(R.id.fragmentToMain)
        }

        binding.orderBtn.setOnClickListener {
            ///findNavController(this)?.navigate(R.id.fragmentToAddAddress)
        }

        binding!!.paymentBtn.setOnClickListener {
            val line_items = (requireActivity() as PaymentActivity).lineItems
            val total_prices = (requireActivity() as PaymentActivity).totalPrice
            findNavController(this)?.navigate(R.id.addresstopayment)

        }
    }

    private fun setAdapter() {

        settingsAddressAdapter = SettingsAddressAdapter(requireContext(), ArrayList(), this)
        binding.addressRow.adapter = settingsAddressAdapter

        settingsAddressViewModel.getAllAddresses(
            requireContext(),
            preference.getData(Constants.USERID)!!
        )

    }

    override fun onClick(address: Addresse) {

    }

    private fun setUpSwipe() {
        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                //helps me to move something in recyclerView, takes 2 params the first one about the movement of the component up or down and the second one about the movement to right or left
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedProductPosition =
                        viewHolder.adapterPosition //position of swiped item in recyclerView
                    val swipedProduct: Addresse =
                        settingsAddressAdapter.getAddressId(swipedProductPosition)!!
                    settingsAddressViewModel.deleteAddress(
                        requireContext(),
                        preference.getData(Constants.USERID)!!,
                        swipedProduct.id.toString()
                    )

                }
            }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.addressRow)
    }

    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }


}