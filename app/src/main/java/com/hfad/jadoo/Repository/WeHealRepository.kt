package com.hfad.jadoo.Repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.hfad.jadoo.Info
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object WeHealRepository {
    private val firebasedatabase: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
    fun getNumOfOnlineHealersLiveData():MutableLiveData<Info>{
        var mutableLiveData:MutableLiveData<Info>?=MutableLiveData()
        if( mutableLiveData==null){
            mutableLiveData=MutableLiveData()
        }
//        GlobalScope.launch{
//            delay(5000)
//            mutableLiveData.postValue(10)
//        }
        firebasedatabase.reference.child("numOfOnlineHealer").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    val key = p0.key!!
                    val value = p0.value?.toString() ?:"anonymous"
                    mutableLiveData.value= Info(key,value)
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }

        })
        return mutableLiveData

    }
    fun getNumOfOnlineUsersLiveData():MutableLiveData<Info>{
        var mutableLiveData:MutableLiveData<Info>?=MutableLiveData()
        if(mutableLiveData==null){
            mutableLiveData= MutableLiveData()
        }
        firebasedatabase.reference.child("numOfOnlineUser").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val key= snapshot.key!!
                    val value=snapshot.value?.toString() ?:"anonymus"
                    mutableLiveData.value=Info(key,value)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return mutableLiveData
    }

    fun getNumOfOfflineHealersLiveData():MutableLiveData<Info>{
        var mutableLiveData:MutableLiveData<Info>?=MutableLiveData()
        if(mutableLiveData==null){
            mutableLiveData= MutableLiveData()

        }
        firebasedatabase.reference.child("offlinehealer").addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
               if(snapshot.exists()){
                   val key=snapshot.key!!
                   val value=snapshot.value?.toString()?:"anonymous"
                   mutableLiveData.value=Info(key,value)
               }

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        return mutableLiveData
    }
    fun pushToDatabase(){
        firebasedatabase.reference.child( "sat").setValue("hi there")
        firebasedatabase.reference.child("hello").setValue("23")
    }


}