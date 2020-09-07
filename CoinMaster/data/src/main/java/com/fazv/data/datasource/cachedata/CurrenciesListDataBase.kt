package com.fazv.data.datasource.cachedata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fazv.data.model.entities.CurrenciesListCache
import dagger.Provides
import javax.inject.Singleton

@Database(version = 1, entities = [CurrenciesListCache::class])
@TypeConverters(Converters::class)
@Singleton
abstract class CurrenciesListDataBase : RoomDatabase() {

    abstract fun currenciesListDao(): CurrenciesListDAO

    companion object {
        fun createDataBase(context: Context): CurrenciesListDAO = Room
            .databaseBuilder(context, CurrenciesListDataBase::class.java, "Currencies.db")
            .build()
            .currenciesListDao()
    }
}