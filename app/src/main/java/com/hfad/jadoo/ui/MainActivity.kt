package com.hfad.jadoo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import com.hfad.jadoo.mainActivityViewModel.MainActivityViewModel
import com.hfad.jadoo.R

class MainActivity : AppCompatActivity() {
    private lateinit var mainactivityviewmodel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lo_gin.setOnClickListener {
            startActivity(Intent(this,login::class.java))
            finish()
        }
        mainactivityviewmodel=ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainactivityviewmodel.numOfOnlineHealersLiveData.observe(this, Observer {
            num_of_online_Healer.text=it.value
        })
        mainactivityviewmodel.numOfOnlineUsersLiveData.observe(this, Observer {
            num_of_online_user.text=it.value
        })
      //  mainactivityviewmodel.numOfOfflineUsersLiveData.observe(this, Observer {
       //     num_of_offline_user.text=it
      //  })
        mainactivityviewmodel.numOfOfflineHealersLiveData.observe(this, Observer {
            num_of_offline_healer.text=it.value
        })
        on_click.setOnClickListener {
            mainactivityviewmodel.pushToDatabase()
        }





    }
}