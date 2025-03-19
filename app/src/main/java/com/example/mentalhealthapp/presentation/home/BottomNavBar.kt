package com.example.mentalhealthapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R

//
//@Composable
//fun BottomNavigationWithFab(
//    navController: NavController,
//    modifier: Modifier = Modifier,
//) {
//    // Get the current route
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    Box(
//        modifier = modifier.fillMaxWidth()
//    ) {
//        BottomAppBar(
//            modifier = Modifier.fillMaxWidth(),
//            containerColor = colorResource(R.color.offwhite_screen_color),
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                // First item: Home
//                NavItem(
//                    item = BottomNavItem.Home,
//                    isSelected = currentRoute == BottomNavItem.Home.route,
//                    onClick = {
//                        navController.navigate(BottomNavItem.Home.route) {
//                            popUpTo(navController.graph.startDestinationId) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
//
//                // Second item: Haven (Tools)
//                NavItem(
//                    item = BottomNavItem.Tools, // Using Tools which has "Haven" as title
//                    isSelected = currentRoute == BottomNavItem.Tools.route,
//                    onClick = {
//                        navController.navigate(BottomNavItem.Tools.route) {
//                            popUpTo(navController.graph.startDestinationId) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
//
//                // Empty space for FAB
//                Box(
//                    modifier = Modifier.padding(16.dp)
//                )
//
//                // Third item: Community
//                NavItem(
//                    item = BottomNavItem.Community,
//                    isSelected = currentRoute == BottomNavItem.Community.route,
//                    onClick = {
//                        navController.navigate(BottomNavItem.Community.route) {
//                            popUpTo(navController.graph.startDestinationId) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
//
//                // Fourth item: Profile
//                NavItem(
//                    item = BottomNavItem.Profile,
//                    isSelected = currentRoute == BottomNavItem.Profile.route,
//                    onClick = {
//                        navController.navigate(BottomNavItem.Profile.route) {
//                            popUpTo(navController.graph.startDestinationId) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
//            }
//        }
//
//        // FAB in the middle - make it circular with the owl icon
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .size(72.dp) // Make it larger to match design
//                .padding(top = 0.dp, bottom = 8.dp, end=8.dp)
//                .clickable { navController.navigate(BottomNavItem.Chatbot.route) {
//               popUpTo(navController.graph.startDestinationId) { saveState = true
//               } // ✅ Avoids multiple instances
//                launchSingleTop = true // ✅ Ensures only one instance of Chatbot
//               restoreState = true
//            } }// Adjust to match the design
//        ) {
//            // Owl icon
//            Image(
//                painter = painterResource(id = BottomNavItem.Chatbot.imageRes),
//                contentDescription = BottomNavItem.Chatbot.title,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(5.dp),
//                contentScale = ContentScale.Fit
//            )
//        }
//    }
//}

@Composable
private fun NavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClick() }
    ) {

        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Fit,
            colorFilter = if (isSelected) ColorFilter.tint(colorResource(R.color.purple_500)) else null
        )

        Text(
            text = item.title,
            color = if (isSelected) colorResource(R.color.purple_500) else Color.Gray,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun BottomNavigationBar(navController: NavController) {
    val bottomMenuItemsList = prepareBottomMenu()
    val contextForToast = LocalContext.current.applicationContext
    var selectedItem by remember {
        mutableStateOf("Home")
    }
    BottomAppBar(
        cutoutShape = CircleShape,
        contentColor = colorResource(id = R.color.black),
        backgroundColor = colorResource(id = R.color.offwhite_screen_color),
        modifier = Modifier.padding(horizontal = 0.dp),
    ) {
        bottomMenuItemsList.forEachIndexed { index, bottomMenuItem ->
            if (index == 2) {
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    enabled = false,
                    modifier = Modifier.padding(horizontal = 0.dp) // Reduce padding for the empty slot
                )
            }
            BottomNavigationItem(
                selected = (selectedItem == bottomMenuItem.label),
                onClick = {
                    selectedItem = bottomMenuItem.label
                    navController.navigate(bottomMenuItem.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = bottomMenuItem.icon,
                        contentDescription = bottomMenuItem.label,
                        modifier = Modifier.height(20.dp),
                        tint = Color.Black
                    )
                },
                label = {
                    Text(
                        text = bottomMenuItem.label,
                        modifier = Modifier.padding(top = 14.dp),
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                },
                alwaysShowLabel = true,
                enabled = true,
           )
        }
    }
}

data class BottomMenuItem(
    val route: String,
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem(
            route = "home",
            label = "Home",
            icon = painterResource(id = R.drawable.ic_home)
        ),
        BottomMenuItem(
            route = "tools",
            label = "Haven",
            icon = painterResource(id = R.drawable.ic_haven)
        ),
        BottomMenuItem(
            route = "community",
            label = "Community",
            icon = painterResource(id = R.drawable.ic_community)
        ),
        BottomMenuItem(
            route = "profile",
            label = "Profile",
            icon = painterResource(id = R.drawable.ic_profile)
        ),
    )
}

@Composable
fun FloatingActionButton(navController: NavController) {
    Box(
        Modifier
            .size(70.dp)
            .clip(CircleShape)
            .clickable {
                navController.navigate(BottomNavItem.Chatbot.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_chatbot),
            contentDescription = "Chatbot",
            modifier = Modifier.matchParentSize().align(Alignment.Center),
        )
    }
}
@Composable
@Preview
fun FABPreview(){
    val navController = NavHostController(LocalContext.current)
   BottomNavigationBar(navController)
}
