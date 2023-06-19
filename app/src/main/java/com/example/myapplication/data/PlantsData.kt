package com.example.myapplication.data

import android.content.Context
import com.example.myapplication.MyApplication
import org.json.JSONArray
import java.nio.charset.Charset


object PlantsData {
    val list = listOf(
        Plant("1", "Apple", "Apple", growZoneNumber = 1),
        Plant("2", "Banana", "Banana", growZoneNumber = 2),
        Plant("3", "Carrot", "Carrot", growZoneNumber = 3),
        Plant("4", "Dill", "Dill", growZoneNumber = 3),
    )
}
/*
val list: List<Plant> = loadFromJson(MyApplication.instance)

private fun loadFromJson(context: Context): List<Plant> {
    val jsonString = getJsonStringFromAssets(context,"plants.json")
    val jsonArray = JSONArray(jsonString)
    val plantList = mutableListOf<Plant>()

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val id = jsonObject.getString("plantId")
        val name = jsonObject.getString("name")
        val description = jsonObject.getString("description")
        val growZoneNumber = jsonObject.getInt("growZoneNumber")

        val plant = Plant(id, name, description, growZoneNumber)
        plantList.add(plant)
    }
    return plantList
}

private fun getJsonStringFromAssets(context: Context,fileName: String): String {
    val assetManager = context.assets
    val inputStream = assetManager.open(fileName)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charset.forName("UTF-8"))
}
}*/
