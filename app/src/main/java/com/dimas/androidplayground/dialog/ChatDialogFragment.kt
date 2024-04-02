package com.dimas.androidplayground.dialog

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.dimas.androidplayground.R
import com.dimas.androidplayground.databinding.FragmentChatDialogBinding
import com.dimas.androidplayground.model.Chat

class ChatDialogFragment : DialogFragment() {

    private var _binding: FragmentChatDialogBinding? = null
    private val binding get() = _binding!!

    private var actionSubmit: ((data: Chat) -> Unit)? = null

    companion object {
        fun newInstance(
            actionSubmit: (data: Chat) -> Unit
        ): ChatDialogFragment {
            val dialogFragment =  ChatDialogFragment()
            dialogFragment.actionSubmit = actionSubmit
            return dialogFragment
        }
    }

    override fun getTheme(): Int {
        return R.style.AppTheme_Dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonSubmit.setOnClickListener {
                actionSubmit?.invoke(populateChat(it.context, view))
                dismiss()
            }
        }
    }

    private fun populateChat(context: Context, view: View): Chat {
        val username = binding.edtUsername.text.toString().trim()
        val message = binding.edtMessage.text.toString().trim()
        var image = R.drawable.male
        binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
            val radioButton: RadioButton = view.findViewById(checkedId)
            when (radioButton.id) {
                R.id.rbFemale -> {
                    image = R.drawable.woman
                }
            }
        }
        val currentTimeMillis = System.currentTimeMillis()
        val relativeTime = DateUtils.getRelativeTimeSpanString(currentTimeMillis)
        return Chat(
            image = ContextCompat.getDrawable(context, image),
            username = username,
            message = message,
            unreadMessage = 0,
            date = relativeTime.toString()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}