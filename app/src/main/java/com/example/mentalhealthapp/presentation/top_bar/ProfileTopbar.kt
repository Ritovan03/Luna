package com.example.mentalhealthapp.presentation.top_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mentalhealthapp.R

@Composable
fun ProfileTopBar() {
    Box(
        modifier = Modifier.fillMaxWidth().height(230.dp).background(color = colorResource( R.color.offwhite_screen_color))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_top_design),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Profile image centered horizontally and positioned at the bottom of the top section
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            ProfileImage(
                imageRes = painterResource(R.drawable.ic_profile_pic),
                onEditClick = {},
                modifier = Modifier.padding(top = 120.dp)
            )
        }
    }
}

@Composable
fun ProfileImage(
    imageRes: Painter,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Profile image
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(3.dp, Color.White, CircleShape)
                .background(Color.LightGray.copy(alpha = 0.3f))
        ) {
            Image(
                painter =imageRes,
                contentDescription = "Profile Picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Edit button positioned at the bottom-right of the profile image
       IconButton(
            onClick = onEditClick,
            modifier = Modifier
                .size(12.dp)  // Reduced from 16.dp to 12.dp
                .offset(x = 30.dp, y = 30.dp)
                .background(color = colorResource(R.color.brown), CircleShape)
                .border(0.5.dp, Color.White, CircleShape)  // Reduced border thickness
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Profile Picture",
                tint = Color.White,
                modifier = Modifier.matchParentSize()
            )
        }
    }
}
