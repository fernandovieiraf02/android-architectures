package com.fazv.data.datasource.cachedata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fazv.data.model.entities.CurrenciesListCache

@Database(version = 1, entities = [CurrenciesListCache::class])
@TypeConverters(Converters::class)
abstract class CoinMasterDataBase : RoomDatabase() {

    abstract fun currenciesListDao(): CurrenciesListDAO

    companion object {
        private const val DATA_BASE_NAME = "coinmaster.db"

        fun createDataBase(context: Context): CoinMasterDataBase = Room
            .databaseBuilder(
                context,
                CoinMasterDataBase::class.java,
                DATA_BASE_NAME
            )
            .build()
    }
}