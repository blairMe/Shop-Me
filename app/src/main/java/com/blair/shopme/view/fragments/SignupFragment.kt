package com.blair.shopme.view.fragments

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupFragment : Fragment() {

    //viewBinding
    private lateinit var binding : FragmentSignupBinding

    //firebase
    private lateinit var auth : FirebaseAuth


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
            findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
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
                        val user = auth.currentUser
                        updateUI(user)
                        findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                    } else {
                        Log.d("Fail", "Failed to create user")
                        Toast.makeText(requireContext(), "Failed to create user, please try again", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }



}