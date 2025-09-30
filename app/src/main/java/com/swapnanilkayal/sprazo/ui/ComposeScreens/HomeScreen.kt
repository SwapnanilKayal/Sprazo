package com.swapnanilkayal.sprazo.ui.ComposeScreens

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Explicit
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Lessons
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Sections
import com.swapnanilkayal.sprazo.Data.Course.CourseRepository.Units
import com.swapnanilkayal.sprazo.Data.Course.LSection
import com.swapnanilkayal.sprazo.Data.Course.LUnit
import com.swapnanilkayal.sprazo.Data.Course.Lesson
import com.swapnanilkayal.sprazo.Data.Course.getLessonMaxScore
import com.swapnanilkayal.sprazo.Data.Course.getLessonProgress
import com.swapnanilkayal.sprazo.Data.Course.getLessonScore
import com.swapnanilkayal.sprazo.Data.Course.getUnitProgress
import com.swapnanilkayal.sprazo.Data.Inventory
import com.swapnanilkayal.sprazo.Data.Repositories
import com.swapnanilkayal.sprazo.Data.ScoreRepository
import com.swapnanilkayal.sprazo.Models.HomeViewModel
import com.swapnanilkayal.sprazo.Models.LockID
import com.swapnanilkayal.sprazo.R
import com.swapnanilkayal.sprazo.System.Prefs
import kotlinx.coroutines.delay
import kotlin.math.abs

@Composable
fun TopStatsBar(
    experience: Int,
    streakCount: Int,
    streakToday: Boolean,
    gemCount: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(
            icon = painterResource(R.drawable.content_de_flag),
            value = experience.toString(),
            label = "Language Score",
            isVector = false,
            iconTint = Color.Unspecified,
            modifier = Modifier.weight(1f)
        )

        StatItem(
            icon = Icons.Default.Whatshot,
            value = streakCount.toString(),
            label = "Current Streak",
            iconTint = if (streakToday) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            modifier = Modifier.weight(1f)
        )

        StatItem(
            icon = Icons.Default.Diamond,
            value = gemCount.toString(),
            label = "Gems",
            iconTint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun StatItem(
    icon: Any,
    value: String,
    label: String,
    iconTint: Color = LocalContentColor.current,
    isVector: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface), onClick = onClick) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isVector && icon is ImageVector) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconTint,
                    modifier = Modifier.size(28.dp)
                )
            } else if (!isVector && icon is Painter) {
                Image(
                    painter = icon,
                    contentDescription = label,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape),
                    contentScale = ContentScale.FillHeight
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun LessonItemCard(
    sectionId: String,
    unitId: String,
    lessonId: String,
    lessonName: String,
    lessonScore: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        onClick = onClick,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = lessonName,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.weight(1f))

                if (Repositories.scoreRepository.isLocked(sectionId,unitId,lessonId)){
                    Icon(Icons.Default.Lock, contentDescription = "Locked")
                } else {
                    Text(
                        text = lessonScore,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            LinearProgressIndicator(
            progress = { getLessonProgress(sectionId, unitId, lessonId).toFloat() },
            modifier = Modifier.fillMaxWidth(),
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )
        }
    }
}

@Composable
fun UnitCardWithLessons(
    unitId: String,
    unitName: String,
    lessonIDs: List<String>,
    sectionId: String,
    lessonsMap: Map<String, Lesson>,
    onLessonClick: (sectionId:String,unitId:String, lessonId: String,difficulty:Int) -> Unit
) {
    Log.d("composition", "unitId: $unitId, unitName: $unitName, lessonIDs: $lessonIDs, sectionId: $sectionId")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = unitName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${getUnitProgress(unitId,sectionId).toInt()}%",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                lessonIDs.forEach { lessonId ->

                    LessonItemCard(
                        sectionId = sectionId,
                        unitId = unitId,
                        lessonId = lessonId,
                        lessonName = lessonsMap[lessonId]?.title ?: lessonId,
                        lessonScore = "${getLessonScore(lessonId, unitId, sectionId)} / ${getLessonMaxScore(lessonId)}",
                        onClick = { onLessonClick(sectionId,unitId,lessonId,Prefs.Reactive.difficulty) }
                    )
                }
            }
        }
    }
}



