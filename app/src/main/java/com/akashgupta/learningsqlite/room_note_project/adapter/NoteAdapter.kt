package com.akashgupta.learningsqlite.room_note_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akashgupta.learningsqlite.databinding.RvNoteLayoutBinding
import com.akashgupta.learningsqlite.room_note_project.model.NoteEntityModel
import com.akashgupta.learningsqlite.room_note_project.user.fragment.HomeFragmentDirections

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itemBinding: RvNoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    /*DiffUtil.ItemCallback is a utility class in Android that is used to efficiently update a RecyclerView when
    the data set changes. Instead of refreshing the entire list, it calculates the differences between the old
    and new lists and updates only the changed items.*/
    private val differCallback = object : DiffUtil.ItemCallback<NoteEntityModel>() {
        override fun areItemsTheSame(oldItem: NoteEntityModel, newItem: NoteEntityModel): Boolean {
            return oldItem.id == newItem.id && oldItem.noteTitle == newItem.noteTitle &&
                    oldItem.noteDesc == newItem.noteDesc
        }

        override fun areContentsTheSame(oldItem: NoteEntityModel, newItem: NoteEntityModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback) //bg thread pai list ko compare karta hai

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = RvNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.apply {
            noteTitle.text = currentNote.noteTitle
            noteDesc.text = currentNote.noteDesc
        }

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }

}