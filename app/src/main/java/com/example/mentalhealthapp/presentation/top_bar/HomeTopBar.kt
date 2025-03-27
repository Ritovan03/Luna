package com.example.mentalhealthapp.presentation.top_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentalhealthapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    DailyStreakTopBar(
        date = "Sat, 23 February 2025",
        username = "Shyam",
        xp = 90,
        mood = "Happy",
        streakDays = 3,
        quoteOfTheDay = "\"For every minute you're angry, You lose 60 seconds of happiness\""
    )
}

@Composable
fun DailyStreakTopBar(
    date: String = "",
    username: String = "",
    xp: Int = 0,
    mood: String = "",
    streakDays: Int = 0,
    quoteOfTheDay: String = ""
) {
    // Get status bar height
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
            .background(colorResource(R.color.HomeScreen_primary_scaffold_color))
            .padding(top = statusBarHeight)
            .padding(end=16.dp, start = 16.dp, bottom = 16.dp)
    ) {
        Column {
            // Date and Notification in the same row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateDisplay(date)
                NotificationButton()
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Modified user info row without the notification button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileImage()

                UserStats(
                    username = username,
                    xp = xp,
                    mood = mood,
                    streakDays = streakDays
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            QuoteOfTheDay(quote = quoteOfTheDay)
        }
    }
}

@Composable
private fun DateDisplay(date: String) {
    Text(
        text = date,
        fontSize = 16.sp,
        color = Color.Black,
        modifier = Modifier.padding(top = 8.dp) // Add top padding to align with notification
    )
}
// User Info Component
@Composable
private fun UserInfoRow(
    username: String,
    xp: Int,
    mood: String,
    streakDays: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileImage()

            UserStats(
                username = username,
                xp = xp,
                mood = mood,
                streakDays = streakDays
            )
        }

        NotificationButton()
    }
}

// Profile Image Component
@Composable
private fun ProfileImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_profile_pic),
        contentDescription = "Profile Picture",
        modifier = Modifier
            .size(48.dp)
            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

// User Stats Component
@Composable
private fun UserStats(
    username: String,
    xp: Int,
    mood: String,
    streakDays: Int
) {
    Column {
        Text(
            text = "Hi, $username!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatItem(
                iconResId = R.drawable.ic_star,
                text = "$xp xp",
            )

            StatItem(
                iconResId = R.drawable.ic_happy,
                text = mood,
            )

            StatItem(
                iconResId = R.drawable.ic_fire,
                text = "${streakDays}d Streak",
            )
        }
    }
}

// Individual Stat Item Component
@Composable
private fun StatItem(
    iconResId: Int,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

// Notification Button Component
@Composable
private fun NotificationButton() {
    Box(
        modifier = Modifier
            .padding(top = 12.dp,end=6.dp) // Add top padding to position slightly lower
            .size(42.dp)
            .clip(CircleShape)
            .background(colorResource(R.color.HomeScreen_primary_variant_scaffold_color)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_notification),
            contentDescription = "Notifications",
            modifier = Modifier.fillMaxSize(),
            tint = Color.Black
        )
    }
}

// Quote of the Day Component
@Composable
private fun QuoteOfTheDay(quote: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color.Black.copy(alpha = 0.25f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.HomeScreen_primary_variant_scaffold_color))
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuoteHeader()

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = quote,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

// Quote Header Component
@Composable
private fun QuoteHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Quote of the day",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "heart",
            tint = colorResource(R.color.teal_700),
            modifier = Modifier.size(16.dp)
        )
    }
}