package com.example.testtask.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.data.remote.ProductData

// адаптер для позиций меню
class MenuProductsAdapter : RecyclerView.Adapter<MenuProductsAdapter.ViewHolder>() {

    private val positions: ArrayList<ProductData> = arrayListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvDescription: TextView
        val tvPrice: TextView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            tvDescription = view.findViewById(R.id.tvDescription)
            tvPrice = view.findViewById(R.id.tvPrice)
        }
    }

    fun setProducts(products: ArrayList<ProductData>?) {
        if(products != null) {
            positions.clear()
            positions.addAll(products)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        holder.tvTitle.text = positions[index].title
        val formattedDescription = positions[index].description?.let { formatDescription(it) }
        holder.tvDescription.text = formattedDescription
        holder.tvPrice.text = "от ${positions[index].price} р"
    }

    // если описание слишком длинное, обрезаем и добавляем 3 точки
    private fun formatDescription(description: String): String {
        val endCrop = 80
        if(description.length < endCrop) return description
        else return "${description.subSequence(0,endCrop).trim()}..."
    }

    override fun getItemCount() = positions.size
}