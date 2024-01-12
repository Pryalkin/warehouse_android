package com.bsuir.kovko.app.screens.app.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bsuir.kovko.R

class AppDialog() : DialogFragment() {

    private lateinit var numberApp: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        @SuppressLint("InflateParams") val root: View = inflater.inflate(R.layout.app_dialog, null)
        numberApp = root.findViewById<EditText>(R.id.numberAppDialog)

        return builder
            .setView(root)
            .setTitle("Укажите номер заяки:")
            .setPositiveButton("Принять") { dialog: DialogInterface?, which: Int ->
                if (numberApp.text.toString() == "") sendMessage("Введите номер заяки")
                else {
                    Application.number = numberApp.text.toString()
                    sendMessage("Форма отправлена!")
                }
            }
            .setNegativeButton("Отменить", null)
            .create()
    }

    private fun sendMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }


}