package com.example.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import android.widget.*
import com.example.notekeeper.databinding.ActivityNoteListBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //val dm = DataManager()
        val adapterCourse = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())

        adapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourses.adapter = adapterCourse

        notePosition = savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET) ?:
            intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)
        if(notePosition != POSITION_NOT_SET)
            displayNote()
        else{
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(EXTRA_NOTE_POSITION, notePosition)
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("Toolbar", "onCreateOptionsMenu: toolbar created")
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_next->{
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu() // this internally calls onPrepareOptionsMenu
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if(notePosition>=DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_next) // added ? to use null safe operator
            if(menuItem!=null){
                menuItem.icon = getDrawable(R.drawable.ic_baseline_not_interested_24) // to sent new icon in place of next icon
                menuItem.isEnabled = false // to disable the next option once it reaches at the end of note
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {

        val note = DataManager.notes[notePosition] // to get the current note
        note.title = textNoteTitle.text.toString() // to save any changes in title at runtime
        note.text = textNoteText.text.toString() // to save any changes in text at runtime
        note.course = spinnerCourses.selectedItem as CourseInfo
    }


}