package com.dimas.androidplayground.screen

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimas.androidplayground.R
import com.dimas.androidplayground.adapter.ChatAdapter
import com.dimas.androidplayground.databinding.FragmentChatBinding
import com.dimas.androidplayground.model.Chat
import com.dimas.androidplayground.utils.BundleConstant


class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private var title: String? = null
    private var button: String? = null
    private var message: String? = null

    private var adapter: ChatAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance(title: String, button: String, message: String) = ChatFragment().apply {
            arguments = Bundle().apply {
                putString(BundleConstant.BUNDLE_TITLE, title)
                putString(BundleConstant.BUNDLE_BUTTON, button)
                putString(BundleConstant.BUNDLE_MESSAGE, message)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            title = args.getString(BundleConstant.BUNDLE_TITLE)
            button = args.getString(BundleConstant.BUNDLE_BUTTON)
            message = args.getString(BundleConstant.BUNDLE_MESSAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            if (adapter == null) adapter = ChatAdapter(
                items = chatList(view.context),
                onLongClick = { position ->
                    showAlertDialog(position)
                }
            )
            listChat.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = this@ChatFragment.adapter
            }
        }
    }

    private fun showDetailChat(data: Chat) {
        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
    }

    private fun showAlertDialog(position: Int) {
        Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(context)
            .setTitle("Delete Chat")
            .setMessage("Are you sure you want to delete this chat?")
            .setPositiveButton("Yes"
            ) { _, _ ->
                adapter?.removeItemChat(position)
            }
            .setNegativeButton("No", null)
            .setIcon(R.drawable.warning)
            .show()
    }

    private fun chatList(context: Context): MutableList<Chat>{
        return mutableListOf(
            Chat(
                image = ContextCompat.getDrawable(context, R.drawable.woman),
                username = "Jane",
                message = "Hi, I'am using whatsapp",
                date = "08:58",
                unreadMessage = 0
            ),
            Chat(
                image = ContextCompat.getDrawable(context, R.drawable.male),
                username = "Mark",
                message = "How are you today",
                date = "today",
                unreadMessage = 8
            ),
            Chat(
                image = ContextCompat.getDrawable(context, R.drawable.male),
                username = "Donny",
                message = "Thank you",
                date = "Yesterday",
                unreadMessage = 1
            ),
            Chat(
                image = ContextCompat.getDrawable(context, R.drawable.male),
                username = "Bryan",
                message = "Okay, See You",
                date = "Monday",
                unreadMessage = 0
            ),
            Chat(
                image = ContextCompat.getDrawable(context, R.drawable.woman),
                username = "Jessica",
                message = "Hmm...",
                date = "Monday",
                unreadMessage = 0
            ),
            Chat(
                image = ContextCompat.getDrawable(context, R.drawable.male),
                username = "Putra",
                message = "Oke",
                date = "Last Week",
                unreadMessage = 0
            ),
            Chat(
                image = ContextCompat.getDrawable(context, R.drawable.woman),
                username = "Gaby",
                message = "Wait a minute",
                date = "Last Week",
                unreadMessage = 0
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}