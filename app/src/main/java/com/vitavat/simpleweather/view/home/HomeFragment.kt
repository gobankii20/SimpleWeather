package com.vitavat.simpleweather.view.home

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitavat.simpleweather.R
import com.vitavat.simpleweather.databinding.FragmentHomeBinding
import com.vitavat.simpleweather.utils.*
import com.vitavat.simpleweather.utils.DateManage.timeStampToDateCurrent
import com.vitavat.simpleweather.vo.model.Status
import com.vitavat.simpleweather.vo.model.response.Daily
import com.vitavat.simpleweather.vo.model.response.Hourly
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModel()

    private val objectManage: ObjectManage by inject()

    private val mCheckPermission: CheckPermission by inject { parametersOf(binding.root.context) }

    private val mMapLocation: MapLocation by inject { parametersOf(binding.root.context) }

    private var mListDataOrderList = ArrayList<Hourly>()

    private val mAdapterWeatherList by lazy {
        AdapterWeatherList(binding.root.context, mListDataOrderList, viewModel.onClickItemOrderList)
    }

    companion object {
        fun newInstance(arg: String?): HomeFragment {
            val args = Bundle()
            args.putString("arg", arg)
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        mCheckPermission.checkPermissionLocation {
            initWeather()
            loadDataWeather()
            onClickSeeWeather()
        }
    }

    private fun onClickSeeWeather() {
        binding.swSeeWeather.setOnCheckedChangeListener { _, status ->
            if (!status){
                viewModel.mSeeWeatherTxt.set("Humidity")
            }else{
                viewModel.mSeeWeatherTxt.set("Temperature")
            }

            viewModel.isCheckModeWeather.set(!status)
        }
    }

    private fun initWeather() {
        binding.rvWeather.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapterWeatherList
            mAdapterWeatherList.notifyDataSetChanged()
        }

        viewModel.mWeatherListCall.value =
            objectManage.createObjWeather(mMapLocation.getCurrentLocation())
    }

    private fun loadDataWeather() {
        viewModel.mResponseWeather.observe(viewLifecycleOwner, Observer {
            binding.loadResource = it
            when (it.status) {
                Status.SUCCESS -> {
                    binding.dataResponse = it.data
                    binding.dataViewModel = viewModel

                    it.data?.let { data ->
                        viewModel.mDateCurrent.set(timeStampToDateCurrent(it.data.current.dt.toLong()))
                        viewModel.mWeatherDescription.set(it.data.current.weather[0].description)

                        requireActivity().setImageView(
                            binding.ivTemperature,
                            it.data.current.weather[0].icon
                        )

                        mListDataOrderList.clear()
                        mListDataOrderList.addAll(data.hourly)
                    } ?: kotlin.run {
                        binding.root.showMessage("ไม่พบข้อมูล")
                    }
                    mAdapterWeatherList.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    requireActivity().dialogMessage(it.message ?: "Som ting went wrong")
                }
                Status.LOADING -> print("no event")
            }
        })
    }

}