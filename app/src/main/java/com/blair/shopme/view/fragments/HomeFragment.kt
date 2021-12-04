package com.blair.shopme.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.blair.shopme.R
import com.blair.shopme.databinding.FragmentHomeBinding
import com.blair.shopme.view.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //firebase Authentication
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //force display the menu
        setHasOptionsMenu(true)


        auth = Firebase.auth
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_action_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //Giving the Action bar items functionality
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.user_logout_me -> {
                logUserOut()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun logUserOut() {
        Firebase.auth.signOut()
        startActivity(Intent(requireActivity(), MainActivity::class.java))
    }

}