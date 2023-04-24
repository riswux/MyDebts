package com.example.mydebts.ui.debts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydebts.databinding.FragmentDebtsBinding
import java.text.SimpleDateFormat
import java.util.*

class MyDebtsFragment : Fragment() {

    private var _binding: FragmentDebtsBinding? = null

    private val binding get() = _binding!!

    lateinit var viewModel: DebtsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentDebtsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DebtsViewModel::class.java]

        binding.mydebtsRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getDebts()?.observe(viewLifecycleOwner){

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            val format = SimpleDateFormat("dd.MM.yyyy", Locale("in", "ID"))

            binding.mydebtsRv.adapter = DebtsAdapter(it, format.format(calendar.time))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}