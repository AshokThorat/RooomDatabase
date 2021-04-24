package com.life.roomdatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.life.roomdatabase.R
import com.life.roomdatabase.database.Note
import org.w3c.dom.Text

class NoteAdapter(private val context: Context, val listener:INote):RecyclerView.Adapter<NoteAdapter.MyHolder>()
{
    val allNotes=ArrayList<Note>()
    inner class MyHolder(v:View):RecyclerView.ViewHolder(v)
    {
        var noteTxt=v.findViewById<TextView>(R.id.textView)
        var deleteImag=v.findViewById<ImageView>(R.id.imageView)
        var idTxt=v.findViewById<TextView>(R.id.id_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

       val v= MyHolder(LayoutInflater.from(context).inflate(R.layout.note_row,parent,false))

        v.deleteImag.setOnClickListener {
            listener.onItemClick(allNotes[v.adapterPosition])
        }
        return v

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.noteTxt.text=allNotes[position].text
        holder.idTxt.text=allNotes[position].id.toString()
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updaeList(newList:List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INote
{
    fun onItemClick(note:Note)


}