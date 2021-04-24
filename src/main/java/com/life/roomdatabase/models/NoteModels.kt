package com.life.roomdatabase.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.life.roomdatabase.database.Note
import com.life.roomdatabase.database.NoteDatabase
import com.life.roomdatabase.respository.NoteRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteModels(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    private val respository:NoteRespository
    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
        respository=NoteRespository(dao)
        allNotes=respository.allNotes
    }
    fun deleteNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {

        respository.delete(note)

    }
    fun insertNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        respository.insert(note)
    }
}