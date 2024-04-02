package com.dimas.androidplayground.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
            if (adapter == null) adapter = ChatAdapter(chatList())
            listChat.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = this@ChatFragment.adapter
            }
        }
    }

    private fun chatList(): MutableList<Chat>{
        return mutableListOf(
            Chat(),
            Chat(),
            Chat(),
            Chat(),
            Chat(),
            Chat(),
            Chat(),
            Chat(),
            Chat(),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}