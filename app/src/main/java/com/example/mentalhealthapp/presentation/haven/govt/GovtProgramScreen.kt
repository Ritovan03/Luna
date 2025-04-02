package com.example.mentalhealthapp.presentation.haven.govt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.ui.theme.UrbanistFont

data class GovtScheme(
    val title: String,
    val description: String,
    val category: SchemeCategory,
    val eligibility: String,
    val location: String
)

enum class SchemeCategory(val color: Color, val label: String) {
    EDUCATION(Color(0xFF90CAF9), "Education"),
    EMPLOYMENT(Color(0xFFC7E18A), "Employment"), // Using green_color
    HEALTHCARE(Color(0xFFFFCC80), "Healthcare"),
    FINANCIAL(Color(0xFFE57373), "Financial Aid")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GovtProgramScreen(mainNavController: NavHostController) {
    // Updated colors to match ThoughtJournalScreen
    val backgroundColor = colorResource(R.color.offwhite_screen_color)
    val primaryColor = colorResource(R.color.home_topbar_color)
    val accentColor = colorResource(R.color.green_color)
    val textPrimaryColor = colorResource(R.color.brown_color)

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<SchemeCategory?>(null) }

    val schemes = remember {
        listOf(
            GovtScheme(
                "Disability Educational Support",
                "Financial assistance and support services for students with learning disabilities",
                SchemeCategory.EDUCATION,
                "Students diagnosed with specific learning disabilities",
                "National"
            ),
            GovtScheme(
                "Workplace Accommodation Grant",
                "Funding for workplace modifications and assistive technology",
                SchemeCategory.EMPLOYMENT,
                "Employed individuals with autism or ADHD",
                "All States"
            ),
            GovtScheme(
                "Mental Healthcare Coverage",
                "Comprehensive mental health services coverage including therapy and medications",
                SchemeCategory.HEALTHCARE,
                "All diagnosed neurodivergent individuals",
                "National"
            ),
            GovtScheme(
                "Special Needs Support Fund",
                "Monthly financial assistance for care and development needs",
                SchemeCategory.FINANCIAL,
                "Low-income families with neurodivergent dependents",
                "State-specific"
            )
        )
    }

    val filteredSchemes = schemes.filter {
        (searchQuery.isEmpty() || it.title.contains(searchQuery, ignoreCase = true)) &&
                (selectedCategory == null || it.category == selectedCategory)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Enhanced header with green background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = primaryColor,
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    )
                    .padding(24.dp)
            ) {
                Column {
                    // Top bar with back button
                    Row(
                        modifier = Modifier.padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .border(1.dp, textPrimaryColor, CircleShape)
                                .clickable { mainNavController.popBackStack() },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "(",
                                color = textPrimaryColor,
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = "Support Programs",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = textPrimaryColor,
                            fontFamily = UrbanistFont
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Find government assistance for neurodivergent individuals",
                        color = textPrimaryColor,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Elevated search bar
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White),
                        placeholder = { Text("Search for programs...", color = Color.Gray) },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                tint = primaryColor
                            )
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Category filters with green accent color for indicator
            ScrollableTabRow(
                selectedTabIndex = SchemeCategory.values().indexOf(selectedCategory) + 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.7f)),
                edgePadding = 0.dp,
                indicator = { tabPositions ->
                    if (selectedCategory != null || tabPositions.isNotEmpty()) {
                        val selectedIndex = if (selectedCategory == null) 0 else SchemeCategory.values().indexOf(selectedCategory) + 1
                        if (selectedIndex < tabPositions.size) {
                            Box(
                                Modifier
                                    .tabIndicatorOffset(tabPositions[selectedIndex])
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                                    .background(accentColor)
                            )
                        }
                    }
                },
                divider = {}
            ) {
                TabCategoryItem(
                    text = "All",
                    selected = selectedCategory == null,
                    color = primaryColor,
                    onClick = { selectedCategory = null }
                )

                SchemeCategory.values().forEach { category ->
                    TabCategoryItem(
                        text = category.label,
                        selected = selectedCategory == category,
                        color = category.color,
                        onClick = { selectedCategory = category }
                    )
                }
            }

            // Results count
            Text(
                text = "${filteredSchemes.size} programs available",
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                color = primaryColor.copy(alpha = 0.7f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            // Schemes list with improved cards
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredSchemes) { scheme ->
                    ImprovedSchemeCard(scheme = scheme, primaryColor = primaryColor)
                }

                // Add some bottom padding for better scrolling experience
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
fun TabCategoryItem(text: String, selected: Boolean, color: Color, onClick: () -> Unit) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = if (selected) color else Color.Gray,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ImprovedSchemeCard(scheme: GovtScheme, primaryColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Category chip at the top
            CategoryChip(category = scheme.category)

            Spacer(modifier = Modifier.height(12.dp))

            // Icon based on category
            val icon = when(scheme.category) {
                SchemeCategory.EDUCATION -> Icons.Outlined.Book
                SchemeCategory.FINANCIAL -> Icons.Outlined.AttachMoney
                SchemeCategory.HEALTHCARE -> Icons.Default.LocationOn  // Using LocationOn as placeholder
                SchemeCategory.EMPLOYMENT -> Icons.Default.Search      // Using Search as placeholder
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = scheme.category.color,
                modifier = Modifier
                    .size(36.dp)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = scheme.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = scheme.category.color,
                fontFamily = UrbanistFont
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = scheme.description,
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Eligibility section
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Eligibility: ",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = scheme.category.color
                )
                Text(
                    text = scheme.eligibility,
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Location with improved visual
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.Gray.copy(alpha = 0.7f),
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = scheme.location,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { /* Navigate to details */ },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = scheme.category.color,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("View Details", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Outlined.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryChip(category: SchemeCategory) {
    // Using the same chip style as in ThoughtJournalScreen for consistency
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(category.color.copy(alpha = 0.2f))
            .border(
                width = 1.dp,
                color = category.color,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = category.label,
            color = category.color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
@Preview
@Composable
fun GovtProgramScreenPreview() {
    // Preview of the GovtProgramScreen
    val navController = NavHostController(context = LocalContext.current)
    GovtProgramScreen(mainNavController = navController)
}