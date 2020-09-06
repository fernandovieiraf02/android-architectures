package com.fazv.data.datasource.cachedata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.fazv.data.model.entities.CurrenciesListCache
import com.fazv.utils.extension.CURRENCIES_TABLE_NAME
import io.reactivex.Single

@Dao
interface CurrenciesListDAO {
    @Query("SELECT * from $CURRENCIES_TABLE_NAME")
    fun getCurrenciesList(): Single<List<CurrenciesListCache>>

    @Transaction
    fun updateData(currencies: CurrenciesListCache) {
        delete()
        insert(currencies)
    }

    @Insert
    fun insert(currencies: CurrenciesListCache)

    @Query("DELETE FROM $CURRENCIES_TABLE_NAME")
    fun delete()
}