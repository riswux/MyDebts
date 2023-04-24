package com.example.mydebts.ui.owesme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mydebts.room.Debt
import com.example.mydebts.room.DebtDao
import com.example.mydebts.room.DebtDatabase

class OwesViewModel(application: Application) : AndroidViewModel(application) {

    private var dao: DebtDao? = null

    init {
        dao = DebtDatabase.getSaveInstance(application)?.debtDao()
    }

    fun getOwes(): LiveData<List<Debt>>? {
        return dao?.getMyOwes()
    }
}