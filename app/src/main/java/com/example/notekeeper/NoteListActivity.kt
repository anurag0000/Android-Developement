package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notekeeper.databinding.ActivityNoteListBinding
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            val activityIntent = Intent(this,MainActivity::class.java)
            startActivity(activityIntent)
        }
        ListNotes.adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            DataManager.notes)

        ListNotes.setOnItemClickListener{ parent,view,position, id ->
            val activityIntent = Intent(this, MainActivity::class.java)
            activityIntent.putExtra(EXTRA_NOTE_POSITION,position)
            startActivity(activityIntent)
        }
    }
    override fun onResume() {
        super.onResume()
        (ListNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}