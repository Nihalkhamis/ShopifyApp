package com.gradle.shopifyapp.settings.currency.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentCurrencyBinding
import com.gradle.shopifyapp.model.Currency
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.settings.currency.viewmodel.CurrencyViewModel
import com.gradle.shopifyapp.settings.currency.viewmodel.CurrencyViewModelFactory
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class CurrencyFragment : Fragment(), OnCurrencyItemClickListener {

    private var _binding: FragmentCurrencyBinding? = null

    private val binding get() = _binding!!

    lateinit var currencyAdapter: CurrencyAdapter
    lateinit var vmFactory: CurrencyViewModelFactory
    lateinit var currencyViewModel: CurrencyViewModel

    lateinit var preference : MyPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preference = MyPreference.getInstance(requireContext())!!

            binding.backBtn.setOnClickListener {
                findNavController(this)?.navigate(R.id.fragmentToMain)
        }

        vmFactory = CurrencyViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )

        currencyViewModel = ViewModelProvider(this, vmFactory).get(CurrencyViewModel::class.java)
        currencyViewModel.getAllCurrencies()

        setAdapter()

    }

    private fun setAdapter() {
        currencyAdapter = CurrencyAdapter(requireContext(), ArrayList(), this)
        binding.currencyRV.adapter = currencyAdapter

        currencyViewModel.liveDataCurrenciesList.observe(viewLifecycleOwner){
            Log.d("TAG", "onCreateView: $it")
            currencyAdapter.setCurrencies(it.currencies)
        }
    }

    private fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }

    override fun onClick(currency: Currency) {
       preference.saveData(Constants.TOCURRENCY,currency.currency)
        Log.d("TAG", "onClick: currency------> ${preference.getData(Constants.TOCURRENCY)}")

        restartApp()

        //restart app
        //in splash call api with saved currency and get result and save it to shared preference
        //in adapter multiply price by this result value
    }

    private fun restartApp() {
//        val intent = Intent(
//            ApplicationProvider.getApplicationContext<Context>(),
//            MainTabsActivity::class.java
//        )
//        val mPendingIntentId: Int = Constants.FINISH
//        val mPendingIntent = PendingIntent.getActivity(
//            ApplicationProvider.getApplicationContext<Context>(),
//            mPendingIntentId,
//            intent,
//            PendingIntent.FLAG_CANCEL_CURRENT
//        )
//        val mgr = ApplicationProvider.getApplicationContext<Context>()
//            .getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        mgr[AlarmManager.RTC, System.currentTimeMillis() + 100] = mPendingIntent
//        exitProcess(0)

        val intent = Intent(requireContext(), MainTabsActivity::class.java)
        this.startActivity(intent)
        activity?.finishAffinity()
    }
}