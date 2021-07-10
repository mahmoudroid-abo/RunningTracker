package com.mahmoudroid.runningtracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.mahmoudroid.runningtracker.repositories.MainRepository
import javax.inject.Inject

class StatisticsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    val totalTimeRun = mainRepository.getTotalTimesInMillis()
    val totalDistance = mainRepository.getTotalDistance()
    val totalBurnedCalories = mainRepository.getTotalCaloriesBurned()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()

    val runSortedByDate = mainRepository.getAllRunSortedByDate()
    val runSortedByDistance = mainRepository.getAllRunSortedByDistance()
    val runSortedByAvgSpeed = mainRepository.getAllRunSortedByAvgSpeed()
    val runSortedByTime = mainRepository.getAllRunSortedByTimeInMillis()
    val runSortedByCaloriesBurned = mainRepository.getAllRunSortedByCaloriesBurned()

}