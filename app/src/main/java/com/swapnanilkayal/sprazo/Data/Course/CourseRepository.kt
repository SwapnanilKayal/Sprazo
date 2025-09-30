package com.swapnanilkayal.sprazo.Data.Course

import androidx.compose.runtime.mutableStateListOf

object CourseRepository {
    val Lessons = mutableStateListOf(
        Lesson("DE/1/Lesson/01", "Greetings 1", mutableListOf(
            "DE/1/MCQ/01", "DE/1/MCQ/02", "DE/1/MCQ/14", "DE/1/FTB/01"
        ), "Greetings"),
        Lesson("DE/1/Lesson/02", "Politeness 1", mutableListOf(
            "DE/1/MCQ/03", "DE/1/FTB/03", "DE/1/FTB/04", "DE/1/FTB/05"
        ), "Politeness"),
        Lesson("DE/1/Lesson/03", "Numbers 1–5", mutableListOf(
            "DE/1/MCQ/04", "DE/1/MCQ/05", "DE/1/MCQ/06", "DE/1/MCQ/21", "DE/1/MCQ/22"
        ), "Numbers"),
        Lesson("DE/1/Lesson/04", "Colors 1", mutableListOf(
            "DE/1/MCQ/07", "DE/1/MCQ/08", "DE/1/MCQ/09", "DE/1/MCQ/10"
        ), "Colors"),
        Lesson("DE/1/Lesson/05", "Basics 1", mutableListOf(
            "DE/1/MCQ/11", "DE/1/MCQ/12", "DE/1/MCQ/13", "DE/1/FTB/10"
        ), "Basics"),
        Lesson("DE/1/Lesson/06", "Days 1", mutableListOf(
            "DE/1/MCQ/16", "DE/1/MCQ/17", "DE/1/MCQ/18", "DE/1/MCQ/19", "DE/1/MCQ/20"
        ), "Days"),
        Lesson("DE/1/Lesson/07", "Numbers 6–10", mutableListOf(
            "DE/1/MCQ/23", "DE/1/MCQ/24", "DE/1/MCQ/25", "DE/1/MCQ/26", "DE/1/MCQ/27"
        ), "Numbers"),
        Lesson("DE/1/Lesson/08", "Months 1", mutableListOf(
            "DE/1/MCQ/35", "DE/1/MCQ/36", "DE/1/MCQ/37", "DE/1/MCQ/38"
        ), "Months"),
        Lesson("DE/1/Lesson/09", "Months 2", mutableListOf(
            "DE/1/MCQ/39", "DE/1/MCQ/40", "DE/1/MCQ/41", "DE/1/MCQ/42"
        ), "Months"),
        Lesson("DE/1/Lesson/10", "Months 3", mutableListOf(
            "DE/1/MCQ/43", "DE/1/MCQ/44", "DE/1/MCQ/45", "DE/1/MCQ/46"
        ), "Months"),
        Lesson("DE/1/Lesson/11", "Animals 1", mutableListOf(
            "DE/1/MCQ/47", "DE/1/MCQ/48", "DE/1/MCQ/49", "DE/1/MCQ/50"
        ), "Animals"),
        Lesson("DE/1/Lesson/12", "Greetings 2", mutableListOf(
            "DE/1/FTB/02", "DE/1/FTB/06", "DE/1/FTB/07"
        ), "Greetings"),
        Lesson("DE/1/Lesson/13", "Greetings 3", mutableListOf(
            "DE/1/FTB/08", "DE/1/FTB/09"
        ), "Greetings"),
        Lesson("DE/1/Lesson/14", "Colors 2", mutableListOf(
            "DE/1/MCQ/07", "DE/1/MCQ/08", "DE/1/MCQ/09", "DE/1/MCQ/10"
        ), "Colors"),
        Lesson("DE/1/Lesson/15", "Numbers 11–15", mutableListOf(
            "DE/1/MCQ/21", "DE/1/MCQ/22", "DE/1/MCQ/23", "DE/1/MCQ/24", "DE/1/MCQ/25"
        ), "Numbers"),
        Lesson("DE/1/Lesson/16", "Politeness 2", mutableListOf(
            "DE/1/FTB/03", "DE/1/FTB/04", "DE/1/FTB/05"
        ), "Politeness"),
        Lesson("DE/1/Lesson/17", "Basics 2", mutableListOf(
            "DE/1/MCQ/11", "DE/1/MCQ/12", "DE/1/MCQ/13"
        ), "Basics"),
        Lesson("DE/1/Lesson/18", "Days 2", mutableListOf(
            "DE/1/MCQ/16", "DE/1/MCQ/17", "DE/1/MCQ/18", "DE/1/MCQ/19", "DE/1/MCQ/20"
        ), "Days"),
        Lesson("DE/1/Lesson/19", "Months 4", mutableListOf(
            "DE/1/MCQ/35", "DE/1/MCQ/36", "DE/1/MCQ/37", "DE/1/MCQ/38", "DE/1/MCQ/39"
        ), "Months"),
        Lesson("DE/1/Lesson/20", "Animals 2", mutableListOf(
            "DE/1/MCQ/47", "DE/1/MCQ/48", "DE/1/MCQ/49", "DE/1/MCQ/50"
        ), "Animals"),
        Lesson("DE/1/Lesson/21", "Food Basics 1", mutableListOf(
            "DE/1/MCQ/51", "DE/1/LSTN/56", "DE/1/TRN_DE_EN/61", "DE/1/TRN_EN_DE/63", "DE/1/MCQ/65", "DE/1/FTB/67"
        ), "Food"),

        Lesson("DE/1/Lesson/22", "Animals Basics 1", mutableListOf(
            "DE/1/MCQ/52", "DE/1/TRN_DE_EN/62", "DE/1/TRN_EN_DE/64", "DE/1/RCGN/60", "DE/1/MCQ/66", "DE/1/RCGN/73"
        ), "Animals"),

        Lesson("DE/1/Lesson/23", "Introductions & Greetings 1", mutableListOf(
            "DE/1/FTB/53", "DE/1/FTB/54", "DE/1/LSTN/55", "DE/1/LSTN/69", "DE/1/SPK/57"
        ), "Greetings"),

        Lesson("DE/1/Lesson/24", "Speaking Practice 1", mutableListOf(
            "DE/1/SPK/58", "DE/1/SPK/72", "DE/1/SPK/71", "DE/1/SPK/85", "DE/1/SPK/86"
        ), "Speaking"),

        Lesson("DE/1/Lesson/25", "Food Basics 2", mutableListOf(
            "DE/1/RCGN/59", "DE/1/RCGN/74", "DE/1/TRN_DE_EN/75", "DE/1/TRN_EN_DE/77", "DE/1/MCQ/79", "DE/1/FTB/81"
        ), "Food"),

        Lesson("DE/1/Lesson/26", "Animals Basics 2", mutableListOf(
            "DE/1/MCQ/80", "DE/1/LSTN/84", "DE/1/RCGN/88", "DE/1/TRN_DE_EN/76", "DE/1/TRN_EN_DE/78"
        ), "Animals"),

        Lesson("DE/1/Lesson/27", "Polite Expressions", mutableListOf(
            "DE/1/LSTN/83", "DE/1/LSTN/97", "DE/1/SPK/85", "DE/1/SPK/99", "DE/1/FTB/68", "DE/1/FTB/96"
        ), "Politeness"),

        Lesson("DE/1/Lesson/28", "Food Basics 3", mutableListOf(
            "DE/1/LSTN/70", "DE/1/RCGN/87", "DE/1/TRN_DE_EN/89", "DE/1/TRN_EN_DE/91", "DE/1/MCQ/93", "DE/1/FTB/95", "DE/1/SPK/100"
        ), "Food"),

        Lesson("DE/1/Lesson/29", "Animals Basics 3", mutableListOf(
            "DE/1/TRN_DE_EN/90", "DE/1/TRN_EN_DE/92", "DE/1/MCQ/94", "DE/1/LSTN/98"
        ), "Animals"),

        Lesson("DE/1/Lesson/30", "Greetings & Review", mutableListOf(
            "DE/1/SPK/71", "DE/1/LSTN/55", "DE/1/LSTN/69", "DE/1/LSTN/83", "DE/1/LSTN/97"
        ), "Greetings")

    )
    val Units = mutableStateListOf(
        LUnit(
            "DE/1/Unit/01",
            "Greetings & Politeness",
            mutableStateListOf("DE/1/Lesson/01", "DE/1/Lesson/02", "DE/1/Lesson/12", "DE/1/Lesson/13")
        ),
        LUnit(
            "DE/1/Unit/02",
            "Numbers",
            mutableStateListOf("DE/1/Lesson/03", "DE/1/Lesson/07", "DE/1/Lesson/15")
        ),
        LUnit(
            "DE/1/Unit/03",
            "Colors",
            mutableStateListOf("DE/1/Lesson/04", "DE/1/Lesson/14",)
        ),
        LUnit(
            "DE/1/Unit/04",
            "Days",
            mutableStateListOf("DE/1/Lesson/06", "DE/1/Lesson/18",)
        ),
        LUnit(
            "DE/1/Unit/05",
            "Months",
            mutableStateListOf("DE/1/Lesson/08", "DE/1/Lesson/09", "DE/1/Lesson/10", "DE/1/Lesson/19")
        ),
        LUnit(
            "DE/1/Unit/06",
            "Animals",
            mutableStateListOf("DE/1/Lesson/11", "DE/1/Lesson/20")
        ),
        LUnit(
            "DE/1/Unit/07",
            "Basics",
            mutableStateListOf("DE/1/Lesson/05", "DE/1/Lesson/17")
        ),
        LUnit(
            "DE/1/Unit/08",
            "Food Basics",
            mutableStateListOf("DE/1/Lesson/21", "DE/1/Lesson/25", "DE/1/Lesson/28")
        ),

        LUnit(
            "DE/1/Unit/09",
            "Animals Basics",
            mutableStateListOf("DE/1/Lesson/22", "DE/1/Lesson/26", "DE/1/Lesson/29")
        ),

        LUnit(
            "DE/1/Unit/10",
            "Greetings & Introductions",
            mutableStateListOf("DE/1/Lesson/23", "DE/1/Lesson/30")
        ),

        LUnit(
            "DE/1/Unit/11",
            "Speaking Practice",
            mutableStateListOf("DE/1/Lesson/24")
        ),

        LUnit(
            "DE/1/Unit/12",
            "Politeness & Expressions",
            mutableStateListOf("DE/1/Lesson/27")
        )

    )

    val Sections = mutableStateListOf(
        LSection(
            "DE/1/Section/01",
            "Basics of German",
            mutableStateListOf(
                "DE/1/Unit/01",
                "DE/1/Unit/02",
                "DE/1/Unit/03",
                "DE/1/Unit/04"
            )
        ),
        LSection(
            "DE/1/Section/02",
            "Everyday Essentials",
            mutableStateListOf(
                "DE/1/Unit/05",
                "DE/1/Unit/06",
                "DE/1/Unit/07",
                "DE/1/Unit/08",
                "DE/1/Unit/09",
                "DE/1/Unit/10"
            )
        ),
        LSection(
            "DE/1/Section/03",
            "Speaking & Politeness",
            mutableStateListOf(
                "DE/1/Unit/11",
                "DE/1/Unit/12"
            )
        )

    )



}