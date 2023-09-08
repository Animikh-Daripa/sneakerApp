package com.example.sneakers.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakers.R


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(),homeModel: HomeModel) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Column {
            val list = homeViewModel.getGridList(homeModel)
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.sneakership),
                        fontSize = 30.sp,
                        color = colorResource(id = R.color.primary)
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(list.size) {
                        Card(
                            onClick = {
                                list[it].onClick?.invoke()
                            },
                            modifier = Modifier.padding(8.dp),
                            elevation = 6.dp
                        ) {
                            val ctx = LocalContext.current
                            val toastMessage:String = list[it].name + stringResource(id = R.string.added_to_cart)
                            Column(Modifier.fillMaxWidth()) {
                                IconButton(
                                    onClick = {
                                        homeViewModel.addItem(list[it].id)
                                        homeViewModel.showToast(ctx, toastMessage)
                                              },
                                    modifier = Modifier
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.add_home),
                                        tint = colorResource(id = R.color.primary),
                                        contentDescription = stringResource(id = R.string.remove)
                                    )
                                }
                            }

                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .padding(top = 20.dp, bottom = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Card(
                                    shape = RoundedCornerShape(50),
                                    backgroundColor = colorResource(id = R.color.primary),
                                ) {
                                    Image(
                                        painter = painterResource(id = list[it].image),
                                        contentDescription = list[it].name,
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(60.dp)
                                            .padding(5.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(9.dp))
                                Text(
                                    text = list[it].name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = homeViewModel.getPriceString(list[it].price),
                                    fontSize = 25.sp,
                                    color= colorResource(id = R.color.primary)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


