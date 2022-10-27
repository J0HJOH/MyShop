package com.tech4decv.myshop.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tech4decv.myshop.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var notificationsViewModel : NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         notificationsViewModel =
             ViewModelProvider(this)[NotificationsViewModel::class.java]

        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listOfNotification = notificationsViewModel.getAllNotifications()

        binding.listOfNotifications.layoutManager = LinearLayoutManager(requireContext())
        binding.listOfNotifications.adapter = NotificationAdapter(requireContext(),listOfNotification)
        val dividerItemDecoration = DividerItemDecoration(requireContext(),RecyclerView.HORIZONTAL)
        binding.listOfNotifications.addItemDecoration(dividerItemDecoration)

    }


}