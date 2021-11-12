package com.example.velu_task.ui.restaurant_page.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.velu_task.model.StarterModel
import com.example.velu_task.databinding.ItemStarterBinding

class StarterAdapter(private val starterList: ArrayList<StarterModel>) :
    RecyclerView.Adapter<ViewHolder>() {
    var onItemPlusClick: ((Int)->Unit) ?= null
    var onItemMinusClick: ((Int)->Unit) ?= null
    var onItemAddClick: ((Int)->Unit) ?= null
    private val TAG: String = this.javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStarterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.dishName.text = starterList[position].dishName
        holder.binding.dishDes.text = starterList[position].dishDes
        holder.binding.dishPrice.text = starterList[position].dishPrice
        holder.binding.count.text = starterList[position].dishCount.toString()
        if (starterList[position].certificate1 && !starterList[position].certificate2) {
            holder.binding.certificate1.visibility = View.VISIBLE
        } else if (starterList[position].certificate2 && !starterList[position].certificate1) {
            holder.binding.certificate2.visibility = View.VISIBLE
            holder.binding.certificate1.visibility = View.GONE
        } else if (starterList[position].certificate1 && starterList[position].certificate2) {
            holder.binding.certificate1.visibility = View.VISIBLE
            holder.binding.certificate2.visibility = View.VISIBLE
        } else {
            holder.binding.certificate1.visibility = View.GONE
            holder.binding.certificate1.visibility = View.GONE
        }

        if (starterList[position].dishCount>0){
            holder.binding.add.visibility = View.GONE
            holder.binding.plusMinusLay.visibility = View.VISIBLE
        }else{
            holder.binding.add.visibility = View.VISIBLE
            holder.binding.plusMinusLay.visibility = View.GONE
        }

        holder.binding.add.setOnClickListener {
            if (starterList[position].dishCount==0){
                Log.d(TAG, "onBindViewHolder: "+starterList[position].dishCount.toString())
                onItemAddClick?.invoke(position)
            }
        }

        holder.binding.plus.setOnClickListener {
            if (starterList[position].dishCount <= 5) {
                onItemPlusClick?.invoke(position)
            }
        }
        holder.binding.minus.setOnClickListener {
            if (starterList[position].dishCount >= 1) {
                onItemMinusClick?.invoke(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return starterList.size
    }
}

class ViewHolder(val binding: ItemStarterBinding) : RecyclerView.ViewHolder(binding.root)