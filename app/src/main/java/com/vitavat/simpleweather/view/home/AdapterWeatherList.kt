package com.vitavat.simpleweather.view.home

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vitavat.simpleweather.R
import com.vitavat.simpleweather.data.local.Constanst
import com.vitavat.simpleweather.databinding.ItemWeatherListBinding
import com.vitavat.simpleweather.utils.DateManage.timeStampToDateFormat
import com.vitavat.simpleweather.utils.SingleLiveData
import com.vitavat.simpleweather.utils.setImageView
import com.vitavat.simpleweather.vo.model.response.Daily
import com.vitavat.simpleweather.vo.model.response.Hourly
import java.util.*

class AdapterWeatherList(
    private var mContext: Context,
    private var mListWeather: ArrayList<Hourly>,
    private var mOnClickList: SingleLiveData<String>
) : RecyclerView.Adapter<AdapterWeatherList.ViewHolder>() {

    override fun getItemCount(): Int {
        return mListWeather.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_weather_list, parent, false
        ) as ItemWeatherListBinding

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindText(holder, position)

        holder.binding.root.setOnClickListener {
            //mOnClickList.value = mListProduct[position].bookingUsername
        }
    }


    private fun bindText(holder: ViewHolder, position: Int) {
        holder.binding.tvTime.text = timeStampToDateFormat(mListWeather[position].dt.toLong())

        holder.binding.tvCurrentTemperature.text =
            String.format(
                mContext.resources.getString(R.string.format_number),
                mListWeather[position].temp
            )

       mContext.setImageView(holder.binding.ivWeather,mListWeather[position].weather[0].icon)
    }

    class ViewHolder(internal var binding: ItemWeatherListBinding) :
        RecyclerView.ViewHolder(binding.root)
}
