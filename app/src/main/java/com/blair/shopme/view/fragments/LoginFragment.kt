package com.blair.shopme.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.blair.shopme.R
import com.blair.shopme.databinding.FragmentLoginBinding
import com.blair.shopme.view.activities.MainActivity
import com.blair.shopme.view.activities.ShopNavActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    //view Binding
    private lateinit var binding : FragmentLoginBinding

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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
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

        //firebase
        auth = Firebase.auth

        //firebase signin
        signinUser()

        //Checking if user is already signed in
        /*val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(requireContext(), ShopNavActivity::class.java)
            startActivity(intent)
        }*/

        //Going to register screen
        val buttonMove = binding.buttonRegister
        buttonMove.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

    private fun signinUser() {
        val userEmail = binding.loginEmail.text
        val userPassword = binding.loginPassword.text
        val loginButton = view?.findViewById<Button>(R.id.buttonLogin)

        loginButton!!.setOnClickListener {
            auth.signInWithEmailAndPassword(userEmail.toString(), userPassword.toString())
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.d("Success", "Login Successful")
                        Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        updateUI(user)
                        val intent = Intent(requireContext(), ShopNavActivity::class.java)
                        startActivity(intent)
                        //findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        updateUI(null)
                        Log.i("Retry", "Please try to login again")
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

}