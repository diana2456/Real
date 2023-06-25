package com.example.realestateagency1.ui.fragment.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.example.realestateagency1.R
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.local.result.Status
import com.example.realestateagency1.data.model.addUser
import com.example.realestateagency1.databinding.FragmentRegistrationBinding
import com.example.realestateagency1.ui.util.Pref
import com.example.realestateagency1.ui.util.showToast

class RegistrationFragment : Fragment() {

    private lateinit var binding : FragmentRegistrationBinding
    private val viewModel : RegistrationViewModel by viewModel()
    private lateinit var user: addUser
    private var login = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        onViewlistener()
        return binding.root
    }

    private fun onViewlistener() {
        binding.voite.setOnClickListener {
            onViewModel()
        }
    }

    private fun onViewModel() {
        viewModel.loading.observe(requireActivity()) {
            binding.progresBar.isVisible = it
        }
        user = addUser(login = binding.name.text.toString(),password = binding.emailPochta.text.toString())
        viewModel.addUser(user).observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    add(it)
                    login = it.data?.login!!
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(true)
                    Log.i("olio", "initViewModel:${it.message}")
                }
                Status.LOADING -> viewModel.loading.postValue(true)
            }
        }
    }

    private fun add(data: Resource<addUser>) {
        if (data.data != null) {
            user.login = data.data.login
            user.login = data.data.login
            Log.i("oko", "add:${data.data} ")
            val pref = Pref(requireContext())
            pref.setBoardingShowed(true)
            findNavController().navigate(R.id.navigation_home)
        } else {
            showToast(requireContext(), "Заполните все поля!")
        }
    }
}