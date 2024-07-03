package com.young.androiddemo.ui.page.main.collect


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

@Composable
fun CollectPage(
    navController: NavHostController,
    modifier: Modifier =Modifier,
//    scaffoldState: ScaffoldState,
//    viewModel: HomeViewModel = hiltViewModel()
) {
    Text(text = "CollectPage", textAlign = TextAlign.Center, modifier = modifier.fillMaxSize())
}