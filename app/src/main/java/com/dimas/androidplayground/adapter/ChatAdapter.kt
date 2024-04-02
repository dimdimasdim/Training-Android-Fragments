package com.dimas.androidplayground.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimas.androidplayground.adapter.ChatAdapter.ChatViewHolder
import com.dimas.androidplayground.databinding.ItemChatBinding
import com.dimas.androidplayground.model.Chat

class ChatAdapter(
    private val items: MutableList<Chat>
): RecyclerView.Adapter<ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ChatViewHolder(private val binding: ItemChatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Chat) {
            with(binding) {

            }
        }
    }


}