package com.example.ajon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

val DarkBackground = Color(0xFF121212)
val CardBackground = Color(0xFF1E1E1E)
val AjonGreen = Color(0xFF00C853)
val GrayText = Color(0xFFAAAAAA)
val DarkGreenBadge = Color(0xFF1E3A2F)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(background = DarkBackground)) {
                AjonApp()
            }
        }
    }
}

@Composable
fun AjonApp() {
    Scaffold(
        topBar = { TopHeader() },
        containerColor = DarkBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item { TabRowSection() }
            item { SearchBar() }
            item { 
                Text(
                    text = "1500+ business ideas",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) 
            }
            item { PlaceholderTutorialCard() }
        }
    }
}

@Composable
fun TopHeader() {
    var userCount by remember { mutableStateOf(214900) }
    LaunchedEffect(Unit) {
        while(true) {
            delay(2000)
            userCount += 1000
        }
    }
    val formattedCount = String.format("%.1fk", userCount / 1000.0)

    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("💰", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("AJON", color = AjonGreen, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
            Text("1500+ Ways To Make Money", color = GrayText, fontSize = 12.sp)
        }
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                modifier = Modifier.background(DarkGreenBadge, RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Group, contentDescription = null, tint = AjonGreen, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(formattedCount, color = AjonGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(modifier = Modifier.size(12.dp).background(Color.Red, CircleShape))
        }
    }
}

@Composable
fun TabRowSection() {
    val tabs = listOf("Tutorials", "Unlock", "Helper", "Notes", "Assist")
    val icons = listOf(Icons.Default.MenuBook, Icons.Default.Lock, Icons.Default.Psychology, Icons.Default.Edit, Icons.Default.Chat)
    var selectedTab by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = selectedTab == index
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) AjonGreen else Color.Transparent)
                    .clickable { selectedTab = index }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Icon(icons[index], contentDescription = title, tint = if (isSelected) Color.Black else GrayText, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text(title, color = if (isSelected) Color.Black else GrayText, fontSize = 10.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
            }
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        placeholder = { Text("Search 1500+ business ideas...", color = GrayText) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = GrayText) },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AjonGreen,
            unfocusedBorderColor = Color.DarkGray,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}

@Composable
fun PlaceholderTutorialCard() {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).border(1.dp, Color(0xFF2A2A2A), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth().height(180.dp).background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                Text("Image Placeholder", color = GrayText)
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text("Tutorial Title Here", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                
                Box(modifier = Modifier.background(DarkGreenBadge, RoundedCornerShape(16.dp)).padding(horizontal = 12.dp, vertical = 4.dp)) {
                    Text("Category", color = AjonGreen, fontSize = 12.sp)
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                Text("Description will be added later...", color = GrayText, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(12.dp))
                
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AjonGreen),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("View Full Tutorial", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
