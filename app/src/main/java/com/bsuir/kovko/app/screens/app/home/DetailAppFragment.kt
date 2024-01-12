package com.bsuir.kovko.app.screens.app.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bsuir.kovko.app.dto.answer.ApplicationAnswerDTO
import com.bsuir.kovko.app.views.HomeViewModel
import com.bsuir.kovko.databinding.FragmentDetailAppBinding

class DetailAppFragment : Fragment() {

    lateinit var binding: FragmentDetailAppBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailAppBinding.inflate(inflater)
        val id: Long = requireArguments().getString(ARG_APP_ID)!!.toLong()
        viewModel.getApplication(id)
        observeMovie()
        return binding.root
    }

    private fun observeMovie() = viewModel.app.observe(viewLifecycleOwner) {
        binding.apply {
            tvWarehouse.text = "Название склада: ${it.warehouseName}"
            tvNumberApplication.text = "Номер заявки: ${it.number}"
            tvWidth.text = "Ширина предмета: ${it.subjectAnswerDTO.width} м"
            tvLength.text = "Длина предмета: ${it.subjectAnswerDTO.length} м"
            tvHeight.text = "Высота предмета: ${it.subjectAnswerDTO.height} м"
            tvStatus.text = "Статус предмета: ${it.status}"
            tvMessage.text = "Сообщение: ${it.message}"
        }
    }

    companion object {
        private const val ARG_APP_ID = "ARG_APP_ID"
        fun newInstance(app: ApplicationAnswerDTO): DetailAppFragment {
            val fragment = DetailAppFragment()
            fragment.arguments = bundleOf(ARG_APP_ID to app.id.toString())
            return fragment
        }
    }
}