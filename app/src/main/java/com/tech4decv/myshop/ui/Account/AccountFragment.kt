package com.tech4decv.myshop.ui.Account

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.tech4decv.myshop.R

class AccountFragment : Fragment() {
    private lateinit var viewModel: AccountViewModel
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()){ result->
        if(result != null){
            if(result.resultCode== Activity.RESULT_OK){
                showProfile()
            }else{
                //an issue occurred
                Toast.makeText(requireContext(),"An error Occurred, Try again",Toast.LENGTH_LONG).show()
            }
        }

        }

    private fun showProfile() {
        //User successfully signs in, so show profile fragment
        childFragmentManager.beginTransaction()
            .replace(R.id.container, ProfileFragment())
            .commitNow()

        //makes visibility of the signIn layout container(first linear layout in xml) to disappear
        container.visibility = View.GONE
    }

    private lateinit var container: LinearLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        container = view.findViewById(R.id.sign_in_layout)
        var user =  FirebaseAuth.getInstance().currentUser
        if(user!= null){
            showProfile()
        }
        val signInBtn : Button = view.findViewById(R.id.sign_in_btn)

        signInBtn.setOnClickListener {
            val providers  = listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
            )
            val intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            //starts authentication flow
            signInLauncher.launch(intent)
        }
    }
}