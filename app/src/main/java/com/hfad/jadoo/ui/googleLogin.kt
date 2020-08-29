package com.hfad.jadoo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hfad.jadoo.R
import kotlinx.android.synthetic.main.activity_google_login.*

class googleLogin : AppCompatActivity() {
    var googleSignInClient:GoogleSignInClient?=null
    val RC_SIGN_IN=1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)
      //  Email_signup.setOnClickListener {
      //      createEmailId()
      //  }

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        google_sign.setOnClickListener {
            var signInIntent=googleSignInClient?.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
        }

    }
    fun createEmailId() {
        var email = Email.text.toString()
        var password = Password.text.toString()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun firebaseAuthwithGoogle(acct:GoogleSignInAccount?) {
        var credential = GoogleAuthProvider.getCredential(acct?.idToken,null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this,"signup success",Toast.LENGTH_LONG)
                }
            }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==RC_SIGN_IN){
            var task=GoogleSignIn.getSignedInAccountFromIntent(data)
            var account=task.getResult(ApiException::class.java)
                firebaseAuthwithGoogle(account)

        }
    }
}