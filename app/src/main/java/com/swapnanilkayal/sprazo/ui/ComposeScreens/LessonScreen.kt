package com.swapnanilkayal.sprazo.ui.ComposeScreens

import android.util.Log
import android.widget.TabWidget
import androidx.activity.compose.BackHandler 
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.PlayLesson
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.PublishedWithChanges
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Diamond
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.ZoomIn
import androidx.compose.ui.input.key.Key.Companion.ZoomOut
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.swapnanilkayal.sprazo.Data.Course.Question
import com.swapnanilkayal.sprazo.Data.difficulties
import com.swapnanilkayal.sprazo.Models.LessonViewModel
import com.swapnanilkayal.sprazo.Models.Phases
import com.swapnanilkayal.sprazo.Models.QuestionState
import com.swapnanilkayal.sprazo.Models.Result
import kotlinx.coroutines.delay









@Composable
fun LessonStartScreen(model: LessonViewModel) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100) 
        visible = true
    }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f), MaterialTheme.colorScheme.surface.copy(alpha = 0.3f))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 200)) +
                        slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(durationMillis = 500, delayMillis = 200))
            ) {
                Icon(
                    imageVector = Icons.Filled.Book,
                    contentDescription = "Quiz Icon",
                    modifier = Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 300)) +
                        slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(durationMillis = 500, delayMillis = 300))
            ) {
                Text(
                    text = model.lesson?.title?:"", 
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 400)) +
                        slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(durationMillis = 500, delayMillis = 400))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (model.PATH_OK) "${model.section?.name} - ${model.unit?.name}" else "Invalid Inheritance", 
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }


            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 500)) +
                        slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(durationMillis = 500, delayMillis = 500))
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InfoChip("Questions: ${model.questionCount}") 
                    InfoChip("Difficulty: ${difficulties[model.difficulty].name}") 
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 600)) +
                        slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(durationMillis = 500, delayMillis = 600))
            ) {
                Button(
                    onClick = { model.phase = Phases.LESSON_ONGOING }, 
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    enabled = model.PATH_OK
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayLesson,
                        contentDescription = "Start Lesson Icon",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Start Lesson", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun InfoChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun GradientLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
    ),
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    cornerRadius: Dp = 12.dp, 
    animationDurationMillis: Int = 2000 
) {
    val infiniteTransition = rememberInfiniteTransition(label = "gradient_flow")
    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f, 
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDurationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart 
        ), label = "gradient_offset"
    )

    val cornerRadiusPx = with(LocalDensity.current) { cornerRadius.toPx() }

    Canvas(modifier = modifier.clip(RoundedCornerShape(cornerRadius))) {
        val barWidth = size.width
        val barHeight = size.height

        
        drawRoundRect(
            color = trackColor,
            size = size,
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadiusPx)
        )

        
        val progressWidth = (barWidth * progress.coerceIn(0f, 1f))
        if (progressWidth > 0f) {
            val brush = Brush.horizontalGradient(
                colors = gradientColors,
                startX = (gradientOffset * barWidth * 2) - barWidth, 
                endX = (gradientOffset * barWidth * 2),      
            )
            drawRoundRect(
                brush = brush,
                size = Size(progressWidth, barHeight),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadiusPx)
            )
        }
    }
}


@Composable
fun TopBar(
    energyProgress: Float, 
    lives: Int
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp), 
        shape = RoundedCornerShape(16.dp), 
        tonalElevation = 4.dp, 
        shadowElevation = 2.dp  
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp), 
            verticalAlignment = Alignment.CenterVertically
        ) {
            
            val animatedEnergyProgress by animateFloatAsState(
                targetValue = energyProgress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                label = "energy_progress_animation"
            )

            GradientLinearProgressIndicator(
                progress = animatedEnergyProgress,
                modifier = Modifier
                    .weight(1f)
                    .height(16.dp) 
            )

            Spacer(modifier = Modifier.width(16.dp))

            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Lives",
                    tint = MaterialTheme.colorScheme.error 
                )
                Spacer(modifier = Modifier.width(6.dp)) 
                Text(
                    text = if (lives < 0) "∞" else "$lives", 
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface 
                    ),
                )
            }
        }
    }
}



