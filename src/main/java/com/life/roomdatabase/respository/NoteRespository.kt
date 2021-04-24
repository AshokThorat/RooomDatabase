package com.life.roomdatabase.respository

import androidx.lifecycle.LiveData
import com.life.roomdatabase.database.Note
import com.life.roomdatabase.database.NoteDao

class NoteRespository(private var noteDao:NoteDao)
{
    val allNotes:LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun insert(note:Note)
    {
        noteDao.insert(note)
    }
    suspend fun delete(note: Note)
    {
        noteDao.delete(note)
    }


}
/*
/ instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
 */