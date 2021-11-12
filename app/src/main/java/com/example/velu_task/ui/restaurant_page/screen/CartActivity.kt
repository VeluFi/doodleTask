package com.example.velu_task.ui.restaurant_page.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.velu_task.databinding.ActivityCartBinding
import com.example.velu_task.model.StarterModel
import com.example.velu_task.ui.restaurant_page.adapter.CartAdapter

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    var totalCartList: ArrayList<StarterModel> = arrayListOf()
    var cartList: ArrayList<StarterModel> = arrayListOf()
    var newCartList: ArrayList<StarterModel> = arrayListOf()
    private lateinit var adapter: CartAdapter
    private var TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener { onBackPressed() }
        totalCartList = MainActivity.starterList
        adapter = CartAdapter(newCartList)
        binding.starterRecycler.adapter = adapter
        addCartList()
        if (cartList.size > 2) {
            binding.showMoreBtn.visibility = View.VISIBLE
        } else {
            binding.showMoreBtn.visibility = View.GONE
        }

        calculateProductPrice()

        binding.showMoreBtn.setOnClickListener {
            newCartList.clear()
            newCartList.addAll(cartList)
            adapter.notifyDataSetChanged()
            binding.showMoreBtn.visibility = View.GONE
        }

        adapter.onItemPlusClick = {
            newCartList[it].dishCount++
            addItemFromArray(it)
            calculateProductPrice()
            adapter.notifyItemChanged(it)
        }

        adapter.onItemMinusClick = {
            newCartList[it].dishCount--
            calculateProductPrice()
            if (newCartList[it].dishCount == 0) {
                deleteItemFromArray(it)
                newCartList.removeAt(it)
                adapter.notifyItemRemoved(it)
                adapter.notifyItemRangeChanged(0, newCartList.size)
            } else {
                deleteItemFromArray(it)
                adapter.notifyItemChanged(it)
            }
        }
    }

    private fun calculateProductPrice(){
        var total = 0
        newCartList.forEachIndexed { index, starterModel ->
            val singleItemPrice = total + (starterModel.dishCount*starterModel.dishPrice.replace("$","").toInt())
            total = singleItemPrice
        }
        binding.totalTv.text = "$$total"
    }

    private fun addCartList() {
        newCartList.clear()
        totalCartList.forEachIndexed { index, starterModel ->
            if (totalCartList[index].dishCount > 0) {
                cartList.add(totalCartList[index])
            }
        }
        for (i in 0 until cartList.size) {
            if (i == 2) {
                break
            } else {
                newCartList.add(
                    StarterModel(
                        cartList[i].dishName,
                        cartList[i].dishDes,
                        cartList[i].dishCount,
                        cartList[i].dishPrice,
                        cartList[i].certificate1,
                        cartList[i].certificate2
                    )
                )
            }
            adapter.notifyItemInserted(i)
        }
    }

    private fun addItemFromArray(position: Int){
        MainActivity.starterList.forEachIndexed { index, starterModel ->
            if (cartList[position].dishName.contains(starterModel.dishName)){
                MainActivity.starterList[index].dishCount++
            }
        }
    }

    private fun deleteItemFromArray(position: Int){
        MainActivity.starterList.forEachIndexed { index, starterModel ->
            if (cartList[position].dishName.contains(starterModel.dishName)){
                MainActivity.starterList[index].dishCount--
            }
        }
    }
}