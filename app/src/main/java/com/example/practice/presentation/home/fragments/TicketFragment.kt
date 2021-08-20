package com.example.practice.presentation.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseFragment
import com.example.practice.data.adapters.TicketAdapter
import com.example.practice.databinding.FragmentTicketBinding
import com.example.practice.presentation.home.viewmodel.HomeVM
import com.example.practice.presentation.home.viewmodel.HomeVMFactory
import javax.inject.Inject

class TicketFragment : BaseFragment() {

    @Inject lateinit var factory: HomeVMFactory
    private lateinit var binding: FragmentTicketBinding

    private val homeVM: HomeVM by lazy {
        activity?.run { ViewModelProviders.of(this, factory)[HomeVM::class.java] }
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
        binding = FragmentTicketBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = homeVM
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ticketItems.layoutManager = LinearLayoutManager(requireContext())
        binding.ticketItems.adapter = TicketAdapter(requireContext()).also {
            it.setData(homeVM.homeListCart.value?:listOf())
        }
    }

    companion object {
        fun newInstance() = TicketFragment()
        val TAG: String = TicketFragment::class.java.simpleName
    }
}