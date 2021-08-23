package com.example.practice.presentation.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseActivity
import com.example.practice.core.base.BaseFragment
import com.example.practice.data.adapters.IAdapterListener
import com.example.practice.data.adapters.ProductAdapter
import com.example.practice.data.persistence.firestore.Product
import com.example.practice.data.network.ConnectionState
import com.example.practice.databinding.FragmentProductsBinding
import com.example.practice.presentation.home.viewmodel.HomeVM
import com.example.practice.presentation.home.viewmodel.HomeVMFactory
import com.example.practice.utils.showLongToast
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ProductsFragment : BaseFragment(), IAdapterListener<Product>, View.OnClickListener {

    @Inject lateinit var factory: HomeVMFactory
    private lateinit var binding: FragmentProductsBinding

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
        binding = FragmentProductsBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.onClickListener = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        MainScope().launch {
            when (val result = homeVM.getAllProductsFromRemote()) {
                is ConnectionState.Success -> {
                    requireContext().showLongToast(result.response.message?:"")
                    binding.recyclerview.adapter = ProductAdapter(requireContext(), this@ProductsFragment).also {
                        it.setData(result.response.list?:listOf())
                    }
                }
                is ConnectionState.Error -> {
                    requireContext().showLongToast(result.response.message?:"")
                }
            }
        }
    }

    override fun onProductAdapterListener(model: Product, position: Int) {
        homeVM.setProduct(model)
        (requireActivity() as BaseActivity).nextFragment(
            DetailFragment.newInstance(),
            DetailFragment.TAG
        )
    }

    override fun addProductToCart(model: Product) {
        val result = homeVM.addProductToCart(model)
        requireContext().showLongToast(result)
    }

    override fun onClick(v: View?) {
        (requireActivity() as BaseActivity).nextFragment(
            ShoppingCartFragment.newInstance(),
            ShoppingCartFragment.TAG
        )
    }

    companion object {
        fun newInstance() = ProductsFragment()
        val TAG: String = ProductsFragment::class.java.simpleName
    }
}