@Composable
fun SwipeableSection(
    currentSection: Int,
    sectionCount: Int,
    onSectionChange: (Int) -> Unit,
    content: @Composable (Int) -> Unit
) {
    var section by remember { mutableStateOf(currentSection) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {},
                    onDragCancel = {},
                    onHorizontalDrag = { change, dragAmount ->
                        val threshold = 100f
                        if (abs(dragAmount) > threshold) {
                            if (dragAmount < 0 && section < sectionCount - 1) {

                                section += 1
                                onSectionChange(section)
                            } else if (dragAmount > 0 && section > 0) {

                                section -= 1
                                onSectionChange(section)
                            }
                        }
                    }
                )
            }
    ) {
        content(section)
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SectionAnimatedContent(
    currentSection: Int,
    unitsMap: Map<String, LUnit>,
    lessonsMap: Map<String, Lesson>,
    modifier: Modifier = Modifier,
    content: @Composable (targetSection: Int, unitsMap: Map<String, LUnit>, lessonsMap: Map<String, Lesson>) -> Unit // Updated lambda
) {
    val previousSection = remember { mutableStateOf(currentSection) }

    AnimatedContent(
        targetState = currentSection,
        transitionSpec = {
            val direction = if (targetState > previousSection.value) 1 else -1

            (slideInHorizontally(
                animationSpec = tween(durationMillis = 350)
            ) { width -> width * direction } + fadeIn() + scaleIn(
                initialScale = 0.95f,
                animationSpec = tween(350)
            )) with
                    (slideOutHorizontally(
                        animationSpec = tween(350)
                    ) { width -> -width * direction } + fadeOut() + scaleOut(
                        targetScale = 0.95f,
                        animationSpec = tween(350)
                    ))
        },
        modifier = modifier,
        contentKey = { it } // ensure animation triggers on value change
    ) { target ->
        content(target, unitsMap, lessonsMap) // Passed maps to content
        previousSection.value = target
    }
}

@Composable
fun lessonView(
    model: HomeViewModel,
    target: Int,
    unitsMap: Map<String, LUnit>,
    lessonsMap: Map<String, Lesson>,
    navigateToLesson: (String) -> Unit
){
    Log.d("composition", "lesson view target: $target")
    Column {
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                //contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                val section = Sections[target]
                items(section.Units, key = { it }) { unitId ->
                    Log.d("composition", "unitId Caller: \"$unitId\"")
                    val unit = unitsMap[unitId] // Used unitsMap
                    if (unit == null) {
                        Text("unit not found: $unitId")
                    } else{
                        UnitCardWithLessons(
                            unitId = unit.id,
                            unitName = unit.name,
                            lessonIDs = unit.lessonIDs,
                            sectionId = section.id,
                            lessonsMap = lessonsMap,
                            onLessonClick = { sectionId, unitId, lessonId, difficulty ->
                                model.openLesson(sectionId, unitId, lessonId, difficulty, navigateToLesson)
                            }
                        )
                    }
                }
            }


        }
    }
}



@Composable
fun SectionView(model: HomeViewModel){
    Card (
        modifier = Modifier
            .padding(10.dp, 0.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {model.isSectionChooserOpen = true},
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHighest),
    ){
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp,0.dp)) {
            AnimatedContent(Prefs.Reactive.loadedSection, modifier = Modifier
                .padding(10.dp)
                .weight(1f)) {
                Text(Sections[it].name, modifier = Modifier.weight(1f), textAlign = TextAlign.Start)
            }
            Icon(Icons.Default.Bookmarks,"")
        }
    }
}

