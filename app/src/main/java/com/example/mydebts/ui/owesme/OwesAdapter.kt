package com.example.mydebts.ui.owesme

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydebts.KeyIntent.DEBT_KEY
import com.example.mydebts.R
import com.example.mydebts.databinding.ItemDebtsBinding
import com.example.mydebts.debtdetails.DebtDetailsActivity
import com.example.mydebts.room.Debt

class OwesAdapter(private val list: List<Debt>, private val currentDate: String) :
    RecyclerView.Adapter<OwesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwesAdapter.ViewHolder {
        val binding = ItemDebtsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OwesAdapter.ViewHolder, position: Int) {
        val debt = list[position]

        if (debt.date == currentDate) {
            holder.binding.untilTxt.text = holder.itemView.context.getString(R.string.until)
        } else {
            holder.binding.untilTxt.text = holder.itemView.context.getString(R.string.until_date, debt.date)
        }

        holder.binding.nameTxt.text = debt.creditorName
        holder.binding.rubTxt.text = holder.itemView.context.getString(R.string.rub, debt.sum, debt.currency)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DebtDetailsActivity::class.java)
            intent.putExtra(DEBT_KEY, debt)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ItemDebtsBinding) : RecyclerView.ViewHolder(binding.root)
}