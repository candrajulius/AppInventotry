package com.candra.projectinventory.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.candra.projectinventory.R
import com.candra.projectinventory.data.Barang
import com.candra.projectinventory.databinding.CardItemProductBinding
import com.candra.projectinventory.helper.Constant

class ProductAdapter(
    private val context: Context,
    private val onClick: (Barang) -> Unit,
    private val onDelete: (Barang) -> Unit
): RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: CardItemProductBinding): RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(data: Barang){
            with(binding){
                codeProduct.text = data.code_barang
                nameProduct.text = data.name_barang
                priceProduct.text = data.price_barang
                stockProduct.text = data.stock_product
                if (data.status_product.equals(Constant.STATUS_IN,ignoreCase = true)){
                    statusProduct.text = context.getString(R.string.product_in)
                    statusProduct.setTextColor(ContextCompat.getColor(context,android.R.color.holo_green_light))
                }else{
                    statusProduct.text = context.getString(R.string.product_out)
                    statusProduct.setTextColor(ContextCompat.getColor(context,android.R.color.holo_red_light))
                }

                cardData.setOnClickListener {
                    onClick(data)
                }
                btnDelete.setOnClickListener {
                    onDelete(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder =
        ViewHolder(CardItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    val differ = AsyncListDiffer(this,object: DiffUtil.ItemCallback<Barang>(){
        override fun areItemsTheSame(oldItem: Barang, newItem: Barang): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Barang, newItem: Barang): Boolean =
            oldItem == newItem
    })

    fun submitListData(it: List<Barang>){
        differ.submitList(it)
    }

}