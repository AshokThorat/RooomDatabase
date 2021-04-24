package com.life.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.life.roomdatabase.adapter.INote
import com.life.roomdatabase.adapter.NoteAdapter
import com.life.roomdatabase.database.Note
import com.life.roomdatabase.models.NoteModels

class MainActivity : AppCompatActivity(), INote {

    lateinit var viewModels: NoteModels
    lateinit var noteEdt: EditText
    lateinit var addBtn: Button
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  viewModels==ViewModelProvider(this).get(NoteModels::class.java)
        noteEdt = findViewById(R.id.note_edt)

        addBtn = findViewById(R.id.add_btn)
        recyclerView=findViewById(R.id.recylerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NoteAdapter(this,this)
        recyclerView.adapter=adapter



        viewModels = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteModels::class.java)
        viewModels.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updaeList(it)


            }


        })

        addBtn.setOnClickListener {

            val noteText=noteEdt.text.toString()
            if(noteText.isNotEmpty())
            {
                viewModels.insertNote(Note(noteText))
            }

        }


    }

    override fun onItemClick(note: Note) {
        viewModels.deleteNote(note)

    }

}