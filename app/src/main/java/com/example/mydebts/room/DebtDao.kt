package com.example.mydebts.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DebtDao {
    @Insert
    suspend fun addDebt(debt: Debt)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDebt(debt: Debt)

    @Query("SELECT * FROM debt")
    fun getAllDebts(): LiveData<List<Debt>>

    @Query("SELECT * FROM debt WHERE date =:date")
    fun getTodayDebts(date: String): LiveData<List<Debt>>

    @Query("SELECT * FROM debt WHERE is_deptor == 1")
    fun getMyOwes(): LiveData<List<Debt>>
}