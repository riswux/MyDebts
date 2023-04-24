package com.example.mydebts.debtdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydebts.room.Debt
import com.example.mydebts.room.DebtDao
import com.example.mydebts.room.DebtDatabase
import kotlinx.coroutines.launch

class DebtDetailsViewModel(application: Application) : AndroidViewModel(application) {

    var dao: DebtDao? = null

    init {
        dao = DebtDatabase.getSaveInstance(application)?.debtDao()
    }

    fun updateDebt(debt: Debt) {
        viewModelScope.launch {
            dao?.updateDebt(debt)
        }
    }
}