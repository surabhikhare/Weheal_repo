package com.hfad.jadoo.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.hfad.jadoo.R
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    private lateinit var providers:List<AuthUI.IdpConfig>
    private val MY_REQUEST_CODE:Int=1000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
         providers= listOf(
             AuthUI.IdpConfig.EmailBuilder().build(),
             AuthUI.IdpConfig.PhoneBuilder().build(),
             AuthUI.IdpConfig.GoogleBuilder().build(),
             AuthUI.IdpConfig.FacebookBuilder().build())

        showSignInOption()
        sign_out.setOnClickListener {
            AuthUI.getInstance().signOut(this).addOnCompleteListener {
                sign_out.isEnabled=false
                showSignInOption()
            }
                .addOnFailureListener {
                    Toast.makeText(this,"failed",Toast.LENGTH_LONG).show()
                }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==MY_REQUEST_CODE){
            var response=IdpResponse.fromResultIntent(data)
            if(resultCode== Activity.RESULT_OK) {
                var user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, "" + user!!.email, Toast.LENGTH_SHORT).show()
                sign_out.isEnabled=true
            }
            else{
                Toast.makeText(this,""+response!!.error!!.message,Toast.LENGTH_SHORT).show()
            }


        }
    }
    fun showSignInOption(){
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build(),MY_REQUEST_CODE)
    }
}