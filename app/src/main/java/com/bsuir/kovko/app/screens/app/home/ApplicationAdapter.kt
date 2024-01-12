package com.bsuir.kovko.app.screens.app.home

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bsuir.kovko.R
import com.bsuir.kovko.app.dto.answer.ApplicationAnswerDTO
import com.bsuir.kovko.app.screens.app.home.ApplicationAdapter.Companion.PICK_UP_FORM
import com.bsuir.kovko.databinding.ItemApplicationBinding
import java.util.Collections.emptyList

interface ApplicationActionListener {
    fun onApplicationDetails(app: ApplicationAnswerDTO)
    fun copyCheck(app: ApplicationAnswerDTO)
    fun copyIdentifier(app: ApplicationAnswerDTO)
    fun pickUpSubjectForm(app: ApplicationAnswerDTO)
    fun pickUpSubjectRequest(app: ApplicationAnswerDTO)
    fun onCause(app: ApplicationAnswerDTO)
}

class ApplicationDiffCallback(
    private val oldList: List<ApplicationAnswerDTO>,
    private val newList: List<ApplicationAnswerDTO>,
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldApp = oldList[oldItemPosition]
        val newApp = newList[newItemPosition]
        return oldApp.id == newApp.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldApp = oldList[oldItemPosition]
        val newApp = newList[newItemPosition]
        return oldApp == newApp
    }
}

class ApplicationAdapter(
    private val actionListener: ApplicationActionListener,
) : RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder>(), View.OnClickListener {

    class ApplicationViewHolder(val binding: ItemApplicationBinding): RecyclerView.ViewHolder(binding.root)

    var applications: List<ApplicationAnswerDTO> = emptyList()
        set(newValue) {
            val diffCallback = ApplicationDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemApplicationBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        binding.moreImageViewButton.setOnClickListener(this)
        return ApplicationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplicationViewHolder, position: Int) {
        val app = applications[position]
        val context = holder.itemView.context
        with(holder.binding) {
            holder.itemView.tag = app
            moreImageViewButton.tag = app
            numberApplicationTV.setText("Номер заявки ${app.number}")
            statusTV.setText("Статус: ${app.status}")
        }
    }

    override fun getItemCount(): Int = applications.size

    override fun onClick(v: View) {
        val app = v.tag as ApplicationAnswerDTO
        when (v.id){
            R.id.moreImageViewButton -> {
                showPopupMenu(v)
            }
            else -> {
                actionListener.onApplicationDetails(app)
            }
        }
    }

    private fun showPopupMenu(v: View) {
        val popupMenu = PopupMenu(v.context, v)
        val context = v.context
        val app = v.tag as ApplicationAnswerDTO
        if(app.status != "Отклонена")
            popupMenu.menu.add(0, COPY_CHECK, Menu.NONE, context.getString(R.string.COPY_CHECK))
        popupMenu.menu.add(0, COPY_IDENTIFIER, Menu.NONE, context.getString(R.string.COPY_IDENTIFIER))
        if(app.status == "Принята"){
            if (Application.number == app.number)
                popupMenu.menu.add(0, PICK_UP_REQUEST, Menu.NONE, context.getString(R.string.PICK_UP_REQUEST))
            else
                popupMenu.menu.add(0, PICK_UP_FORM, Menu.NONE, context.getString(R.string.PICK_UP_FORM))
        }
        if (app.status == "Откланена")
            popupMenu.menu.add(0, CAUSE, Menu.NONE, context.getString(R.string.CAUSE))
        popupMenu.setOnMenuItemClickListener{
            when (it.itemId){
                COPY_CHECK -> {
                    actionListener.copyCheck(app)
                }
                COPY_IDENTIFIER -> {
                    actionListener.copyIdentifier(app)
                }
                PICK_UP_FORM -> {
                    actionListener.pickUpSubjectForm(app)
                }
                PICK_UP_REQUEST -> {
                    actionListener.pickUpSubjectRequest(app)
                }
                CAUSE -> {
                    actionListener.onCause(app)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    companion object{
        private const val COPY_CHECK = 1
        private const val COPY_IDENTIFIER = 2
        private const val PICK_UP_FORM = 3
        private const val PICK_UP_REQUEST = 4
        private const val CAUSE = 5
    }

}