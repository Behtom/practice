package com.example.practice.presentation.signup.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseFragment
import com.example.practice.data.network.ConnectionState
import com.example.practice.databinding.FragmentCreatePasswordBinding
import com.example.practice.presentation.home.HomeActivity
import com.example.practice.presentation.signup.viewmodel.SignUpVM
import com.example.practice.presentation.signup.viewmodel.SignUpVMFactory
import com.example.practice.utils.extensions.hide
import com.example.practice.utils.extensions.show
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreatePasswordFragment : BaseFragment(), View.OnClickListener {

    @Inject lateinit var factory: SignUpVMFactory
    private lateinit var binding: FragmentCreatePasswordBinding

    private val signupVM: SignUpVM by lazy {
        activity?.run { ViewModelProviders.of(this, factory)[SignUpVM::class.java] }
                ?: throw Exception("Invalid Activity")
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as BaseApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePasswordBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.onClickListener = this
            it.viewModel = signupVM
        }
        return binding.root
    }

    override fun onClick(v: View?) {
        if (signupVM.isValidPwd()) {
            binding.loaderContainer.show()
            MainScope().launch {
                when (val result = signupVM.signupUser()) {
                    is ConnectionState.Success -> {
                        Toast.makeText(requireContext(), result.response, Toast.LENGTH_LONG).show()
                        val intent = Intent(requireActivity(), HomeActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                    is ConnectionState.Error -> {
                        Toast.makeText(requireContext(), result.response, Toast.LENGTH_LONG).show()
                    }
                }
            }
            binding.loaderContainer.hide()
        } else {
            Toast.makeText(requireContext(), "Valida tu contraseña.", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance() = CreatePasswordFragment()
        val TAG: String = CreatePasswordFragment::class.java.simpleName
    }
}