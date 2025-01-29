package com.example.newnavigationcomposeexample.ui.screens

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.newnavigationcomposeexample.ui.Item
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object ItemNavType : NavType<Item>(isNullableAllowed = false) {
    // Retrieve the Item object from the bundle
    override fun get(bundle: Bundle, key: String): Item? {
     return Json.decodeFromString(bundle.getString(key)?:return null)
    }

    // Store the Item object in the bundle
    override fun put(bundle: Bundle, key: String, value: Item) {
        bundle.putString(key, Json.encodeToString(value))
    }

    // Convert the Item object to a string (for navigation argument)
    override fun parseValue(value: String): Item {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: Item): String {
        return Uri.encode(Json.encodeToString(value))
    }


}
