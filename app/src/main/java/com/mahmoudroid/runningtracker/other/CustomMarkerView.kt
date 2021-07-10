package com.mahmoudroid.runningtracker.other

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.mahmoudroid.runningtracker.db.Run
import kotlinx.android.synthetic.main.marker_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    val runs: List<Run>,
    context: Context,
    layoutId: Int
) : MarkerView(context, layoutId) {

    override fun getOffset(): MPPointF {
        return MPPointF(
            -width / 2f,
            -height.toFloat()
        )
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e == null) {
            return
        }

        val currentRunId = e.x.toInt()
        val run = runs[currentRunId]
        val calender = Calendar.getInstance().apply {
            timeInMillis = run.timeStamp
        }

        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        markerDate.text = dateFormat.format(calender.time)


        val avgSpeed = "${run.avgSpeedInKMH}km/h"
        markerAvgSpeed.text = avgSpeed

        val distanceInKm = "${run.distanceInMeters / 1000f}km"
        markerDistance.text = distanceInKm

        markerDuration.text = TrackingUtility.getFormattedStopWatchTime(
            run.timeInMillis)

        val caloriesBurned = "${run.caloriesBurned}kcal"
        markerCaloriesBurned.text = caloriesBurned
    }
}