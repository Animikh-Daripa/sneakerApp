package com.example.sneakers.cart
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakers.R
import com.example.sneakers.home.HomeViewModel
import com.example.sneakers.home.HomeViewModel.OrderList


@Composable
fun CartList(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onBack: () -> Unit
) {

    val orderlist by viewModel.orderList.collectAsStateWithLifecycle()
    if(orderlist!=null && orderlist.size>=1) {
        Cart(
            orderList = orderlist,
            removeSnack = viewModel::removeItem,
            increaseItemCount = viewModel::increaseItemCount,
            decreaseItemCount = viewModel::decreaseItemCount,
            onBack = onBack,
            modifier = modifier
        )
    } else {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                stringResource(id = R.string.no_item_in_cart),
                fontSize = 30.sp,
                color = colorResource(id = R.color.primary)
                )
        }

    }
}

@Composable
fun Cart(
    orderList: List<OrderList>,
    removeSnack: (String) -> Unit,
    increaseItemCount: (String) -> Unit,
    decreaseItemCount: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box {
            CartContent(
                orderList = orderList,
                removeSnack = removeSnack,
                increaseItemCount = increaseItemCount,
                decreaseItemCount = decreaseItemCount,
                onBack = onBack,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun CartContent(
    orderList: List<OrderList>,
    removeSnack: (String) -> Unit,
    increaseItemCount: (String) -> Unit,
    decreaseItemCount: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Row() {
                IconButton(
                    onClick = {
                            onBack.invoke()
                    },
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        tint = colorResource(id = R.color.primary),
                        contentDescription = stringResource(id = R.string.remove)
                    )
                }
                Text(
                    text = stringResource(id = R.string.cart),
                    color = colorResource(id = R.color.primary),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(start = 10.dp,top = 5.dp)
                )
            }

        }
        items(orderList) { orderList ->

                CartItem(
                    orderList = orderList,
                    removeSnack = removeSnack,
                    increaseItemCount = increaseItemCount,
                    decreaseItemCount = decreaseItemCount
                )

        }
        item {
            SummaryItem(
                subtotal = orderList.map { it.item.price.toLong() * it.count }.sum(),
                taxes = 40
            )
        }
    }
}

@Composable
fun CartItem(
    orderList: OrderList,
    removeSnack: (String) -> Unit,
    increaseItemCount: (String) -> Unit,
    decreaseItemCount: (String) -> Unit,
) {
    val item = orderList.item


    Card(border = BorderStroke(1.dp, Color.Gray), modifier = Modifier.padding(10.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Row() {
                Card(
                    shape = RoundedCornerShape(50),
                    backgroundColor = colorResource(id = R.color.primary),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.shoe),
                        contentDescription = stringResource(id = R.string.display),
                        modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .padding(20.dp)
                    )
                }

                Column(modifier=Modifier.padding(start = 20.dp)) {
                    Text(
                        text = item.name,
                        fontSize = 20.sp,
                        color= colorResource(id = R.color.black)
                    )
                    Text(
                        text = getPriceSring(item.price.toLong()),
                        fontSize = 16.sp,
                        color= colorResource(id = R.color.gray)
                    )
                    QuantitySelector(
                        count = orderList.count,
                        decreaseItemCount = { decreaseItemCount(item.id) },
                        increaseItemCount = { increaseItemCount(item.id) })
                }


            }
        }

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End

        ) {
            IconButton(
                onClick = { removeSnack(item.id) },
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    tint = colorResource(id = R.color.black),
                    contentDescription = stringResource(id = R.string.remove)
                )
            }
        }

    }
}

@Composable
fun SummaryItem(
    subtotal: Long,
    taxes: Long,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(id = R.string.order_details),
            style = MaterialTheme.typography.h6,
            color = colorResource(id = R.color.background),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .heightIn(min = 56.dp)
                .wrapContentHeight()
        )
        Row(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(id = R.string.subtotal),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                text = getPriceSring(subtotal),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(id = R.string.taxes),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                text = getPriceSring(taxes),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(id = R.string.total),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .wrapContentWidth(Alignment.End)
                    .alignBy(LastBaseline)
            )
            Text(
                text = getPriceSring(subtotal + taxes),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Divider()
    }
}


@Composable
fun QuantitySelector(
    count: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = stringResource(id = R.string.qty),
                style = MaterialTheme.typography.subtitle1,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .padding(end = 18.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.remove_outline),
            contentDescription = stringResource(id = R.string.decrease),
            modifier = Modifier.clickable {
                decreaseItemCount()
            }
        )

        Crossfade(
            targetState = count,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "$it",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 18.sp,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(min = 24.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.add_outline),
            contentDescription = stringResource(id = R.string.decrease),
            modifier = Modifier.clickable {
                increaseItemCount()
            }
        )

    }
}

fun getPriceSring(price:Long):String {
    return "₹ "+price.toString()
}

