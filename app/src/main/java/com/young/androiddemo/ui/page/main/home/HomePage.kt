package com.young.androiddemo.ui.page.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

@Composable
fun HomePage(
    navController: NavHostController,
    modifier: Modifier=Modifier,
//    scaffoldState: ScaffoldState,
//    viewModel: HomeViewModel = hiltViewModel()
) {
    Text(text = "HomePage", textAlign = TextAlign.Center, modifier = modifier.fillMaxSize())
}