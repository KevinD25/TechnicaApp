package com.davis.kevin.technicav2.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.davis.kevin.technicav2.models.Praesidium
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainRepository {
    private val apiService = RetrofitManager.apiService

    val praesidiumSuccessLiveData = MutableLiveData<MutableList<Praesidium>>()
    val praesidiumFailureLiveData = MutableLiveData<Boolean>()

    /*
    this fun is suspend fun means it will execute in different thread
     */
    suspend fun getPraesidium() {

        try {

            //here api calling became so simple just 1 line of code
            //there is no callback needed

            val response = apiService.getPraesidium()

            Log.d(TAG, "$response")

            if (response.isSuccessful) {
                Log.d(TAG, "SUCCESS")
                Log.d(TAG, "${response.body()}")
                praesidiumSuccessLiveData.postValue(response.body())

            } else {
                Log.d(TAG, "FAILURE")
                Log.d(TAG, "${response.body()}")
                praesidiumFailureLiveData.postValue(true)
            }

        } catch (e: UnknownHostException) {
            Log.e(TAG, e.message)
            //this exception occurs when there is no internet connection or host is not available
            //so inform user that something went wrong
            praesidiumFailureLiveData.postValue(true)
        } catch (e: SocketTimeoutException) {
            Log.e(TAG, e.message)
            //this exception occurs when time out will happen
            //so inform user that something went wrong
            praesidiumFailureLiveData.postValue(true)
        } catch (e: Exception) {
            Log.e(TAG, e.message)
            //this is generic exception handling
            //so inform user that something went wrong
            praesidiumFailureLiveData.postValue(true)
        }

    }

    companion object {
        val TAG = MainRepository::class.java.simpleName
    }
}