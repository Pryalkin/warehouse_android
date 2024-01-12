package com.bsuir.kovko.app.screens.app.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsuir.kovko.Singletons.navigator
import com.bsuir.kovko.app.dto.SubjectDTO
import com.bsuir.kovko.app.dto.WarehouseDTO
import com.bsuir.kovko.app.dto.answer.ApplicationAnswerDTO
import com.bsuir.kovko.app.dto.utils.Role
import com.bsuir.kovko.app.utils.observeEvent
import com.bsuir.kovko.app.views.HomeViewModel
import com.bsuir.kovko.databinding.FragmentHomeForViewingPage2Binding
import org.apache.commons.lang3.math.NumberUtils
import kotlin.properties.Delegates


class HomeForViewingPage2Fragment : Fragment(){

    private var pageNumber by Delegates.notNull<Int>()
    private lateinit var binding: FragmentHomeForViewingPage2Binding
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var applicationAdapter: ApplicationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = if (arguments != null) requireArguments().getInt("num") else 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeForViewingPage2Binding.inflate(inflater)
        val role = viewModel.getRole()
        observeShowAuthMessageEvent()
        when (pageNumber) {
            0 -> {
                customizeScreen(b = true, b1 = false)
                if(role == Role.ROLE_ADMIN.name) customizeScreen2(b = true, b1 = false)
                else {
                    viewModel.getWarehouseName()
                    viewModel.warehouseName.observe(viewLifecycleOwner){
                        seeSpinner(it, binding.spinnerWarehouse)
                    }
                    customizeScreen2(b = false, b1 = true)
                }
                binding.btnSendAdmin.setOnClickListener {
                    binding.apply {
                        if (etWarehouseNameAdmin.text.toString() == "") sendMessage("Введите название склада!")
                        else if (etLengthAdmin.text.toString() == "") sendMessage("Введите длину склада!")
                        else if (!NumberUtils.isParsable(etLengthAdmin.text.toString())) sendMessage("Вы ввели не число!")
                        else if (etWidthAdmin.text.toString() == "") sendMessage("Введите ширигу склада!")
                        else if (!NumberUtils.isParsable(etWidthAdmin.text.toString())) sendMessage("Вы ввели не число!")
                        else if (etHeightAdmin.text.toString() == "") sendMessage("Введите высоту склада!")
                        else if (!NumberUtils.isParsable(etHeightAdmin.text.toString())) sendMessage("Вы ввели не число!")
                        else {
                            val warehouseNameAdmin = etWarehouseNameAdmin.text.toString()
                            val lengthAdmin = etLengthAdmin.text.toString().toDouble()
                            val widthAdmin = etWidthAdmin.text.toString().toDouble()
                            val heightAdmin = etHeightAdmin.text.toString().toDouble()
                            val warehouseDTO: WarehouseDTO = WarehouseDTO(
                                name = warehouseNameAdmin,
                                length = lengthAdmin,
                                width = widthAdmin,
                                height = heightAdmin)
                            viewModel.createWarehouse(warehouseDTO)
                            etWarehouseNameAdmin.setText("")
                            etLengthAdmin.setText("")
                            etWidthAdmin.setText("")
                            etHeightAdmin.setText("")
                        }
                    }
                }
                binding.btnSendUser.setOnClickListener {
                    binding.apply {
                        if (etLengthUser.text.toString() == "") sendMessage("Введите длину предмета!")
                        else if (!NumberUtils.isParsable(etLengthUser.text.toString())) sendMessage("Вы ввели не число!")
                        else if (etWidthUser.text.toString() == "") sendMessage("Введите ширигу предмета!")
                        else if (!NumberUtils.isParsable(etWidthUser.text.toString())) sendMessage("Вы ввели не число!")
                        else if (etHeightUser.text.toString() == "") sendMessage("Введите высоту предмета!")
                        else if (!NumberUtils.isParsable(etHeightUser.text.toString())) sendMessage("Вы ввели не число!")
                        else {
                            val warehouseName = binding.spinnerWarehouse.selectedItem.toString()
                            val lengthAdmin = etLengthUser.text.toString().toDouble()
                            val widthAdmin = etWidthUser.text.toString().toDouble()
                            val heightAdmin = etHeightUser.text.toString().toDouble()
                            val subjectDTO: SubjectDTO = SubjectDTO(
                                warehouseName = warehouseName,
                                length = lengthAdmin,
                                width = widthAdmin,
                                height = heightAdmin)
                            viewModel.createSubject(subjectDTO)
                            etLengthUser.setText("")
                            etWidthUser.setText("")
                            etHeightUser.setText("")
                        }
                    }
                }
            }
            1 -> {
                customizeScreen(b = false, b1 = true)
                if(role == Role.ROLE_ADMIN.name) customizeScreen3(b = true, b1 = false)
                else {
                    customizeScreen3(b = false, b1 = true)
                    configureTheAdapterForApp()
                }
            }
        }
        return binding.root
    }

    private fun customizeScreen3(b: Boolean, b1: Boolean) {
        binding.apply {
            if (b) seeWarehouseForAdmin.visibility = View.VISIBLE
            else seeWarehouseForAdmin.visibility = View.GONE
            if (b1) seeApplicationForUser.visibility = View.VISIBLE
            else seeApplicationForUser.visibility = View.GONE
        }
    }

    private fun customizeScreen2(b: Boolean, b1: Boolean) {
        binding.apply {
            if (b) seeFormForAdmin.visibility = View.VISIBLE
            else seeFormForAdmin.visibility = View.GONE
            if (b1) seeFormForUser.visibility = View.VISIBLE
            else seeFormForUser.visibility = View.GONE
        }
    }

    private fun observeShowAuthMessageEvent() = viewModel.message.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private fun configureTheAdapterForApp() {
        applicationAdapter = ApplicationAdapter(object : ApplicationActionListener{
            override fun onApplicationDetails(app: ApplicationAnswerDTO) {
                navigator().showDetailApp(app)
            }
            override fun copyCheck(app: ApplicationAnswerDTO) {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, app.file)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            override fun copyIdentifier(app: ApplicationAnswerDTO) {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, app.number)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
            override fun pickUpSubjectForm(app: ApplicationAnswerDTO) {
                Application.number = ""
                val dialog = AppDialog()
                dialog.show(requireActivity().supportFragmentManager, null)
            }
            override fun pickUpSubjectRequest(app: ApplicationAnswerDTO) {
                viewModel.pickUpSubject(Application.number)
                Application.number = ""
            }

            override fun onCause(app: ApplicationAnswerDTO) {
                Toast.makeText(requireContext(), app.message, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.apps.observe(viewLifecycleOwner){
            applicationAdapter.applications = it
        }

        val layoutManagerAnnouncement = LinearLayoutManager(context)
        binding.recyclerViewApp.layoutManager = layoutManagerAnnouncement
        binding.recyclerViewApp.adapter = applicationAdapter
        val itemAnimatorAnnouncement = binding.recyclerViewApp.itemAnimator
        if (itemAnimatorAnnouncement is DefaultItemAnimator){
            itemAnimatorAnnouncement.supportsChangeAnimations = false
        }
        viewModel.getApplications()
    }


    private fun seeSpinner(str: List<String>, spinnerForString: Spinner) {
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            str
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.apply {
            spinnerForString.adapter = adapter
            spinnerForString.setSelection(0)
        }
    }

    private fun sendMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }

    private fun customizeScreen(b: Boolean, b1: Boolean) {
        binding.apply {
            if (b) seeForm.visibility = View.VISIBLE
            else seeForm.visibility = View.GONE
            if (b1) seeWarehouse.visibility = View.VISIBLE
            else seeWarehouse.visibility = View.GONE
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(page: Int): HomeForViewingPage2Fragment {
            val fragment: HomeForViewingPage2Fragment = HomeForViewingPage2Fragment()
            val args = Bundle()
            args.putInt("num", page)
            fragment.arguments = args
            return fragment
        }
    }

}

