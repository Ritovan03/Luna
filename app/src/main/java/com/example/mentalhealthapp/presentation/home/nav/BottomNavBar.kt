package com.example.mentalhealthapp.presentation.home.nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun BottomNavigationBar(navController: NavController) {
    val bottomMenuItemsList = prepareBottomMenu()
    var selectedItem by remember {
        mutableStateOf("Home")
    }

    BottomAppBar(
        cutoutShape = CircleShape,  // This creates the cutout for the FAB
        contentColor = colorResource(id = R.color.black),
        backgroundColor = colorResource(id = R.color.offwhite_screen_color),
        elevation = 3.dp,
        modifier = Modifier.height(70.dp)
    ) {
        bottomMenuItemsList.forEachIndexed { index, bottomMenuItem ->
            // Add appropriate spacing for the FAB in the middle
            if (index == 2) {
                // This empty item creates space for the FAB
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    enabled = false,
                    // Use weight instead of padding to ensure proper spacing
                    modifier = Modifier.weight(1f)
                )
            }

            val isSelected = selectedItem == bottomMenuItem.label
            val scale by animateFloatAsState(
                targetValue = if (isSelected) 1.2f else 1.0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                label = "IconScale"
            )

            val iconColor = if (isSelected)
                colorResource(id = R.color.blue_200) else Color.Black

            BottomNavigationItem(
                selected = isSelected,
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Animate indicator
                        AnimatedVisibility(
                            visible = isSelected,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            // You can add a small indicator here if desired
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Animated icon
                        Icon(
                            painter = bottomMenuItem.icon,
                            contentDescription = bottomMenuItem.label,
                            modifier = Modifier
                                .scale(scale)
                                .size(24.dp),
                            tint = iconColor
                        )
                    }
                },
                label = {
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 300))
                    ) {
                        Text(
                            text = bottomMenuItem.label,
                            modifier = Modifier.padding(top = 2.dp),
                            color = iconColor,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                },
                alwaysShowLabel = true,
                enabled = true,
                // Add weight to ensure equal spacing
                modifier = Modifier.weight(1f)
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
            label = "Socials",
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
fun FloatingActionButton(navController: NavHostController) {
    Box(
        Modifier
            .size(72.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            .clickable(
                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                indication = null,
                onClick = {
                    // Use mainNavController instead of bottom nav controller
                    navController.navigate(Route.Chatbot.route) {
                        launchSingleTop = true
                    }
                }
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_chatbot),
            modifier = Modifier.matchParentSize(),
            contentDescription = "Chatbot"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FABPreview() {
    val navController = NavHostController(LocalContext.current)
    BottomNavigationBar(navController)
}
