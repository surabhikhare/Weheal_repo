package com.hfad.jadoo.mainActivityViewModel

import android.icu.text.IDNA
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.jadoo.Info
import com.hfad.jadoo.Repository.WeHealRepository


class MainActivityViewModel : ViewModel() {
    private val  numOfOnlineHealer:MutableLiveData<Info> = WeHealRepository.getNumOfOnlineHealersLiveData()
            val numOfOnlineHealersLiveData:LiveData<Info> get()=numOfOnlineHealer

    private val numOfOnlineUser:MutableLiveData<Info> = WeHealRepository.getNumOfOnlineUsersLiveData()
    val numOfOnlineUsersLiveData:LiveData<Info> get()=numOfOnlineUser

   // private val numOfOfflineUser:MutableLiveData<String> = WeHealRepository.getNumOfOfflineUsersLiveData()
   // val numOfOfflineUsersLiveData:LiveData<String> get()=numOfOfflineUser

    private val numOfOfflineHealer:MutableLiveData<Info> = WeHealRepository.getNumOfOfflineHealersLiveData()
    val numOfOfflineHealersLiveData:LiveData<Info> get()=numOfOfflineHealer
    fun pushToDatabase(){
        WeHealRepository.pushToDatabase()
    }

}