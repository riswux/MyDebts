package com.example.mydebts.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Debt::class],
    version = 1,
    exportSchema = false
)
abstract class DebtDatabase : RoomDatabase() {
    abstract fun debtDao(): DebtDao

    companion object {
        private var INSTANCE: DebtDatabase? = null
        fun getSaveInstance(context: Context?): DebtDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE =
                    Room.databaseBuilder(
                        context!!,
                        DebtDatabase::class.java,
                        "debt_database.db"
                    )
                        .build()
            }
            return INSTANCE
        }
    }
}