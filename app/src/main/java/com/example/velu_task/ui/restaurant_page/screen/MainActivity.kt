package com.example.velu_task.ui.restaurant_page.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.velu_task.databinding.ActivityMainBinding
import com.example.velu_task.model.StarterModel
import com.example.velu_task.ui.restaurant_page.adapter.StarterAdapter
import com.example.velu_task.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StarterAdapter
    private lateinit var viewModel: MainViewModel
    private var TAG: String = this.javaClass.simpleName
    companion object {
        var starterList: ArrayList<StarterModel> = arrayListOf()
        var cartProductCount: ArrayList<String> = arrayListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = StarterAdapter(starterList)
        binding.starterRecycler.adapter = adapter
        getStarterData()
        adapter.onItemPlusClick = {
            starterList[it].dishCount++
            if (!cartProductCount.contains(starterList[it].dishName)) {
                cartProductCount.add(
                    starterList[it].dishName
                )
            }
            binding.cartText.text = "(${cartProductCount.size} ITEM)"
            adapter.notifyItemChanged(it)
        }
        adapter.onItemMinusClick = {
            starterList[it].dishCount--
            if (cartProductCount.contains(starterList[it].dishName)&&starterList[it].dishCount<1) {
                cartProductCount.remove(
                    starterList[it].dishName
                )
            }
            binding.cartText.text = "(${cartProductCount.size} ITEM)"
            adapter.notifyItemChanged(it)
        }

        adapter.onItemAddClick = {
            starterList[it].dishCount++
            if (!cartProductCount.contains(starterList[it].dishName)) {
                cartProductCount.add(
                    starterList[it].dishName
                )
            }
            binding.cartText.text = "(${cartProductCount.size} ITEM)"
            adapter.notifyItemChanged(it)
        }

        binding.cartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun getStarterData() {
        viewModel.getStarterData().observe(this, Observer {
            starterList.clear()
            starterList.addAll(it)
            starterList.forEachIndexed { index, starterModel ->
                adapter.notifyItemInserted(index)
            }
        })
    }
}