package com.tech4decv.myshop.ui.Account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tech4decv.myshop.R
import com.tech4decv.myshop.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var profileBinding : FragmentProfileBinding
    private val user : FirebaseUser ?  = FirebaseAuth.getInstance().currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileBinding = FragmentProfileBinding.inflate(inflater,container,false)
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //displays name on ui
        profileBinding.userName.text = user?.displayName!!

        //shows email from firebase on ui
        profileBinding.email.text = user.email
        //loads user profile image from firebase
        Glide.with(this)
            .load(user.photoUrl)
            .into(profileBinding.profileImage)
    }

}