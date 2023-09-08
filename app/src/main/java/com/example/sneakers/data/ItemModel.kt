package com.example.sneakers.data

data class ItemModel(
    val id:String,
    val name: String,
    val price:Int,
    val image: Int,
    var onClick: (() -> Unit)? = null
)