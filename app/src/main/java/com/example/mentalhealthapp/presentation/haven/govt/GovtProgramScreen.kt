package com.example.mentalhealthapp.presentation.haven.govt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class GovtScheme(
    val title: String,
    val description: String,
    val category: SchemeCategory,
    val eligibility: String,
    val location: String
)

enum class SchemeCategory(val color: Color, val label: String) {
    EDUCATION(Color(0xFF90CAF9), "Education"),
    EMPLOYMENT(Color(0xFFA5D6A7), "Employment"),
    HEALTHCARE(Color(0xFFFFCC80), "Healthcare"),
    FINANCIAL(Color(0xFFEF9A9A), "Financial Aid")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GovtProgramScreen(mainNavController: NavHostController) {
    val backgroundColor = Color(0xFFF8F5F1)
    val textBrownColor = Color(0xFF5A3C2C)

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
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar with back button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFF65635F), CircleShape)
                        .clickable { mainNavController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "(",
                        color = Color(0xFF65635F),
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Government Programs",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = textBrownColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search schemes...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = textBrownColor,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Category filters
            ScrollableTabRow(
                selectedTabIndex = SchemeCategory.values().indexOf(selectedCategory) + 1,
                modifier = Modifier.fillMaxWidth(),
                edgePadding = 0.dp
            ) {
                Tab(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null }
                ) {
                    Text(
                        "All",
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = if (selectedCategory == null) textBrownColor else Color.Gray
                    )
                }

                SchemeCategory.values().forEach { category ->
                    Tab(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category }
                    ) {
                        Text(
                            category.label,
                            modifier = Modifier.padding(vertical = 16.dp),
                            color = if (selectedCategory == category) category.color else Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Schemes list
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredSchemes) { scheme ->
                    SchemeCard(scheme = scheme)
                }
            }
        }
    }
}

@Composable
fun SchemeCard(scheme: GovtScheme) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = scheme.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A2B0F)
                )

                CategoryChip(category = scheme.category)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = scheme.description,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = scheme.location,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                TextButton(
                    onClick = { /* Navigate to details */ }
                ) {
                    Text("Learn More")
                    Icon(
                        imageVector = Icons.Outlined.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryChip(category: SchemeCategory) {
    Surface(
        color = category.color.copy(alpha = 0.2f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = category.label,
            color = category.color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}