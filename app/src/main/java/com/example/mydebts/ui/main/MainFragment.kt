package com.example.mydebts.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydebts.adddebt.AddDebtActivity
import com.example.mydebts.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    lateinit var homeViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.debtsRv.layoutManager = LinearLayoutManager(requireContext())

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        val format = SimpleDateFormat("dd.MM.yyyy", Locale("in", "ID"))

        homeViewModel.getDebts(format.format(calendar.time))?.observe(viewLifecycleOwner) {
            binding.debtsRv.adapter = MainAdapter(it)
        }

        binding.addDebtBtn.setOnClickListener {
            val intent = Intent(requireContext(), AddDebtActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}