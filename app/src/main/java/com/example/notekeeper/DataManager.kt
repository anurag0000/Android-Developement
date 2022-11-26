package com.example.notekeeper

object DataManager {
    val courses = HashMap<String,CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }
    private fun initializeCourses(){
        var course = CourseInfo("Android Intents","Android programming with Intents")
        courses.set(course.courseId,course)
        course = CourseInfo("Android Async","Android async programming and services")
        courses.set(course.courseId,course)
        course = CourseInfo("java_lang","Java Fundamentals : The Java Language")
        courses.set(course.courseId,course)
        course = CourseInfo("java_core","Java Fundamentals : The core Platform")
        courses.set(course.courseId,course)
    }
    private fun initializeNotes(){
        var note = NoteInfo(courses.getValue("Android Intents"),"Dynamic Intent Resolution","Anurag, Wow, Intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(courses.getValue("Android Intents"),"Dynamic Intent Resolution","Anushant, Wow, Intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(courses.getValue("Android Intents"),"Dynamic Intent Resolution","Shaksham, Wow, Intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(courses.getValue("Android Intents"),"Dynamic Intent Resolution","Shruti, Wow, Intents allow components to be resolved at runtime")
        notes.add(note)
    }


}