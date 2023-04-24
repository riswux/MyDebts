package com.example.mydebts.adddebt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydebts.room.Debt
import com.example.mydebts.room.DebtDao
import com.example.mydebts.room.DebtDatabase
import kotlinx.coroutines.launch

class AddDebtViewModel(application: Application) : AndroidViewModel(application) {

    var dao: DebtDao? = null

    init {
        dao = DebtDatabase.getSaveInstance(application)?.debtDao()
    }

    fun addDebt(debt: Debt) {
        viewModelScope.launch {
            dao?.addDebt(debt)
        }
    }
}