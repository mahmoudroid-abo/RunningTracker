package com.mahmoudroid.runningtracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahmoudroid.runningtracker.R
import com.mahmoudroid.runningtracker.db.Run
import com.mahmoudroid.runningtracker.other.TrackingUtility
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.measureTime

class RunAdapter : RecyclerView.Adapter<RunAdapter.RunViewHolder>() {

    val diffCallback = object : DiffUtil.ItemCallback<Run>() {
        override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Run>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        return RunViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_run,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(run.img)
                .into(ivRunImage)

            val calender = Calendar.getInstance().apply {
                timeInMillis = run.timeStamp
            }

            val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            tvDate.text = dateFormat.format(calender.time)


            val avgSpeed = "${run.avgSpeedInKMH}km/h"
            tvAvgSpeed.text = avgSpeed

            val distanceInKm = "${run.distanceInMeters / 1000f}km"
            tvDistance.text = distanceInKm

            tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

            val caloriesBurned = "${run.caloriesBurned}kcal"
            tvCalories.text = caloriesBurned
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}