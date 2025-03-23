package com.example.mentalhealthapp.presentation.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.compose.foundation.horizontalScroll


data class Medicine(val name: String, val isSelected: Boolean = false)
@Composable
fun QuizScreen7(navController: NavHostController) {
    val backgroundColor = Color(0xFFF8F5F1) // Light beige background
    val selectedGreenColor = Color(0xFF8DB06F) // Green color for selected items
    val brownColor = Color(0xFF64422C) // Brown color for continue button
    val textBrownColor = Color(0xFF5A3C2C) // Brown color for text

    // List of hardcoded medicines (A-D)
    val medicinesList = remember {
        listOf(
            "Abilify",
            "Abilify Maintena",
            "Abiraterone",
            "Acetaminophen",
            "Acyclovir",
            "Adderall",
            "Advil",
            "Albuterol",
            "Amitriptyline",
            "Amlodipine",
            "Amoxicillin",
            "Atorvastatin",
            "Azithromycin",
            "Benadryl",
            "Bisoprolol",
            "Budesonide",
            "Bupropion",
            "Carvedilol",
            "Cefdinir",
            "Cephalexin",
            "Cetirizine",
            "Ciprofloxacin",
            "Citalopram",
            "Clindamycin",
            "Clonazepam",
            "Cyclobenzaprine",
            "Cymbalta",
            "Dexamethasone",
            "Diazepam",
            "Diclofenac",
            "Diphenhydramine",
            "Doxycycline",
            "Duloxetine"
        )
    }

    // State to track selected medicines
    var selectedMedicines by remember {
        mutableStateOf(
            setOf(
                "Aspirin",
                "Ibuprofen"
            )
        )
    } // Pre-selected items

    // Active alphabet filter
    var activeAlphabet by remember { mutableStateOf("A") }

    // List of alphabets for filter pills
    val alphabets = listOf("A", "B", "C", "D", "...", "X", "Y", "Z")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar with back button and progress
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { /* Handle back navigation */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_revert),
                        contentDescription = "Back",
                        tint = textBrownColor
                    )
                }

                // Title
                Text(
                    text = "Assessment",
                    color = textBrownColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                // Progress indicator
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFE8D0C0)),
                    color = Color(0xFFE8D0C0)
                ) {
                    Text(
                        text = "10 of 14",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = textBrownColor,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "Please specify your medications!",
                modifier = Modifier.fillMaxWidth(),
                color = textBrownColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Alphabet filter row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray.copy(alpha = 0.3f)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    alphabets.forEach { letter ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 2.dp)
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(
                                    if (activeAlphabet == letter) Color(0xFFE67E22) else Color.Transparent
                                )
                                .clickable { activeAlphabet = letter },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = letter,
                                color = if (activeAlphabet == letter) Color.White else textBrownColor,
                                fontWeight = if (activeAlphabet == letter) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }

                // Search icon
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    tint = textBrownColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Medicines list
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(medicinesList) { medicine ->
                    val isSelected = selectedMedicines.contains(medicine)

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                selectedMedicines = if (isSelected) {
                                    selectedMedicines - medicine
                                } else {
                                    selectedMedicines + medicine
                                }
                            },
                        color = if (isSelected) selectedGreenColor else Color.White
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = medicine,
                                color = if (isSelected) Color.White else textBrownColor,
                                fontWeight = FontWeight.Medium
                            )

                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Selected",
                                    tint = Color.White
                                )
                            } else {
                                // Empty circle
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .border(1.dp, textBrownColor, CircleShape)
                                )
                            }
                        }
                    }
                }
            }

            // Selected medicines chips
            if (selectedMedicines.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Selected:",
                        color = textBrownColor,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Row(
                        modifier = Modifier
                            .horizontalScroll(androidx.compose.foundation.rememberScrollState())
                            .padding(vertical = 4.dp)
                    ) {
                        selectedMedicines.forEach { medicine ->
                            AssistChip(
                                onClick = {
                                    selectedMedicines = selectedMedicines - medicine
                                },
                                label = { Text(text = medicine) },
                                modifier = Modifier.padding(end = 8.dp),
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Remove",
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Continue button
                Button(
                    onClick = {
                        // Log selected medicines
                        Log.d(
                            "MedicationSelection",
                            "Selected Medications: ${selectedMedicines.joinToString(", ")}"
                        )
                        // Navigate to screen 9
                        navController.navigate(Route.Quiz8.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = brownColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Continue",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Continue"
                        )
                    }
                }
            }
        }
    }
}