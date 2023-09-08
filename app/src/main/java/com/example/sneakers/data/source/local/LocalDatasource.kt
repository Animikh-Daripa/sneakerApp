package com.example.sneakers.data.source.local

import com.example.sneakers.R
import com.example.sneakers.data.ItemModel
import com.example.sneakers.home.HomeModel


open class LocalDatasource() {
    fun getGridList(homeModel: HomeModel):List<ItemModel> {
        var list: List<ItemModel> = ArrayList()
        list = list + ItemModel("1","Nike 1",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("1")})
        list = list + ItemModel("2","Nike 2",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("2")})
        list = list + ItemModel("3","Nike 3",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("3")})
        list = list + ItemModel("4","Nike 4",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("4")})
        list = list + ItemModel("5","Nike 5",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("5")})
        list = list + ItemModel("6","Nike 6",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("6")})
        list = list + ItemModel("7","Nike 7",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("7")})
        list = list + ItemModel("8","Nike 8",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("8")})
        list = list + ItemModel("9","Nike 9",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("9")})
        list = list + ItemModel("10","Nike 10",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("10")})
        list = list + ItemModel("11","Nike 11",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("11")})
        list = list + ItemModel("12","Nike 12",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("12")})
        list = list + ItemModel("13","Nike 13",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("13")})
        list = list + ItemModel("14","Nike 14",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("14")})
        list = list + ItemModel("15","Nike 15",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("15")})
        list = list + ItemModel("16","Nike 16",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("16")})
        list = list + ItemModel("17","Nike 17",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("17")})
        list = list + ItemModel("18","Nike 18",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("18")})
        list = list + ItemModel("19","Nike 19",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("19")})
        list = list + ItemModel("20","Nike 20",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("20")})
        list = list + ItemModel("21","Nike 21",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("21")})
        list = list + ItemModel("22","Nike 22",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("22")})
        list = list + ItemModel("23","Nike 23",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("23")})
        list = list + ItemModel("24","Nike 24",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("24")})

        return list

    }

}