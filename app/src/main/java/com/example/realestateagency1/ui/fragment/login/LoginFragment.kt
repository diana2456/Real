package com.example.realestateagency1.ui.fragment.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.example.realestateagency1.R
import com.example.realestateagency1.data.local.result.Resource
import com.example.realestateagency1.data.local.result.Status
import com.example.realestateagency1.data.model.UserResponse
import com.example.realestateagency1.databinding.FragmentLoginBinding
import com.example.realestateagency1.ui.fragment.registration.OnRegistrationListener
import com.example.realestateagency1.ui.util.Pref
import com.example.realestateagency1.ui.util.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var registrationListener: OnRegistrationListener? = null
    private val viewModel: LoginViewModel by viewModel()
    private var searchJob: Job? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRegistrationListener) {
            registrationListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.voite.setOnClickListener {
            onLoginClicked()
        }
        binding.newAcoynt.setOnClickListener {
            findNavController().navigate(R.id.radisFragment)
        }
    }

    private fun onLoginClicked() {
        val email = binding.email.text.toString()
        if (email.isNotEmpty()) {
            searchUser(email)
        } else {
            showToast(requireContext(), "Введите мя пользователя")
        }
    }

    private fun searchUser(email: String) {
        searchJob?.cancel()

        searchJob = lifecycleScope.launch {
            try {
                viewModel.loading.postValue(true)
                val result = withContext(Dispatchers.IO) {
                    viewModel.searchUser(email).observe(requireActivity()) {
                        when(it.status) {
                            Status.SUCCESS ->{}
                            Status.ERROR ->{}
                            Status.LOADING ->{}
                        }

                    }
                }
            } finally {
                viewModel.loading.postValue(false)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        searchJob?.cancel()
    }
}