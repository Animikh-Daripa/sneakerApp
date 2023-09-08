package com.example.sneakers.home

data class HomeModel(
    var onItemClick: ((String) -> Unit)? = null
)