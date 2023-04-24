package com.example.mydebts.ui.owesme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydebts.databinding.FragmentOwesMeBinding
import com.example.mydebts.ui.debts.DebtsAdapter
import java.text.SimpleDateFormat
import java.util.*

class OwesMeFragment : Fragment() {

    private var _binding: FragmentOwesMeBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: OwesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOwesMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[OwesViewModel::class.java]

        binding.owesRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getOwes()?.observe(viewLifecycleOwner){

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            val format = SimpleDateFormat("dd.MM.yyyy", Locale("in", "ID"))

            binding.owesRv.adapter = DebtsAdapter(it, format.format(calendar.time))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}