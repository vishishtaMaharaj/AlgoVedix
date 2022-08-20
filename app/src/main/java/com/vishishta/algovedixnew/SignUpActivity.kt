package com.vishishta.algovedixnew

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.vishishta.algovedixnew.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivitySignUpBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure Actionbar
        actionBar = supportActionBar!!
        actionBar.title= "Sign Up"
        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //configure ProgressDialog
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating Account...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init up firebaseAuth
        firebaseAuth= FirebaseAuth.getInstance()

        //handle click for signup
        binding.btnSignUp.setOnClickListener{
            validateData()
        }

    }

    private fun validateData() {
        //get data
        email = binding.etMobNum.toString().trim()
        password= binding.etPassword.toString().trim()

        //validate data
       if (Patterns.PHONE.matcher(email).matches())/*(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())*/
        {
            //invalid email format
            binding.etMobNum.error= "Invalid email format"
        }
        else if (TextUtils.isEmpty(password)){
            //password is not entered
            binding.etPassword.error= "Please enter your Password"
        }
        else if(password.length<6){
            binding.etPassword.error="Password must be at least 6 characters long"
        }
        else{
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        //showProgress
        progressDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
               progressDialog.dismiss()
                val firebaseUser= firebaseAuth.currentUser
                val email = firebaseUser!!.email
               Toast.makeText(this,"Account created with email $email", Toast.LENGTH_SHORT).show()

               //open profile
               startActivity(Intent(this, MyProfileActivity::class.java))
               finish()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this,"SignUp failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //go back to previous activity when back button on action bar is pressed
        return super.onSupportNavigateUp()
    }
}