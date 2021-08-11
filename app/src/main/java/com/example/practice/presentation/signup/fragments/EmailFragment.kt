package com.example.practice.presentation.signup.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.practice.R
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseActivity
import com.example.practice.core.base.BaseFragment
import com.example.practice.data.persistence.shared_preferences.Preferences
import com.example.practice.databinding.FragmentEmailBinding
import com.example.practice.presentation.signup.viewmodel.SignUpVM
import com.example.practice.presentation.signup.viewmodel.SignUpVMFactory
import javax.inject.Inject

class EmailFragment : BaseFragment(), View.OnClickListener {

    @Inject lateinit var factory: SignUpVMFactory
    private lateinit var binding: FragmentEmailBinding
    private lateinit var preferences: Preferences

    private val signupVM: SignUpVM by lazy {
        activity?.run { ViewModelProviders.of(this, factory)[SignUpVM::class.java] }
            ?: throw Exception("Invalid Activity")
    }
    private val errorObserver = Observer<String> { value ->
        binding.inputLayoutEmail.error = value
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as BaseApplication).appComponent.inject(this)
        super.onAttach(context)
        preferences = Preferences(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmailBinding.inflate(inflater, container,false).also {
            it.lifecycleOwner = this
            it.onClickListener = this
            it.viewModel = signupVM
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupVM.signupEmailError.observe(viewLifecycleOwner, errorObserver)
    }

    override fun onClick(v: View?) {
        if (signupVM.isValidEmail()) {
            (requireActivity() as BaseActivity).nextFragment(
                CreatePasswordFragment.newInstance(),
                CreatePasswordFragment.TAG
            )
        } else {
            signupVM.setEmailError(getString(R.string.text_helper_email))
        }
    }

    companion object {
        fun newInstance() = EmailFragment()
        val TAG: String = EmailFragment::class.java.simpleName
    }
}