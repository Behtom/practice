package com.example.practice.presentation.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.core.application.BaseApplication
import com.example.practice.core.base.BaseActivity
import com.example.practice.core.base.BaseFragment
import com.example.practice.data.adapters.IAdapterListener
import com.example.practice.data.adapters.ShoppingCartAdapter
import com.example.practice.data.firebase.firestore.Product
import com.example.practice.databinding.FragmentShoppingCartBinding
import com.example.practice.presentation.home.viewmodel.HomeVM
import com.example.practice.presentation.home.viewmodel.HomeVMFactory
import com.example.practice.utils.AlertUtils
import com.example.practice.utils.showLongToast
import javax.inject.Inject

class ShoppingCartFragment : BaseFragment(), IAdapterListener<Product>, View.OnClickListener {

    @Inject lateinit var factory: HomeVMFactory
    private lateinit var binding: FragmentShoppingCartBinding

    private val homeVM: HomeVM by lazy {
        activity?.run { ViewModelProviders.of(this, factory)[HomeVM::class.java] }
            ?: throw Exception("Invalid Class")
    }

    private val listObserver = Observer<MutableList<Product>> { list ->
        if (list.isNullOrEmpty()) {
            requireContext().showLongToast("No hay productos en tu carrito.")
        } else {
            binding.recyclerview.adapter = ShoppingCartAdapter(requireContext(), this).also {
                it.setData(list)
            }
        }
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
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = homeVM
            it.onClickListener = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        homeVM.homeListCart.observe(viewLifecycleOwner, listObserver)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnBuy.id -> {
                if (homeVM.homeListCart.value!!.size > 0) {
                    AlertUtils.showChooseAlert(requireContext(), "Alerta", "¿Deseas comprar estos artículos?") {
                        (requireActivity() as BaseActivity).nextFragment(
                                TicketFragment.newInstance(),
                                TicketFragment.TAG
                        )
                    }
                } else {
                    AlertUtils.showErrorAlert(requireContext(), "No existen productos en tu carrito.")
                }
            }
        }
    }

    override fun addCount(model: Product) {
        if (homeVM.addCount(model)) {
            updateAdapter()
        } else {
            requireContext().showLongToast("No se pudo agregar una unidad del producto.")
        }
    }

    override fun minusCount(model: Product) {
        if (homeVM.minusCount(model)) {
            updateAdapter()
        } else {
            requireContext().showLongToast("No se pudo quitar una unidad del producto.")
        }
    }

    override fun deleteProduct(model: Product) {
        AlertUtils.showChooseAlert(requireContext(), "Eliminar producto", "¿Desea eliminar este produto de su carrito?") {
            if (homeVM.deleteProduct(model)) {
                updateAdapter()
            } else {
                requireContext().showLongToast("No se pudo quitar el producto.")
            }
        }

    }

    private fun updateAdapter() {
        (binding.recyclerview.adapter as ShoppingCartAdapter).setData(homeVM.homeListCart.value?:listOf())
    }

    companion object {
        fun newInstance() = ShoppingCartFragment()
        val TAG: String = ShoppingCartFragment::class.java.simpleName
    }
}