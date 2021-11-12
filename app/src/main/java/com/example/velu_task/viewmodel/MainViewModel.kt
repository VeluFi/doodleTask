package com.example.velu_task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.velu_task.model.StarterModel
import com.example.velu_task.repo.MainRepo

class MainViewModel: ViewModel() {

    fun getStarterData(): LiveData<ArrayList<StarterModel>>{
        return MainRepo().getAllStarters()
    }

}