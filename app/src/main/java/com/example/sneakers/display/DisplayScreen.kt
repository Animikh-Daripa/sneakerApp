package com.example.sneakers.display


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakers.R
import com.example.sneakers.home.HomeViewModel


@Composable
fun DisplayScreen(viewModel: HomeViewModel = viewModel(),
itemId:String = "",onBack: () -> Unit) {
    if(itemId.isNotEmpty()) {
        DisplayItem(viewModel,itemId, onBack = onBack)
    }
}

@Composable
fun DisplayItem(viewModel: HomeViewModel = viewModel(),
                  itemId:String = "",
                onBack: () -> Unit
                ) {

    val item = viewModel.getItem(itemId)
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
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

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Card(
                shape = RoundedCornerShape(50),
                backgroundColor = colorResource(id = R.color.primary),

                ) {
                Image(
                    painter = painterResource(id = R.drawable.shoe),
                    contentDescription = stringResource(id = R.string.display),
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .padding(20.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(shape = RoundedCornerShape(topStartPercent = 10,topEndPercent = 10), elevation = 6.dp,
                border = BorderStroke(1.dp, Color.Gray) ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item?.let {
                        Text(
                            it.name,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.black)
                        )

                    }

                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                        ) {
                        Row {
                            val priceText = buildAnnotatedString {
                                withStyle(style = SpanStyle(colorResource(id = R.color.gray))) {
                                    append(stringResource(id = R.string.price))
                                }
                                withStyle(style = SpanStyle(colorResource(id = R.color.primary))) {
                                    append(item?.let { viewModel.getPriceString(it.price) })
                                }
                            }
                            item?.let {
                                Text(
                                    text = priceText,
                                    fontSize = 25.sp,
                                    color = colorResource(id = R.color.primary)
                                )
                            }
                            val ctx = LocalContext.current
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 10.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                val toastMessage = item?.name.toString() + stringResource(id = R.string.added_to_cart)
                                Button(
                                    
                                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.primary)),
                                    onClick = {
                                        viewModel.addItem(itemId)
                                        viewModel.showToast(ctx,toastMessage)
                                    }) {
                                    Text(text = stringResource(id = R.string.add_to_cart),
                                        color = colorResource(id = R.color.white)
                                    )
                                }
                            }
                        }
                    }

                }

            }

        }
    }
}




