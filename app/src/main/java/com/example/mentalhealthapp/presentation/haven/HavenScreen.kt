package com.example.mentalhealthapp.presentation.haven

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.home.BottomNavigationBar
import com.example.mentalhealthapp.presentation.home.FloatingActionButton


@Composable
fun HavenScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(navController)
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = colorResource(R.color.offwhite_screen_color),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HavenScreenContent()
        }
    }
}

@Composable
fun HavenScreenContent(modifier: Modifier = Modifier) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            // Background image needs to be in a Box to position it properly
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)  // Reduced from 200dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.haven_topdesign),
                    contentDescription = "Haven",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.sun))
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = LottieConstants.IterateForever
                )
                //Lottie Animation
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .height(240.dp)  // Reduced from 240dp
                        .padding(top = 45.dp)  // Reduced from 45dp
                )
            }
        }

        item {
            // Welcome Text - reduced size
            Text(
                text = "Welcome to Haven",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,  // Reduced from 40sp
                color = colorResource(R.color.teal_text_color),
                modifier = Modifier.padding(bottom = 12.dp)  // Reduced from 16dp
            )
        }

        // ImageButtons
        item {
            ImageButton(
                imagePainter = painterResource(R.drawable.sensory_therapy_btn),
                onClick = {}
            )
        }

        item {
            ImageButton(
                imagePainter = painterResource(R.drawable.todo_list_btn),
                onClick = {}
            )
        }

        item {
            ImageButton(
                imagePainter = painterResource(R.drawable.thought_journal_btn),
                onClick = {}
            )
        }

        item {
            ImageButton(
                imagePainter = painterResource(R.drawable.govt_schemes_btn),
                onClick = {}
            )
        }

        item {
            // Articles section
            ArticlesCuratedForYou(
                articles = dummyArticles(),
                onArticleClick = { /* Handle article click */ }
            )
        }
    }
}

@Composable
fun ImageButton(imagePainter: Painter, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(110.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(22.dp))
                .clickable(onClick = onClick),
            contentScale = ContentScale.Crop
        )
    }
}

data class Article(
    val id: String,
    val title: String,
    val imageRes: Painter,
    val backgroundColor: Color = Color(0xFF68A99A)
)

@Composable
fun ArticlesCuratedForYou(
    articles: List<Article>,
    onArticleClick: (Article) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)  // Reduced from 16dp
    ) {

        Text(
            text = "Articles curated for you",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 24.sp,  // Added specific font size
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)  // Reduced bottom padding
        )


        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)  // Reduced from 12dp
        ) {
            items(articles) { article ->
                ArticleCard(
                    article = article,
                    onClick = { onArticleClick(article) }
                )
            }
        }
    }
}

@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit
) {
    // Card with rounded corners
    Card(
        modifier = Modifier
            .width(240.dp)  // Reduced from 240dp
            .height(180.dp),  // Reduced from 180dp
        shape = RoundedCornerShape(16.dp),  // Reduced from 16dp
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = article.backgroundColor
        ),
       border = androidx.compose.foundation.BorderStroke(
           width = 1.dp,
           color = Color.Black
       )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Article Image (top portion)
            Image(
                painter = article.imageRes,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)  // Reduced from 120dp
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),  // Reduced from 16dp
                contentScale = ContentScale.Crop,

            )


            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            )
        }
    }
}
@Preview
@Composable
fun HavenScreenPreview() {
    val navController: NavController = rememberNavController()
    HavenScreen(navController)
}


@Composable
fun dummyArticles() = listOf(
    Article(
        id = "1",
        title = "Navigating Social Life with Neurodiversity",
        imageRes = painterResource(R.drawable.neurodivergent_article_1)
    ),
    Article(
        id = "2",
        title = "Self-Care Tips for Neurodivergent Minds",
        imageRes = painterResource(R.drawable.neurodivergent_article_2)
    ),
)
