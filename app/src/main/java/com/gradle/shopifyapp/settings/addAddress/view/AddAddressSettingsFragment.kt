package com.gradle.shopifyapp.settings.addAddress.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentAddAddressSettingsBinding
import com.gradle.shopifyapp.model.Addresse
import com.gradle.shopifyapp.model.Customer
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.settings.addAddress.viewmodel.AddAddressSettingsViewModel
import com.gradle.shopifyapp.settings.addAddress.viewmodel.AddAddressSettingsViewModelFactory
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class AddAddressSettingsFragment : Fragment() {

    private var _binding: FragmentAddAddressSettingsBinding? = null
    private val binding get() = _binding!!
    lateinit var vmFactory: AddAddressSettingsViewModelFactory
    lateinit var addAddressViewModel: AddAddressSettingsViewModel
    lateinit var preference: MyPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddAddressSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vmFactory = AddAddressSettingsViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )

        preference = MyPreference.getInstance(requireContext())!!

        addAddressViewModel =
            ViewModelProvider(this, vmFactory).get(AddAddressSettingsViewModel::class.java)

        val customer = Customer()

        binding.saveBtn.setOnClickListener {
            if (binding.addressEdt.text!!.isNotEmpty() && binding.zipcodeEdt.text!!.isNotEmpty() && binding.phoneEdt.text!!.isNotEmpty() &&
                binding.cityEdt.text!!.isNotEmpty() && binding.countryEdt.text!!.isNotEmpty()
            ) {
                customer.addresses = listOf(
                    Addresse(
                        address1 = binding.addressEdt.text.toString(),
                        zip = binding.zipcodeEdt.text.toString(),
                        phone = binding.phoneEdt.text.toString(),
                        city = binding.cityEdt.text.toString(),
                        country = binding.countryEdt.text.toString()
                    )
                )
                val customerModel = CustomerModel(customer)
                addAddressViewModel.addCustomerAddress(
                    requireContext(),
                    preference.getData(Constants.USERID).toString(), customerModel
                )

//            addAddressViewModel.errorMessage.observe(viewLifecycleOwner) {
//                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
//                Log.d("TAG", "ERROR while adding address: $it")
//            }

                addAddressViewModel.loading.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        binding.progressbar.visibility = View.VISIBLE
                    } else {
                        binding.progressbar.visibility = View.GONE
                        when (Navigation.findNavController(requireView()).previousBackStackEntry?.destination?.id) {
                            R.id.settingsAddressFragment -> {
                                findNavController(this)?.navigate(R.id.fragmentToAddresses)
                            }
                            else -> {
                                requireActivity().finish()
                            }
                        }
                    }
                })
            } else {
                Toast.makeText(requireContext(), "Please fill all fields !", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.cancelBtn.setOnClickListener {
            when (Navigation.findNavController(requireView()).previousBackStackEntry?.destination?.id) {
                R.id.settingsAddressFragment -> {
                    findNavController(this)?.navigate(R.id.fragmentToAddresses)
                }
                else -> {
                    requireActivity().finish()
                }
            }
        }
    }

    private fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }
}