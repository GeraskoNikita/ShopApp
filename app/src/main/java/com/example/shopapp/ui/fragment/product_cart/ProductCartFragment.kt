package com.example.shopapp.ui.fragment.product_cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.shopapp.databinding.FragmentProductCartBinding
import com.example.shopapp.ui.fragment.product_cart.viewmodel.ProductCartViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductCartFragment : Fragment() {


    private var _binding: FragmentProductCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductCartViewModel by viewModel()
    private val adapter = ProductCartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCart.adapter = adapter
        observeData()

        binding.btnPlace.setOnClickListener {
            viewModel.checkout()
        }

        binding.btnClearCart.setOnClickListener {
            viewModel.clearCart()
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(viewModel.items, viewModel.total) { items, total ->
                    Pair(items, total)
                }.collect { (items, total) ->
                    adapter.submitList(items)
                    binding.btnPlace.isEnabled = items.isNotEmpty()
                    binding.tvPrice.text = "Total: %.2f".format(total)
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}