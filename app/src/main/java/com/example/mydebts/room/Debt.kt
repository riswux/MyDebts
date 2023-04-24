package com.example.mydebts.room

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.Companion.INTEGER
import androidx.room.ColumnInfo.Companion.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debt")
data class Debt(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "is_deptor", typeAffinity = INTEGER)
    val isDeptor: Boolean,
    @ColumnInfo("creditor_name", typeAffinity = TEXT)
    val creditorName: String,
    @ColumnInfo("date", typeAffinity = TEXT)
    val date: String,
    @ColumnInfo("millis", typeAffinity = INTEGER)
    val sum: Int,
    @ColumnInfo("currency", typeAffinity = TEXT)
    val currency: String
): java.io.Serializable