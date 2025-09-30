package com.swapnanilkayal.sprazo.ui.ComposeScreens

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.swapnanilkayal.sprazo.Data.Difficulty
import com.swapnanilkayal.sprazo.Data.StaticData
import com.swapnanilkayal.sprazo.Data.difficulties
import com.swapnanilkayal.sprazo.Models.OnboardingViewModel
import com.swapnanilkayal.sprazo.R
import com.swapnanilkayal.sprazo.System.Prefs
import com.swapnanilkayal.sprazo.System.getSkillLevelName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun WelcomePage(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.iconfg),
            contentDescription = "Sprazo",
            modifier = Modifier.size(256.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Welcome to Sprazo!",
            style = MaterialTheme.typography.headlineLarge.copy(shadow = Shadow(
                color = Color.Black.copy(alpha = 0.6f),
                offset = Offset(0f, 0f),
                blurRadius = 10f
            )),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SkillLevelBar(
    text: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if (selected) 2.dp else 1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (selected) 6.dp else 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = if (selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurface
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            )
        }
    }
}



@Composable
fun SkillPage(model: OnboardingViewModel) {
    val skills = listOf("Beginner", "Intermediate", "Advanced")

    val descriptions = listOf(
        "Perfect for newcomers starting their learning journey. Covers the basics with simple explanations and guided practice.",
        "Designed for learners with some foundation. Focuses on improving fluency, grammar, and comprehension with practical exercises.",
        "Challenging content for experienced learners. Helps refine language skills, master nuances, and build confidence in real-world use."
    )

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp, 10.dp)) {

        Text(
            text = "Choose Your German Proficiency Level",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.weight(1f))


        LazyColumn {
            items(skills.size) { index ->
                SkillLevelBar(skills[index], descriptions[index], index == Prefs.Reactive.skillLevel, onClick = {if (index > 0)
                    Toast.makeText(context, "Courses not implemented", Toast.LENGTH_LONG).show() else model.selectSkillLevel(index)})
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ModernTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Your Name",
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = modifier,
        shape = RoundedCornerShape(50),
        textStyle = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
fun Introduction(
    model: OnboardingViewModel,
    onContinue: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Introduce Yourself",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "To personalize your experience, please tell us your name.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))


        ModernTextField(
            value = Prefs.Reactive.userName,
            onValueChange = { model.updateUname(it) },
            label = "Your Name",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun LicensePage(){
    val scrollState = rememberScrollState()
    Text(
        text = StaticData.License,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    )
}

@Composable
fun CourseIntroduction(
    model: OnboardingViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Hi, ${Prefs.Reactive.userName} 👋",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Course intro line
        Text(
            text = "Ready to begin your ${getSkillLevelName(Prefs.Reactive.skillLevel)} German course?",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Course details
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "This course is designed to help you strengthen your language skills step by step.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            val items = listOf(
                "Interactive quizzes to expand your vocabulary",
                "Practical exercises to improve grammar",
                "Engaging lessons to boost confidence in real conversations"
            )

            items.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Stay consistent, and you’ll notice your progress faster than you expect!",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun DifficultyPage(model: OnboardingViewModel, exp: Int = 0) {
    // Only show unlocked difficulties
    val difficulties = difficulties.filter { it.getIsUnlocked(exp) }
    val currentDifficulty = Prefs.Reactive.difficulty

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Choose Lesson Difficulty",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 10.dp)
        )
        Spacer(Modifier.weight(1f))

        difficulties.forEachIndexed { index, difficulty ->
            DifficultyCard(
                difficulty = difficulty,
                isSelected = index == currentDifficulty,
                onSelect = {
                    model.setDifficulty(index)
                }
            )
        }
        Spacer(Modifier.weight(1f))

    }
}

@Composable
fun DifficultyCard(
    difficulty: Difficulty,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val backgroundColor = if (isSelected) difficulty.color else MaterialTheme.colorScheme.surface
    val contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 12.dp else 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ), onClick = { onSelect() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = difficulty.icon,
                contentDescription = difficulty.name,
                tint = if (isSelected) contentColor else difficulty.color,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = difficulty.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = contentColor
                )
                Text(
                    text = difficulty.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = contentColor.copy(alpha = 0.8f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = if ( isSelected) contentColor else backgroundColor
            )
        }
    }
}


@Composable
fun OnBoardingScreen(model: OnboardingViewModel, onFinish: () -> Unit) {

    Surface {
        Column (modifier = Modifier.systemBarsPadding()){
            Card(modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(10.dp),
                shape = RoundedCornerShape(40.dp,40.dp,16.dp,16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))) {
                AnimatedContent(model.page) {
                    when(it){
                        0 -> WelcomePage()
                        1 -> LicensePage()
                        2 -> SkillPage(model)
                        3 -> DifficultyPage(model)
                        4 -> Introduction(model)
                        5 -> CourseIntroduction(model)
                    }
                }
            }
            Row(modifier = Modifier.padding(10.dp,0.dp,10.dp,10.dp)) {
                FilledIconButton(onClick = {
                    model.prevPage()
                },
                    modifier = Modifier
                        .size(80.dp),
                    enabled = model.isPrevEnabled(),
                    shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 40.dp), // Or CircleShape for round buttons
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                        disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "BackArrow",
                        modifier = Modifier.size(48.dp)
                    )
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .size(80.dp)
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = model.pageNames[model.page],
                            textAlign = TextAlign.Center
                        )
                    }
                }
                FilledIconButton(onClick = {
                    model.nextPage(onFinish)
                },
                    modifier = Modifier
                        .size(80.dp),
                    enabled = model.isNextEnabled(),
                    shape = RoundedCornerShape(16.dp,16.dp,40.dp,16.dp), // Or CircleShape for round buttons
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                        disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "BackArrow",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}
