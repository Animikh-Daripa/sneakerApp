package com.example.sneakers.home


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.example.sneakers.R
import com.example.sneakers.data.ItemModel
import com.example.sneakers.data.source.local.LocalDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class HomeViewModel(var localDatasource: LocalDatasource = LocalDatasource()):ViewModel() {
    private val _orderList: MutableStateFlow<List<OrderList>> =
        MutableStateFlow(getCartList())
    val orderList: StateFlow<List<OrderList>> get() = _orderList

    lateinit var list: List<ItemModel>
    fun getGridList(homeModel:HomeModel):List<ItemModel> {

        list = localDatasource.getGridList(homeModel)
//        list = ArrayList()
//        list = list + ItemModel("1","Nike 1",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("1")})
//        list = list + ItemModel("2","Nike 2",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("2")})
//        list = list + ItemModel("3","Nike 3",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("3")})
//        list = list + ItemModel("4","Nike 4",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("4")})
//        list = list + ItemModel("5","Nike 5",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("5")})
//        list = list + ItemModel("6","Nike 6",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("6")})
//        list = list + ItemModel("7","Nike 7",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("7")})
//        list = list + ItemModel("8","Nike 8",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("8")})
//        list = list + ItemModel("9","Nike 9",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("9")})
//        list = list + ItemModel("10","Nike 10",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("10")})
//        list = list + ItemModel("11","Nike 11",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("11")})
//        list = list + ItemModel("12","Nike 12",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("12")})
//        list = list + ItemModel("13","Nike 13",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("13")})
//        list = list + ItemModel("14","Nike 14",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("14")})
//        list = list + ItemModel("15","Nike 15",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("15")})
//        list = list + ItemModel("16","Nike 16",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("16")})
//        list = list + ItemModel("17","Nike 17",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("17")})
//        list = list + ItemModel("18","Nike 18",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("18")})
//        list = list + ItemModel("19","Nike 19",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("19")})
//        list = list + ItemModel("20","Nike 20",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("20")})
//        list = list + ItemModel("21","Nike 21",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("21")})
//        list = list + ItemModel("22","Nike 22",2000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("22")})
//        list = list + ItemModel("23","Nike 23",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("23")})
//        list = list + ItemModel("24","Nike 24",3000, R.drawable.shoe, onClick = {homeModel.onItemClick?.invoke("24")})

       return list

    }

    fun increaseItemCount(itemId: String) {
            val currentCount = _orderList.value.first { it.item.id.equals(itemId) }.count
            updateItemCount(itemId, currentCount + 1)
    }

    fun decreaseItemCount(itemId: String) {
            val currentCount = _orderList.value.first { it.item.id.equals(itemId) }.count
            if (currentCount == 1) {
                removeItem(itemId)
            } else {
                updateItemCount(itemId, currentCount - 1)
            }
    }

    private fun updateItemCount(itemId: String, count: Int) {
        _orderList.value = _orderList.value.map {
            if (it.item.id.equals(itemId)) {
                it.copy(count = count)
            } else {
                it
            }
        }
    }


    fun getItem(itemId: String):ItemModel? {
        var itemModel:ItemModel? =null
         list.forEach {
            if (it.id.equals(itemId)) {
                itemModel = it
            }
        }
        return itemModel
    }

    fun addItem(itemId: String) {
        var addItem = true
        var count = 0
        list.forEach {
            if (it.id.equals(itemId)) {
                if(_orderList.value == null) {
                    _orderList.value = listOf(OrderList(it,1))
                    addItem = false
                } else {

                    _orderList.value.map {
                        if(it.item.id == itemId) {
                            addItem = false
                            count = it.count + 1
                        }
                    }

                    if(addItem) {
                        _orderList.value = _orderList.value.plus(OrderList(it, 1))
                    } else {
                        updateItemCount(itemId,count)
                    }
                }

            }
        }
    }



    fun removeItem(itemId: String) {
        _orderList.value = _orderList.value.filter { !it.item.id.equals(itemId)}
    }

    fun getCartList() = cart

    val cart = emptyList<OrderList>()

    @Immutable
    data class OrderList(
        val item: ItemModel,
        val count: Int
    )

    fun getPriceString(price:Int):String {
        return "₹ "+price.toString()
    }

    fun showToast(ctx: Context, message:String) {
        Toast.makeText(ctx,message, Toast.LENGTH_SHORT).show()
    }

}