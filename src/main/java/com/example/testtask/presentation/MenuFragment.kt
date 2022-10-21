package com.example.testtask.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.utils.Status

class MenuFragment : Fragment() {

    private val viewModel: ProductsViewModel by lazy {
        ViewModelProvider(requireActivity())[ProductsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val rvPositions = view.findViewById<RecyclerView>(R.id.pizzasList)
        rvPositions.layoutManager = LinearLayoutManager(requireActivity())
        val productsAdapter = MenuProductsAdapter()
        rvPositions.adapter = productsAdapter

        lifecycleScope.launchWhenResumed {
            viewModel.getPizzas().observe(viewLifecycleOwner) { pizzasListResult ->
                if(!pizzasListResult.data.isNullOrEmpty()) {
                    productsAdapter.setProducts(pizzasListResult.data)
                    productsAdapter.notifyDataSetChanged()
                }
                when(pizzasListResult.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
        return view
    }


}