@Composable
fun LessonScreen(model: LessonViewModel){
    
    val currentEnergy by remember { mutableStateOf(2.5f) }
    val currentLives by remember { mutableStateOf(15) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            energyProgress = currentEnergy, 
            lives = currentLives          
        )
        
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Quiz Content Here for ${model.lesson?.title}")
        }
    }

}

val PassColor = Color(0xFF66BB6A) 
val FailColor = Color(0xFFEF5350) 
val IncompleteColor = Color(0xFF42A5F5) 

/**
 * Main Composable for the ongoing lesson phase.
 * It handles question rendering, user input, answer checking, and navigation.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LessonOngoingScreen(
    model: LessonViewModel,
) {

    
    if (!model.PATH_OK) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: Lesson data not found.")
        }
        return
    }


    Scaffold(
        topBar = {TopBar(model.lifeEssence, model.livesLeft) },
        bottomBar = {
            if (model.phase == Phases.LESSON_ONGOING || model.phase == Phases.LESSON_MISTAKE_REVIEW) {
                BottomFeedbackBar(currentQuestion = model.loadedQuestions[model.questionNumber], model = model)
            }
        },
        modifier = Modifier.background(Color.Transparent),
        containerColor = Color.Transparent
    ) { paddingValues ->
        AnimatedContent(
            targetState = model.questionNumber,
            transitionSpec = {
                slideInHorizontally{ fullWidth -> fullWidth } togetherWith
                        slideOutHorizontally { fullWidth -> -fullWidth}
            }, label = "question_transition",
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { targetQuestionIndex ->
            
            val questionState = model.loadedQuestions.getOrNull(targetQuestionIndex)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    if (questionState != null) {
                        QuestionContent(questionState)
                        Spacer(Modifier.height(32.dp))
                        QuestionInputArea(questionState)
                    } else {
                        
                        Text("Loading question...", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}



@Composable
fun QuestionContent(questionState: QuestionState) {
    
    val questionText = questionState.qTxt
    if (questionText != null) {
        Text(
            text = questionText.replace("__", "______"), 
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

    
    
    
    
    
    

    
    
    if (questionState.qAud != null) {
        Button(onClick = { /* TODO: Implement audio playback */ }) {
            Text("Play Audio")
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun QuestionInputArea(questionState: QuestionState) {
    when (questionState.inputType) {
        QuestionState.Companion.InputTypes.OPTIONS -> OptionsInput(questionState)
        QuestionState.Companion.InputTypes.WORD_CHOOSE -> WordChooseInput(questionState)
        QuestionState.Companion.InputTypes.TEXT -> TextInput(questionState)
        QuestionState.Companion.InputTypes.SPEAK -> SpeakInput(questionState)
        else -> Text("Unsupported input type.")
    }
}

@Composable
fun OptionsInput(questionState: QuestionState) {
    val options = questionState.options ?: return
    val selectedOption by remember { derivedStateOf { questionState.selectedOption } }
    val isAnswerChecked = questionState.ResultState != com.swapnanilkayal.sprazo.Models.Result.INCOMPLETE

    Column(Modifier.fillMaxWidth()) {
        options.forEach { option ->
            val isSelected = option == selectedOption
            val (backgroundColor, contentColor) = when {
                !isAnswerChecked -> if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) to MaterialTheme.colorScheme.onPrimary else Color.White to Color.Black
                option.isCorrect -> PassColor to Color.White
                isSelected && !option.isCorrect -> FailColor to Color.White
                else -> Color.White to Color.Black
            }

            Card(
                onClick = { questionState.takeInput(option) },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = backgroundColor,
                    contentColor = contentColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = option.string,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordChooseInput(questionState: QuestionState) {
    val chosenWords = questionState.wordChosen.toList()
    val availableWords = questionState.wordPool.toList()
    val isAnswerChecked = questionState.ResultState != com.swapnanilkayal.sprazo.Models.Result.INCOMPLETE

    Column(Modifier.fillMaxWidth()) {
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            FlowRow(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (chosenWords.isEmpty()) {
                    Text("Choose words here...", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
                } else {
                    chosenWords.forEach { word ->
                        AssistChip(
                            onClick = { if (!isAnswerChecked) questionState.takeInput(word) },
                            label = { Text(word) },
                            enabled = !isAnswerChecked
                        )
                    }
                }
            }
        }

        
        Text("Word Pool:", style = MaterialTheme.typography.titleSmall)
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            availableWords.forEach { word ->
                ElevatedAssistChip(
                    onClick = { if (!isAnswerChecked) questionState.takeInput(word) },
                    label = { Text(word) },
                    enabled = !isAnswerChecked
                )
            }
        }
    }
}

@Composable
fun TextInput(questionState: QuestionState) {
    val text by remember { derivedStateOf { questionState.textInput } }
    val isAnswerChecked = questionState.ResultState != com.swapnanilkayal.sprazo.Models.Result.INCOMPLETE

    OutlinedTextField(
        value = text,
        onValueChange = {
            if (!isAnswerChecked) questionState.takeInput(it)
        },
        label = { Text("Your Answer") },
        singleLine = false,
        maxLines = 5,
        readOnly = isAnswerChecked,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SpeakInput(questionState: QuestionState) {
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { /* TODO: Implement STT logic and set textInput for checkAnswer */ },
            enabled = questionState.ResultState == com.swapnanilkayal.sprazo.Models.Result.INCOMPLETE
        ) {
            Text("Tap to Speak")
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Your recording will be displayed here.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BottomFeedbackBar(currentQuestion: QuestionState?, model: LessonViewModel) {
    val question = currentQuestion ?: return

    val isAnswerProvided = remember(question.selectedOption, question.wordChosen, question.textInput) {
        when (question.inputType) {
            QuestionState.Companion.InputTypes.OPTIONS -> question.selectedOption != null
            QuestionState.Companion.InputTypes.WORD_CHOOSE -> question.wordChosen.isNotEmpty()
            QuestionState.Companion.InputTypes.TEXT, QuestionState.Companion.InputTypes.SPEAK -> question.textInput.isNotBlank()
            else -> false
        }
    }

    val showContinueButton = question.ResultState == com.swapnanilkayal.sprazo.Models.Result.INCOMPLETE && isAnswerProvided
    val showNextButton = question.ResultState != com.swapnanilkayal.sprazo.Models.Result.INCOMPLETE
    val feedbackColor = when (question.ResultState) {
        com.swapnanilkayal.sprazo.Models.Result.PASS -> PassColor
        com.swapnanilkayal.sprazo.Models.Result.FAIL -> FailColor
        else -> IncompleteColor 
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(top = 8.dp)
    ) {
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically { it },
            exit = slideOutVertically { it }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (showNextButton) feedbackColor else Color.Transparent,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

                
                if (showNextButton) {
                    Text(
                        text = if (question.getPass()) "Great job!" else "Not quite...",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    
                    if (question.summary.isNotEmpty()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = question.summary,
                            color = Color.White.copy(alpha = 0.8f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                }

                
                Button(
                    onClick = {
                        if (showContinueButton) {
                            question.checkAnswer() 
                            
                        } else if (showNextButton) {
                            model.next() 
                        }
                    },
                    enabled = showContinueButton || showNextButton,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (showNextButton) Color.White else MaterialTheme.colorScheme.primary,
                        contentColor = if (showNextButton) feedbackColor else MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (showNextButton) "Continue" else "Check answer")
                }
                Spacer(Modifier.height(16.dp)) 
            }
        }
    }
}



@Composable
fun OptionChooser(question: QuestionState){
    Column {
        question.options?.forEach {
            Card (modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(80.dp), onClick = {question.takeInput(it)}, border = BorderStroke(2.dp,if (question.selectedOption == it) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.surfaceVariant), colors = CardDefaults.outlinedCardColors()) {
                Row(Modifier
                    .padding(20.dp)
                    .fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    AnimatedContent(question.selectedOption == it) {
                        if (it){
                            Icon(Icons.Default.CheckCircle,"Chosen")
                        } else {
                            Icon(Icons.Default.RadioButtonUnchecked,"NotChosen")
                        }
                    }
                    Spacer(Modifier.width(16.dp))
                    Text(it.string, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun LessonOngoingBody(model: LessonViewModel, questionNumber:Int){
    val question = model.loadedQuestions[questionNumber]
    Column (Modifier.padding(20.dp)){
        if (question.qTxt != null){
            Text(question.qTxt!!, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
        if (question.qImg != null){
            Image(painter = painterResource(id = question.qImg!!), contentDescription = null, modifier = Modifier.height(200.dp))
        }
        Spacer(Modifier.weight(1f))
        when (question.inputType) {
            QuestionState.Companion.InputTypes.OPTIONS -> OptionChooser(question)
            QuestionState.Companion.InputTypes.WORD_CHOOSE -> WordChooseInput(question)
            QuestionState.Companion.InputTypes.TEXT -> TextInput(question)
        }
        Spacer(Modifier.weight(1.75f))

    }
    Column() {
        Spacer(Modifier.weight(1f))
        AnimatedVisibility(question.canCheck() && question.ResultState == Result.INCOMPLETE, enter = fadeIn(tween(300)) + slideInVertically(initialOffsetY = { -it / 2 }), exit = fadeOut(tween(300)) + slideOutVertically(targetOffsetY = { -it / 2 })) {
            Button(onClick = { question.checkAnswer() }, Modifier
                .fillMaxWidth()
                .padding(20.dp, 40.dp)) {
                Text("Check Answer", style = MaterialTheme.typography.titleMedium)
            }
        }
        AnimatedVisibility(question.ResultState!= Result.INCOMPLETE) {
            Card {
                Column(Modifier.background(brush = Brush.verticalGradient(listOf(if (question.ResultState == Result.PASS) PassColor else FailColor,
                    MaterialTheme.colorScheme.surface)))) {
                    Text(if (question.ResultState == Result.PASS) "Correct" else "Incorrect", style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(20.dp,10.dp,10.dp,5.dp))
                    Text(question.summary.trim(), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(20.dp,5.dp))
                    Spacer(Modifier.height(5.dp))
                    if (model.phase == Phases.LESSON_MISTAKE_REVIEW){
                        Button(onClick = { model.next(true) }, Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 5.dp, 20.dp, 10.dp)) {
                            Text("Skip Review", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                    Button(onClick = { model.next() }, Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 5.dp, 20.dp, 40.dp)) {
                        Text("Next Question", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

@Composable
fun LessonOngoingScreen2(model: LessonViewModel){
    Column {
        Box(Modifier.systemBarsPadding()){
            TopBar(model.lifeEssence, model.livesLeft)
        }
        Box(){
            AnimatedContent(model.questionNumber) {
                LessonOngoingBody(model,it)
            }
        }
    }
}


@Composable
fun ReviewAcknowledgement(model: LessonViewModel, onStartReview: () -> Unit, onSkipReview:() -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(100) 
        visible = true
    }

    val lessonTitle = model.lesson?.title ?: "the lesson"
    val mistakeCount = model.mistakenQuestions.size 

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 200)) + slideInVertically(tween(500, 200)) { it / 2 }
            ) {
                Icon(
                    imageVector = Icons.Filled.Psychology, 
                    contentDescription = "Review Mistakes Icon",
                    modifier = Modifier.size(72.dp),
                    tint = MaterialTheme.colorScheme.secondary 
                )
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 300)) + slideInVertically(tween(500, 300)) { it / 2 }
            ) {
                Text(
                    text = "Let's Review!",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 400)) + slideInVertically(tween(500, 400)) { it / 2 }
            ) {
                Text(
                    text = if (mistakeCount > 0) {
                        "You made $mistakeCount mistake${if (mistakeCount == 1) "" else "s"} in '$lessonTitle'.\nTime to master them!"
                    } else {
                        "Great job on '$lessonTitle'! No mistakes to review."
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 500)) + slideInVertically(tween(500, 500)) { it / 2 }
            ) {
                Column {
                    Button(
                        onClick = onStartReview,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(52.dp).padding(0.dp,5.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 5.dp),
                        enabled = mistakeCount > 0
                    ) {
                        Icon(
                            Icons.Filled.PublishedWithChanges,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            if (mistakeCount > 0) "Start Review" else "Done",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Button(
                        onClick = onSkipReview,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(52.dp).padding(0.dp,5.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 5.dp),
                        enabled = mistakeCount > 0
                    ) {
                        Icon(
                            Icons.Filled.PublishedWithChanges,
                            contentDescription = null,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            if (mistakeCount > 0) "Skip Review" else "Done",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LessonEndScreen(
    xpGained: Int,
    gemsGained: Int,
    finalScore: Int,
    lessonTitle: String,
    onExitLesson: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(100) 
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()) 
                .padding(bottom = 16.dp)
        ) {
            
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 200)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
            ) {
                Icon(
                    imageVector = Icons.Filled.EmojiEvents, 
                    contentDescription = "Lesson Complete Icon",
                    modifier = Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 300)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
            ) {
                Text(
                    text = "Lesson Complete!",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 350)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
            ) {
                Text(
                    text = "You've successfully completed '$lessonTitle'.",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }


            Spacer(modifier = Modifier.height(12.dp))

            
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 400)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        StatItem(icon = Icons.Filled.Star, label = "XP Gained", value = "+$xpGained XP", iconColor = MaterialTheme.colorScheme.tertiary)
                        StatItem(icon = Icons.Outlined.Diamond, label = "Gems Earned", value = "+$gemsGained", iconColor = MaterialTheme.colorScheme.secondary) 
                        StatItem(icon = Icons.Filled.CheckCircle, label = "Final Score", value = "$finalScore", iconColor = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp)) 

            
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500, 500)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
            ) {
                Button(
                    onClick = onExitLesson,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 5.dp)
                ) {
                    Text(
                        "Continue",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun StatItem(icon: ImageVector, label: String, value: String, iconColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            shape = CircleShape,
            color = iconColor.copy(alpha = 0.15f),
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}



@Composable
fun LessonScreen(model: LessonViewModel, onExit: () -> Unit) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f), MaterialTheme.colorScheme.surface.copy(alpha = 0.3f))
    )
    Surface(modifier = Modifier.fillMaxSize()){
        Box(Modifier
            .fillMaxSize()
            .background(gradientBrush)){ 
            Surface(modifier = Modifier
                .fillMaxSize() ,color = Color.Transparent) {
                AnimatedContent(model.phase) {
                    when (it) { 
                        0 -> LessonStartScreen(model = model)
                        1 -> LessonOngoingScreen2(model)
                        2 -> ReviewAcknowledgement(model = model, onStartReview = { model.next()}, onSkipReview = {model.next(true)})
                        3 -> LessonOngoingScreen2(model)
                        4 -> LessonEndScreen(model.xpEarned,model.earnedGems,model.netScore,model.lesson?.title ?: "the lesson", onExit)
                        else -> {
                            
                            Text("Unknown quiz phase: ${model.phase}", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}
