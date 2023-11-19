package com.anbui.recipely.presentation.cart_order.cart

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.getTotalPrice
import com.anbui.recipely.presentation.cart_order.cart.components.CartItem
import com.anbui.recipely.presentation.ui.components.StandardCard
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.DarkGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.ThinGrey
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import com.anbui.recipely.presentation.util.Screen
import com.anbui.recipely.util.toStringAsFixed
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@ExperimentalMaterial3Api
@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    var openAlertDialog by remember { mutableStateOf(false) }
    var timeOut by remember { mutableIntStateOf(-1) }
    val ingredient by cartViewModel.ingredients.collectAsStateWithLifecycle()
    val price by remember {
        derivedStateOf {
            ingredient.getTotalPrice()
        }
    }

    val account by cartViewModel.currentAccount.collectAsStateWithLifecycle()

    val success by cartViewModel.success.collectAsStateWithLifecycle()

    LaunchedEffect(success) {
        if (success) {
            openAlertDialog = true
            timeOut = 1
        }
    }

    LaunchedEffect(ingredient) {
        Log.d("Cart Screen", ingredient.toString())
    }
    LaunchedEffect(timeOut) {
        if (timeOut != -1) {
            delay(1.seconds)
            timeOut--
        }
        if (timeOut == 0) {
            openAlertDialog = false
            navController.popBackStack()
        }
        Log.d("asd", "asd")
    }

    when {
        openAlertDialog -> {
            Dialog(onDismissRequest = {
                openAlertDialog = false
                timeOut = -1
            }) {
                StandardCard() {
                    Text(
                        text = "Succes",
                        modifier = Modifier
//                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = stringResource(id = R.string.my_cart),
            showBackArrow = true
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(ingredient, key = { it.ingredientId }) {
                    CartItem(
                        modifier = Modifier.padding(
                            vertical = SpaceMedium,
                            horizontal = SpaceLarge
                        ),
                        imageUrl = it.imageUrl,
                        name = it.name,
                        amount = it.amount.toInt(),
                        unit = it.unit.toString(),
                        price = it.price
                    ) { amount ->
                        cartViewModel.onChangeAmount(it.ingredientId, amount)
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(120.dp))
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .shadow(
                        elevation = 16.dp,
                        spotColor = ThinGrey,
                        ambientColor = MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.large
                    )
                    .background(TrueWhite, shape = MaterialTheme.shapes.large)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = SpaceLarge, vertical = SpaceSmall)
                        .clickable {
                            navController.navigate(Screen.AddressScreen.route) {
                                launchSingleTop = true
                            }
                        },
                    horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = stringResource(
                            R.string.location
                        )
                    )
                    Text(
                        text = account.getAddress(),
                        maxLines = 1,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            color = DarkGrey
                        )
                    )
                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = SpaceLarge, vertical = SpaceSmall),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = stringResource(R.string.total_price),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "\$${price.toDouble().toStringAsFixed(2)}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Button(
                        onClick = {
                            cartViewModel.checkOut()
                        },
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .fillMaxWidth(.7f)

                    ) {
                        Text(
                            text = "Checkout",
                            style = MaterialTheme.typography.bodyMedium.copy(color = TrueWhite),
                            modifier = Modifier.padding(vertical = SpaceSmall)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = SpaceSmall))
            }
        }
    }
}
