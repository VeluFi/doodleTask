package com.example.velu_task.repo

import androidx.lifecycle.MutableLiveData
import com.example.velu_task.model.StarterModel

class MainRepo {
    fun getAllStarters(): MutableLiveData<ArrayList<StarterModel>> {
        val starterList: MutableLiveData<ArrayList<StarterModel>> = MutableLiveData()
        val data: ArrayList<StarterModel> = arrayListOf()
//        for (i in 0 until 10) {
            data.add(StarterModel("Chicken Tandoori", "Spicy, Juicy, Crispy", 0, "$5", true, false))
            data.add(StarterModel("Chicken Tikka", "Spicy, Juicy, Crispy", 0, "$5", true, false))
            data.add(StarterModel("Chicken Kabob", "Spicy, Juicy, Crispy", 0, "$5", true, false))
            data.add(StarterModel("Chicken 65", "Spicy, Juicy, Crispy", 0, "$5", true, false))
            data.add(StarterModel("Chicken Gravy", "Spicy, Juicy, Crispy", 0, "$5", true, false))
            data.add(StarterModel("Chicken Boneless", "Spicy, Juicy, Crispy", 0, "$5", true, false))
//        }
//        data.shuffle()
        starterList.postValue(data)
        return starterList

    }
}