package com.fazv.data.datasource.cachedata

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun hashMapToString(data: HashMap<String, String>?): String? =
        Gson().toJson(data)

    @TypeConverter
    fun fromStringToHashMap(data: String?): HashMap<String, String>? =
        Gson().fromJson<HashMap<String, String>>(data, HashMap::class.java)
}
