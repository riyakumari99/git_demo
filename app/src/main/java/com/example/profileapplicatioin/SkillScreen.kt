package com.example.profileapplicatioin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background


import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import com.example.profileapplicatioin.ui.theme.blue
import androidx.compose.ui.unit.sp
import com.example.profileapplicatioin.ui.theme.lightyellow
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import com.example.profileapplicatioin.ui.theme.lightgray2
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.profileapplicatioin.ui.theme.black
import com.example.profileapplicatioin.ui.theme.darkGray
import com.example.profileapplicatioin.ui.theme.lightgray3
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import com.example.profileapplicatioin.ui.theme.white
import java.util.logging.Level
import android.R.attr.top
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.OutlinedTextField


data class SkillData(
    val imageIcon: Int = R.drawable.kotlin_logo,
    val name: String,
    val level: Float
)

@Composable
fun SkillScreen() {
    var showAddSkillDialog by remember { mutableStateOf(false) }

    var skills by remember {
        mutableStateOf(
            listOf(
                SkillData(R.drawable.kotlin_logo, "Kotlin", 0.9f),
                SkillData(R.drawable.img, "Jetpack Compose", 0.85f),
                SkillData(R.drawable.android_logo, "Android", 0.8f),
                SkillData(R.drawable.ui_uxlogo, "UI/UX", 0.7f),
                SkillData(R.drawable.javalogo, "Java", 0.75f),
                SkillData(R.drawable.img_1, "Git", 0.8f)

            )
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            ProfileHeader()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "My Skills",
                    fontSize = 20.sp,
                    color = blue,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp, 4.dp, 4.dp, 80.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.weight(1f)

                ) {
                    items(skills) { skill ->
                        SkillCard(skill = skill)
                    }

                }

                Button(
                    onClick = { showAddSkillDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue,
                        contentColor = white
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Skill",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Add New Skill")
                }
                if (showAddSkillDialog == true) {
                    AddSkillDialog(
                        onDismiss = { showAddSkillDialog = false },
                        onSave = { name, level ->
                            skills = skills + SkillData(
                                R.drawable.img, // default icon, you can allow selection later
                                name,
                                level
                            )
                            showAddSkillDialog = false
                        }

                    )
                }
            }
        }
    }
}


        @Composable
        fun SkillCard(
            skill: SkillData
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = lightgray2)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(lightyellow),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = skill.imageIcon),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = skill.name,
                        fontSize = 16.sp,
                        color = black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    LinearProgressIndicator(
                        progress = { skill.level },
                        modifier = Modifier.fillMaxWidth(),
                        color = blue,
                        trackColor = lightgray3,
                        strokeCap = StrokeCap.Square,
                    )
                    Text(
                        text = "${(skill.level * 100).toInt()}%",
                        fontSize = 12.sp,
                        color = darkGray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        @Composable
        fun AddSkillDialog(
            onDismiss: () -> Unit,
            onSave: (String, Float) -> Unit
        ) {
            var skillName by remember { mutableStateOf("") }
            var skillLevelText by remember { mutableStateOf("") }

            AlertDialog(
                onDismissRequest = { onDismiss() },
                confirmButton = {
                    TextButton(onClick = {
                        val level = skillLevelText.toFloatOrNull()?.coerceIn(0f, 1f) ?: 0f
                        if (skillName.isNotBlank()) {
                            onSave(skillName, level)
                        }
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Add New Skill") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = skillName,
                            onValueChange = { skillName = it },
                            label = { Text("Skill Name") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = skillLevelText,
                            onValueChange = { skillLevelText = it },
                            label = { Text("Skill Level (0.0 - 1.0)") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            )
        }







