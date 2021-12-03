package com.blair.shopme.view.fragments

import android.content.Intent
import android.graphics.Insets.add
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.blair.shopme.R
import com.blair.shopme.databinding.FragmentSignupBinding
import com.blair.shopme.view.activities.MainActivity
import com.blair.shopme.view.activities.ShopNavActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupFragment : Fragment() {

    //viewBinding
    private lateinit var binding : FragmentSignupBinding

    //firebase authentication
    private lateinit var auth : FirebaseAuth

    //firestore
    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        //checks if user is already signed in
        val currentUser = auth.currentUser
        if(currentUser != null) {
            //Checking if user is already signed in
            val intent = Intent(requireContext(), ShopNavActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //firebase signup
        //Initialize firebase
        auth = Firebase.auth
        registerUser()

        //Using Sign in button(below register button)
        val buttonSignin = binding.backToSignin
        buttonSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }

    private fun registerUser() {
        //implementing firebase
        val email = binding.userSignupEmail.text
        val password = binding.userSignupPassword.text
        val buttonRegister = view?.findViewById<Button>(R.id.registerMe)

        buttonRegister!!.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                .addOnCompleteListener(requireActivity()) { task ->
                    if(task.isSuccessful) {
                        Log.d("Success", "Signup Successful")
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                        sendingToDatabase()
                        val user = auth.currentUser
                        updateUI(user)
                        //Checking if user is already signed in
                        val intent = Intent(requireContext(), ShopNavActivity::class.java)
                            startActivity(intent)
                    } else {
                        Log.d("Fail", "Failed to create user")
                        Toast.makeText(requireContext(), "Failed to create user, please try again", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    //sending user details to firestore database
    fun sendingToDatabase() {
        val userId = Firebase.auth.uid
        val firstUserName = binding.signupFirstName.text
        val secondUserName = binding.signupSecondName.text
        val userEmailAd = binding.userSignupEmail.text
        val userPassword = binding.userSignupPassword.text


        //creating the user
        val user = hashMapOf(
            "userId" to userId.toString(),
            "firstName" to firstUserName.toString(),
            "secondName" to secondUserName.toString(),
            "userEmail" to userEmailAd.toString(),
            "userPassword" to userPassword.toString()
        )



        //Adding document with generated id
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("Success Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
                Toast.makeText(requireContext(), "Success adding to firestore", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w("Firestore no success", "Error adding document", e)
                Toast.makeText(requireContext(), "Not successful adding to firestore", Toast.LENGTH_SHORT).show()
            }
        }

    private fun updateUI(user: FirebaseUser?) {

    }

}

