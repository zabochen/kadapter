package ua.ck.zabochen.androidx

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Kadapter<T> constructor(
    private val dataList: MutableList<T>,
    adapterItemLayoutId: Int,
    private val bindViewData: View.(T) -> Unit,
    private val onClick: T.() -> Unit = {},
    private val onLongClick: T.() -> Unit = {}
) : AbstractKadapter<T>(dataList, adapterItemLayoutId) {

    override fun View.aBindViewData(aDataObject: T) {
        bindViewData(aDataObject)
    }

    override fun aOnClick(view: View, position: Int) {
        dataList[position].onClick()
    }

    override fun aOnLongClick(view: View, position: Int) {
        dataList[position].onLongClick()
    }
}

fun <T> RecyclerView.init(
    dataList: MutableList<T> = mutableListOf(),
    adapterItemLayoutId: Int,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context),
    bindViewData: View.(T) -> Unit,
    onClick: T.() -> Unit = {},
    onLongClick: T.() -> Unit = {}
): Kadapter<T> {
    return Kadapter(
        dataList = dataList,
        adapterItemLayoutId = adapterItemLayoutId,
        bindViewData = bindViewData,
        onClick = onClick,
        onLongClick = onLongClick
    ).apply {
        this@init.layoutManager = layoutManager
        this@init.adapter = this
    }
}