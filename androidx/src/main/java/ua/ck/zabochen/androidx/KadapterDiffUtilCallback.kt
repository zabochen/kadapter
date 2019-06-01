package ua.ck.zabochen.androidx

import androidx.recyclerview.widget.DiffUtil

class KadapterDiffUtilCallback<T> constructor(
    private val oldDataList: List<T>,
    private val newDataList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldDataList.size
    }

    override fun getNewListSize(): Int {
        return newDataList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDataList[oldItemPosition] == newDataList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDataList[oldItemPosition] == newDataList[newItemPosition]
    }
}