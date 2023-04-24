package com.example.mydebts.debtdetails

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mydebts.KeyIntent
import com.example.mydebts.R
import com.example.mydebts.databinding.ActivityDebtDetailsBinding
import com.example.mydebts.room.Debt
import java.text.SimpleDateFormat
import java.util.*

class DebtDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDebtDetailsBinding

    val viewModel: DebtDetailsViewModel by viewModels()

    lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebtDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val debt = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(KeyIntent.DEBT_KEY, Debt::class.java)
        } else {
            intent.getSerializableExtra(KeyIntent.DEBT_KEY) as Debt
        }

        val currencys = resources.getStringArray(R.array.currencys)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencys)

        binding.currencySpinner.adapter = adapter

        binding.pickDateImg.setOnClickListener {
            calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()

            DatePickerDialog(
                this, { _: DatePicker?, i: Int, i1: Int, i2: Int ->
                    calendar[Calendar.YEAR] = i
                    calendar[Calendar.MONTH] = i1
                    calendar[Calendar.DAY_OF_MONTH] = i2
                    val dateFormat =
                        SimpleDateFormat("dd.MM.yyyy", Locale("in", "ID"))
                    binding.selectedDateTxt.text = dateFormat.format(calendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        if (debt != null) {
            binding.debtorSwitch.isChecked = debt.isDeptor
            binding.creditorEdt.setText(debt.creditorName)
            binding.selectedDateTxt.text = debt.date

            val currency = when(debt.currency) {
                currencys[1] -> 1
                currencys[2] -> 2
                currencys[3] -> 3
                currencys[4] -> 4
                else -> 0
            }

            binding.currencySpinner.setSelection(currency)

            binding.sumEdt.setText(debt.sum.toString())

            if (binding.debtorSwitch.isChecked) {
                binding.creditorEdt.hint = "Debtor Name"
            } else {
                binding.creditorEdt.hint = "Creditor Name"
            }
        }

        binding.debtorSwitch.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                binding.creditorEdt.hint = "Debtor Name"
            } else {
                binding.creditorEdt.hint = "Creditor Name"
            }
        }

        binding.toolbar2.setNavigationOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
            val isDebtor = binding.debtorSwitch.isChecked
            val creditorName = binding.creditorEdt.text.toString()
            val date = binding.selectedDateTxt.text.toString()
            val sum = binding.sumEdt.text.toString().toInt()
            val currency = binding.currencySpinner.selectedItem.toString()

            if (creditorName.isBlank() || date.equals("date is not selected") || sum.toString().isBlank() || currency == "Currency") {
                Toast.makeText(this, "Please fill the field", Toast.LENGTH_SHORT).show()
            } else {
                val myDebt = Debt(debt!!.id, isDebtor, creditorName, date, sum, currency)
                viewModel.updateDebt(myDebt)
                finish()
            }
        }
    }
}