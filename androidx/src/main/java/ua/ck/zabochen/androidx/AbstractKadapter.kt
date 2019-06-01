package ua.ck.zabochen.androidx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractKadapter<T> constructor(
    private val aDataList: MutableList<T> = mutableListOf(),
    private val aAdapterItemLayoutId: Int
) : RecyclerView.Adapter<AbstractKadapter.Holder>() {

    protected abstract fun View.aBindViewData(aDataObject: T)
    protected abstract fun aOnClick(view: View, position: Int)
    protected abstract fun aOnLongClick(view: View, position: Int)

    override fun getItemCount(): Int {
        return if (aDataList.isNotEmpty()) aDataList.size else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val holder = Holder(
            LayoutInflater.from(parent.context)
                .inflate(aAdapterItemLayoutId, parent, false)
        )

        // OnClick()
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                aOnClick(holder.itemView, holder.adapterPosition)
            }
        }

        // OnLongClick()
        holder.itemView.setOnLongClickListener {
            when {
                holder.adapterPosition != RecyclerView.NO_POSITION -> {
                    aOnLongClick(holder.itemView, holder.adapterPosition)
                    true
                }
                else -> false
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.aBindViewData(aDataList[position])
    }

    fun updateDataList(newDataList: List<T>) {
        aDataList.apply {
            // Compare old & new lists
            val compareResult = DiffUtil.calculateDiff(
                KadapterDiffUtilCallback(oldDataList = aDataList, newDataList = newDataList)
            )

            // Clear & set new data
            clear()
            addAll(newDataList)

            // Update recyclerView
            compareResult.dispatchUpdatesTo(this@AbstractKadapter)
        }
    }

    fun addDataObject(dataObject: T) {
        aDataList.add(dataObject)
        notifyItemInserted(aDataList.size)
    }

    fun deleteDataObject(position: Int) {
        aDataList.removeAt(position)
        notifyItemRemoved(position)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}