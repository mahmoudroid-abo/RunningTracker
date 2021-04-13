package com.mahmoudroid.runningtracker.repositories

import com.mahmoudroid.runningtracker.db.Run
import com.mahmoudroid.runningtracker.db.RunDAO
import javax.inject.Inject


class MainRepository @Inject constructor(
    val runDao: RunDAO
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)
    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunSortedByDate() = runDao.getAllRunsSortedByDate()

    fun getAllRunSortedByDistance() = runDao.getAllRunsSortedByDistanceInMeters()

    fun getAllRunSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()

    fun getAllRunSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeedInKMH()

    fun getAllRunSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeedInKMH()

    fun getTotalDistance() = runDao.getTotalDistanceInMeters()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalTimesInMillis() = runDao.getTotalTimeInMillis()

}