package com.example.practice.presentation.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseActivity
import com.example.practice.core.base.BaseFragment
import com.example.practice.data.persistence.firestore.Product
import com.example.practice.databinding.FragmentDetailBinding
import com.example.practice.presentation.home.viewmodel.HomeVM
import com.example.practice.presentation.home.viewmodel.HomeVMFactory
import com.example.practice.utils.showLongToast
import javax.inject.Inject

class DetailFragment : BaseFragment(), View.OnClickListener {

    @Inject lateinit var factory: HomeVMFactory
    private lateinit var binding: FragmentDetailBinding

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
        binding = FragmentDetailBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.onClickListener = this
            it.viewModel = homeVM
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivAdd.setOnClickListener {
            val result = homeVM.addProductToCart(homeVM.homeProduct.value?:Product())
            requireContext().showLongToast(result)
        }
    }

    override fun onClick(v: View?) {
        (requireActivity() as BaseActivity).nextFragment(
                ShoppingCartFragment.newInstance(),
                ShoppingCartFragment.TAG
        )
    }

    companion object {
        fun newInstance() = DetailFragment()
        val TAG: String = DetailFragment::class.java.simpleName
    }
}