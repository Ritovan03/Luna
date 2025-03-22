package com.example.mentalhealthapp.presentation.community

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.ui.theme.UrbanistFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(navController: NavHostController) {
    Scaffold(
    modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.offwhite_screen_color)
    ) { innerPadding ->
            CommunityScreenContent(Modifier.padding(innerPadding))
    }
}


// Data models
data class Community(
    val imageResId : Int,
    val id: String,
    val name: String,
    )

// Sample data - moved to a separate object for better organization
object SampleData {
    val communities = listOf(
        Community(R.drawable.neurodivergent_article_2,"1", "ZigZag Minds"),
        Community(R.drawable.neuromind_community,"2", "AutiVerse"),
        Community(R.drawable.dyslexia_community,"3", "Spelling Rebels"),
        Community(R.drawable.neurodivergent_article_1,"4", "NeuroMinds")
    )
}

@Composable
fun CommunityScreen() {
    CommunityScreenContent()
}

@Composable
fun CommunityScreenContent(modifier: Modifier = Modifier) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.community_top_design),
                    contentDescription = "Community",
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Welcome to",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.black),
                        fontFamily = UrbanistFont,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                    //Spacer(modifier = Modifier.height(.dp))
                    Text(
                        text = "Community",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.black),
                        fontFamily = UrbanistFont,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // All communities section
        item {
            AllCommunitiesSection(communities = SampleData.communities)
        }

        // My feed / My communities tabs
        item {
            CommunityTabs()
        }
    }
}

@Composable
fun CommunityTabs() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("My feed", "My communities")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .height(3.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp)
                        )
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontFamily = UrbanistFont,
                            fontWeight = FontWeight.Medium
                        )
                    }
                )
            }
        }

        // Display different content based on selected tab
        when (selectedTab) {
            0 -> MyFeedContent() // My feed tab
            1 -> MyCommunitiesContent() // My communities tab
        }
    }
}

@Composable
fun MyFeedContent() {
    Column {
        // Post creation card
        CreatePostCard()

        // Posted in community info and post
        CommunityPostSection()
    }
}

@Composable
fun MyCommunitiesContent() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        CommunityCard(
            imageResId = R.drawable.zigzag_community_card,
            modifier = Modifier.padding(bottom = 16.dp),
            painterDescription = "ZigZag minds community"
        )
        CommunityCard(
            imageResId = R.drawable.spelling_rebels_community,
            modifier = Modifier.padding(bottom = 16.dp),
            painterDescription = "Spelling Rebels community"
        )
        CommunityCard(
            imageResId = R.drawable.autiverse_community,
            modifier = Modifier.padding(bottom = 16.dp),
            painterDescription = "Autiverse community"
        )
    }
}

@Composable
fun CommunityCard(
    imageResId: Int,
    modifier: Modifier = Modifier,
    painterDescription: String = "Community"
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start=8.dp, end=8.dp)
            .height(160.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Image(
            painter = painterResource(id = imageResId), // You'll need this image resource
            contentDescription = painterDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun AllCommunitiesSection(communities: List<Community>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        // Section header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "All communities",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                fontFamily = UrbanistFont
            )
            Text(
                text = "View all",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = UrbanistFont
            )
        }

        // Communities list
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(communities) { community ->
                CommunityItem(community)
            }
        }
    }
}

@Composable
fun CreatePostCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.community_screen_color)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // User input row
            Row(verticalAlignment = Alignment.CenterVertically) {
                // User avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile_pic), // You'll need this image resource
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "write your post here",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action buttons - Fixed layout to prevent text wrapping
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { },
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.weight(3f),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Add your post in",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.weight(2f),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Publish Post",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun CommunityPostSection() {
    Column {
        // Posted in community info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Posted in ZigZag Minds",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "view community",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Post card
        CommunityPost()
    }
}

@Composable
fun CommunityPost() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.community_screen_color)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // User info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.neurodivergent_article_2), // You'll need this image resource
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Shyam Modi",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Bangalore, India",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Post content
            Text(
                text = "We get a bad rap for being \"distracted\" or \"disorganized,\" but the truth? Our brains are wired for innovation. While others follow a straight line, we zigzag through ideas, connecting dots no one else sees. That's why so many ADHD-ers excel in fields like art, entrepreneurship, and tech—our ability to think outside the box is the magic.\n\nFun fact: Did you know Einstein, Da Vinci, and Simone Biles are suspected to have had ADHD? Yeah, we're in good company.",
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Post engagement
            PostEngagementBar()
        }
    }
}

@Composable
fun PostEngagementBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Likes
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "16",
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Comments
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "24",
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Share
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "Share",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun CommunityItem(community: Community) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Community image
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .border(1.dp, Color.Black, CircleShape)
        ){
            Image(
                painter = painterResource( community.imageResId), // You'll need this image resource
                contentDescription = "Community",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = community.name,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines =1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(85.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommunityScreenPreview() {
    CommunityScreen()
}