@Composable
fun SectionChooserDialog(
    isOpen: Boolean,
    onDismiss: () -> Unit,
    sections: List<LSection>,
    selected:Int
) {
    Log.d("SectionChooserDialog", "isOpen: $isOpen, selected: $selected")
    if (isOpen) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainer)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Choose Section",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    LazyColumn {
                        items(sections.size) { section ->
                            SectionChooserItem(
                                sectionName = Sections[section].name,
                                onClick = {
                                    Prefs.Reactive.loadedSection = section
                                    onDismiss()
                                },
                                selected = section == selected
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionChooserItem(
    sectionName: String,
    onClick: () -> Unit,
    selected: Boolean
) {
    Log.d("SectionChooserItem", "selected: $selected @ $sectionName")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (selected) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                contentDescription = null,
                tint = if (!selected) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.surfaceVariant
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = sectionName,
                style = MaterialTheme.typography.bodyLarge,
                color = if (!selected) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LockedPrompt(
    isVisible: Boolean,
    lockedContentIdentifier: LockID?,
    onDismiss: () -> Unit,
    onContinue: (lockedData: LockID) -> Unit
) {
    var contentVisible by remember { mutableStateOf(false) }
    val lessonName = if (isVisible && lockedContentIdentifier != null) Lessons.find { it.id == lockedContentIdentifier.lessonId }?.title else "Unknown Lesson"
    val unitName = if (isVisible && lockedContentIdentifier != null) Units.find { it.id == lockedContentIdentifier.unitId }?.name else "Unknown Unit"
    val sectionName = if (isVisible && lockedContentIdentifier != null) Sections.find { it.id == lockedContentIdentifier.sectionId }?.name else "Unknown Section"


    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(50)
            contentVisible = true
        } else {
            contentVisible = false
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.6f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                ),
            contentAlignment = Alignment.Center
        ) {
            val gradientBrush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.95f),
                    MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.95f)
                )
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clickable(enabled = false) {},
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(
                    modifier = Modifier
                        .background(gradientBrush)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(400, 100)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ReportProblem,
                            contentDescription = "Content Locked",
                            modifier = Modifier.size(60.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(400, 200)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
                    ) {
                        Text(
                            text = "Content Locked",
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(400, 300)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
                    ) {
                        if (lockedContentIdentifier != null) {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "The following lesson is locked:",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(bottom = 4.dp).align(Alignment.Start).padding(25.dp,0.dp)
                                )


                                @Composable fun DetailItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = label,
                                            tint = MaterialTheme.colorScheme.secondary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(Modifier.width(8.dp))
                                        Text(
                                            text = "$label: ",
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Text(
                                            text = value,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }

                                Column(modifier = Modifier.fillMaxWidth().padding(25.dp,0.dp)) {
                                    DetailItem(icon = Icons.Filled.School, label = "Lesson", value = lessonName?:"")
                                    DetailItem(icon = Icons.Filled.Folder, label = "Unit", value = unitName?:"")
                                    DetailItem(icon = Icons.Filled.Category, label = "Section", value = sectionName?:"")

                                }
                                Text(
                                    text = "It is locked until you complete the previous lesson.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally)
                                )
                            }
                        } else {
                            Text(
                                text = "This content is currently locked. You might need to complete previous lessons or meet certain criteria.",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(400, 400)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
                    ) {
                        val interactionSource = remember { MutableInteractionSource() }
                        val isPressed by interactionSource.collectIsPressedAsState()
                        val scale = if (isPressed) 0.95f else 1f

                        Button(
                            onClick = {
                                lockedContentIdentifier?.let { onContinue(it) }
                            },
                            enabled = lockedContentIdentifier != null,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(52.dp)
                                .scale(scale),
                            shape = RoundedCornerShape(12.dp),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp, pressedElevation = 1.dp)
                        ) {
                            Icon(
                                Icons.Default.LockOpen,
                                contentDescription = "Unlock",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                            Text("Continue Anyway", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        }
                    }

                    AnimatedVisibility(
                        visible = contentVisible,
                        enter = fadeIn(tween(400, 450)) + slideInVertically(spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)) { it / 2 }
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text("Cancel", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(model: HomeViewModel,navigateToLesson:(String) -> Unit, onNavigateToSettings: () -> Unit){
    // Hoisted maps
    val unitsMap = remember { Units.associateBy { it.id } }
    val lessonsMap = remember { Lessons.associateBy { it.id } }
    SectionChooserDialog(model.isSectionChooserOpen, { model.isSectionChooserOpen = false }, Sections, Prefs.Reactive.loadedSection)
    Surface(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.systemBarsPadding()) {
            Column(Modifier
                .fillMaxSize()
                .padding(5.dp, 10.dp)
                .weight(1f)) {
                TopStatsBar(
                    Repositories.inventoryRepository.getXP(),
                    Repositories.streakRepository.streakCount,
                    Repositories.streakRepository.streakToday,
                    Repositories.inventoryRepository.getGemCount()
                )
                SectionView(model)
                SectionAnimatedContent(
                    currentSection = Prefs.Reactive.loadedSection,
                    unitsMap = unitsMap,
                    lessonsMap = lessonsMap,
                )  { targetSection, passedUnitsMap, passedLessonsMap ->
                    lessonView(
                        model = model,
                        target = targetSection,
                        unitsMap = passedUnitsMap,
                        lessonsMap = passedLessonsMap,
                        navigateToLesson = navigateToLesson
                    )
                }

            }

           //navigationBar(model)

        }
        LockedPrompt(model.lockedSessionsData!=null,model.lockedSessionsData,{model.lockedSessionsData = null},{if (it!=null) model.openLesson(it,navigateToLesson)})

    }
}
