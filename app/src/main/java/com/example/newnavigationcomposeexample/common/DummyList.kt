package com.example.newnavigationcomposeexample.common

import com.example.newnavigationcomposeexample.ui.Item

// Helper function to get the list of items
fun getItemList(): List<Item> {
    return listOf(
        Item(1, "Item 1"),
        Item(2, "Item 2"),
        Item(3, "Item 3"),
        Item(4, "Item 4"),
        Item(5, "Item 5")
    )
}
fun getItemById(id: Int): Item? {
    return getItemList().find { it.id == id }
}
