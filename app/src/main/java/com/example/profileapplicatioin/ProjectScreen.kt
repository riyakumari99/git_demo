package com.example.profileapplicatioin


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import com.example.profileapplicatioin.ui.theme.blue
import com.example.profileapplicatioin.ui.theme.white
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.example.profileapplicatioin.ui.theme.black
import com.example.profileapplicatioin.ui.theme.green
import com.example.profileapplicatioin.ui.theme.lightgreen
import com.example.profileapplicatioin.ui.theme.lightyellow
import androidx.compose.material3.Card
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import com.example.profileapplicatioin.ui.theme.lightgray3
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import com.example.profileapplicatioin.ui.theme.lightgreen2
import com.example.profileapplicatioin.ui.theme.yellow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.material3.OutlinedTextField

data class ProjectData(
    val name: String,
    val techStack: String,
    val description: String,
    val completed: Boolean
)
@Composable
fun ProjectScreen() {

    var selectedCategoryInParent by remember { mutableStateOf("Completed") }
    var projects by remember {mutableStateOf(ProjectList)}
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var tech by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(40.dp))

            ProfileHeader()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ProjectCategoryTabs(
                    selectedCategoryInParent,
                    selectedCategoryInParent = { sentCategory ->
                        selectedCategoryInParent = sentCategory
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                val filterProjects = projects.filter {
                    if (selectedCategoryInParent == "Completed") {
                        it.completed
                    } else {
                        !it.completed
                    }
                }
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(filterProjects) { project ->
                        ProjectCard(project = project)

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Add Project Button
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add New Project")
                }
            }
        }
    }

    // Show Add Project Dialog at screen level
    if (showDialog) {
        AddNewProjectDialog(
            onDismiss = { showDialog = false },
            onAddProject = { name, tech, desc ->
                projects = projects + ProjectData(
                    name = name,
                    techStack = tech,
                    description = desc,
                    completed = false
                )
                showDialog = false
            }
        )
    }
}



            // closes Box
           // closes ProjectScreen







@Composable
fun ProjectCategoryTabs(
    selectedCategoryInChild: String,
    selectedCategoryInParent: (String)-> Unit
){
    TabRow(
        selectedTabIndex = if(selectedCategoryInChild == "Completed") 0 else 1,
        modifier = Modifier.fillMaxWidth()
            .height(48.dp),
        containerColor = white,
        contentColor = blue

    ){
        Tab(
            selected = if(selectedCategoryInChild == "Completed") true else false,
            onClick ={selectedCategoryInParent("Completed")} ,
            text = {Text(text = "Completed") }
        )
        Tab(
            selected = if(selectedCategoryInChild == "In Progress") true else false,
            onClick = {selectedCategoryInParent("In Progress")},
            text = {Text(text = "In Progress") }
        )

    }
}
var ProjectList = listOf(

    ProjectData(
        name = "CGPA Calculator",
        techStack = "Kotlin,Jetpack Compose",
        description = "An App to Calculate CGPA based on course credits and grade",
        completed = true
    ),
    ProjectData(
        name = "Wishlist App",
        techStack = "Kotlin,Firebase,Material Design",
        description = "An App to create and manage your wishlist with cloud synchronization",
        completed = true
),
    ProjectData(
        name = "Portfolio App",
        techStack = "Room Database,Jetpack Compose",
        description = "A portfolio management app to showcase your projects and skills",
        completed = false
    ),
    ProjectData(
        name = "E-Commerce App",
        techStack = "Kotlin,Retrofit, MVM",
        description = "An online shopping app with product catalog and cart functionality",
        completed = false
    )
)
@Composable
fun ProjectCard(
    project: ProjectData
) {
    val backgroundColor = if (project.completed == true) lightgreen2 else lightyellow
    val statusTextColor = if (project.completed == true) green else yellow
    var expanded by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = project.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = black
                    )
                    Text(
                        text = if (project.completed == true) "Completed" else "In Progress",
                        fontSize = 16.sp,
                        color = statusTextColor
                    )
                }
                IconButton(
                    onClick = { expanded = !expanded },

                    ) {
                    Icon(
                        imageVector = if (expanded == true)
                            Icons.Default.KeyboardArrowUp
                        else
                            Icons.Default.KeyboardArrowDown,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = black
                    )
                }
            }
            AnimatedVisibility(
                visible = if (expanded == true) true else false,
                enter = expandVertically(animationSpec = tween(200)),
                exit = shrinkVertically(animationSpec = tween(200))

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Divider(color = lightgray3)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row() {
                        Text(
                            text = "Tech Stack:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp),
                            color = black
                        )
                        Text(
                            text = project.techStack,
                            color = black
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row() {
                        Text(
                            "Description:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp),
                            color = black
                        )
                        Text(
                            text = project.description,
                            color = black

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Text(
                            "Description:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(100.dp),
                            color = black
                        )
                        Text(text = project.description, color = black)
                    }
                }
            }
        }
    }
}









        @Composable
        fun AddNewProjectDialog(
            onDismiss: () -> Unit,
            onAddProject: (String, String, String) -> Unit
        ) {
            var name by remember { mutableStateOf("") }
            var tech by remember { mutableStateOf("") }
            var desc by remember { mutableStateOf("") }
            var showAddDialog by remember { mutableStateOf(false) }

            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text("Add New Project") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Project Name") }
                        )
                        OutlinedTextField(
                            value = tech,
                            onValueChange = { tech = it },
                            label = { Text("Tech Stack") }
                        )
                        OutlinedTextField(
                            value = desc,
                            onValueChange = { desc = it },
                            label = { Text("Description") }
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (name.isNotBlank()) onAddProject(name, tech, desc)
                    }) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            )
        }













