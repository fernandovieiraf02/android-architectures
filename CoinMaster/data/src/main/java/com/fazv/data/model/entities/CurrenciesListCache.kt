package com.fazv.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fazv.utils.extension.CURRENCIES_TABLE_NAME

@Entity(tableName = CURRENCIES_TABLE_NAME)
class CurrenciesListCache (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var currencies: HashMap<String, String> = HashMap()
)