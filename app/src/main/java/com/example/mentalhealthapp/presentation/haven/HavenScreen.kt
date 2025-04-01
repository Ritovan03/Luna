package com.example.mentalhealthapp.presentation.haven

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route
import com.example.mentalhealthapp.ui.theme.UrbanistFont

/**
 * 🏠 HavenScreen
 *
 * Main haven screen that provides a safe space with various mental health resources.
 * Contains interactive buttons for different features and curated articles.
 */
@Composable
fun HavenScreen(navController: NavHostController,
                mainNavController: NavHostController) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        // 👋 Welcome section
        item {
            Text(
                text = "Welcome to Haven",
                fontFamily = UrbanistFont,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = colorResource(R.color.teal_text_color),
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        // 🧠 Feature buttons section - Each button represents a different mental health tool
        item {
            ImageButton(
                title = "Sensory Therapy",
                imagePainter = painterResource(R.drawable.sensory_therapy_btn),
                onClick = {
                    mainNavController.navigate(Route.SensoryTherapy.route)
                }
            )
        }

        item {
            ImageButton(
                title = "To-do List",
                imagePainter = painterResource(R.drawable.todo_list_btn),
                onClick = {
                    mainNavController.navigate(Route.Todo.route)
                }
            )
        }

        item {
            ImageButton(
                title = "Thought Journal",
                imagePainter = painterResource(R.drawable.thought_journal_btn),
                onClick = {}
            )
        }

        item {
            ImageButton(
                title = "      Government\n Schemes & Support",
                imagePainter = painterResource(R.drawable.govt_schemes_btn),
                onClick = {
                    mainNavController.navigate(Route.GovtProgram.route)
                }
            )
        }

        // 📚 Articles section - Horizontal scrollable list of articles
        item {
            ArticlesCuratedForYou(
                articles = dummyArticles(),
                onArticleClick = { /* Handle article click */ }
            )
        }
    }
}

/**
 * 🔘 ImageButton
 *
 * Custom button that displays a full-width image that's clickable.
 * Used for the main feature buttons in the Haven screen.
 *
 * @param imagePainter The image to display as the button
 * @param onClick The action to perform when clicked
 */
@Composable
fun ImageButton(title: String,imagePainter: Painter, onClick: () -> Unit) {
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
        Text(
            text = title,
            fontWeight= FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(end = 24.dp)
        )
    }
}

/**
 * 📝 Article data class
 *
 * Represents an article with its metadata.
 *
 * @param id Unique identifier for the article
 * @param title The title text to display
 * @param imageRes The image resource to show
 * @param backgroundColor The background color for the card
 */
data class Article(
    val id: String,
    val title: String,
    val imageRes: Painter,
    val backgroundColor: Color = Color(0xFF68A99A)
)

/**
 * 📚 ArticlesCuratedForYou
 *
 * Displays a horizontal scrollable row of article cards with a section title.
 *
 * @param articles List of articles to display
 * @param onArticleClick Callback when an article is clicked
 */
@Composable
fun ArticlesCuratedForYou(
    articles: List<Article>,
    onArticleClick: (Article) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // 📋 Section title
        Text(
            text = "Articles curated for you",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        // 🔄 Horizontal scrollable row of articles
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
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

/**
 * 🎴 ArticleCard
 *
 * Card component that displays an article with image and title.
 *
 * @param article The article data to display
 * @param onClick Callback when the card is clicked
 */
@Composable
fun ArticleCard(
    article: Article,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(240.dp)
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = article.backgroundColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Black
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // 🖼️ Article image (top portion)
            Image(
                painter = article.imageRes,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop,
            )

            // 📝 Article title (bottom portion)
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

/**
 * 🧪 Mock Data
 *
 * Returns sample articles for testing and preview purposes.
 * Replace with real data from repository when implementing.
 */
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

@Preview(showBackground = true)
@Composable
fun HavenScreenPreview() {
    val navController = rememberNavController()
    HavenScreen(
        navController = navController, mainNavController = navController,
    )
}