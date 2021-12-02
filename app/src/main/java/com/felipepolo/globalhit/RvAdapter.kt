package com.felipepolo.globalhit

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipepolo.globalhit.databinding.TemplateCellColorBinding

class RvAdapter(
    private val context: Context,
    private var colors: ArrayList<Colors>,
    private val onCellClickInterface: OnCellClickInterface
    ) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    interface OnCellClickInterface {
        fun onCellClick(color:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            TemplateCellColorBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = ViewHolder(itemBinding)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int = colors.size

    inner class ViewHolder(val binding: TemplateCellColorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(color: Colors) {
            binding.cell1.setBackgroundColor(Color.parseColor(color.color1))
            binding.cell2.setBackgroundColor(Color.parseColor(color.color2))
            binding.cell3.setBackgroundColor(Color.parseColor(color.color3))
            binding.cell1.setOnClickListener { color.color1?.let { it -> onCellClickInterface.onCellClick(it) } }
            binding.cell2.setOnClickListener { color.color2?.let { it -> onCellClickInterface.onCellClick(it) } }
            binding.cell3.setOnClickListener { color.color3?.let { it -> onCellClickInterface.onCellClick(it) } }
        }
    }
}