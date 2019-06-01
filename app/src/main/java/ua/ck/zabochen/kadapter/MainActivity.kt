package ua.ck.zabochen.kadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_item_user.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import ua.ck.zabochen.androidx.Kadapter
import ua.ck.zabochen.androidx.init
import java.util.*

class MainActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var kadapter: Kadapter<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
    }

    private fun setUi() {
        // Layout
        setContentView(R.layout.activity_main)

        // UserLists
        val defaultUserList: MutableList<User> = mutableListOf()
        defaultUserList.apply {
            add(User(1, "User_1"))
            add(User(2, "User_2"))
            add(User(3, "User_3"))
            add(User(4, "User_4"))
            add(User(5, "User_5"))
        }

        val newUserList: MutableList<User> = mutableListOf()
        newUserList.apply {
            add(User(1, "User_1"))
            add(User(2, "User_2"))
            add(User(44, "User_4"))
            add(User(5, "User_5"))
            add(User(6, "User_6"))
            add(User(7, "User_7"))
            add(User(8, "User_8"))
        }

        // Buttons
        activityMain_button_update.setOnClickListener {
            this.kadapter.updateDataList(newUserList)
        }

        activityMain_button_add.setOnClickListener {
            this.kadapter.addDataObject(User(10, "User_100"))
        }

        activityMain_button_delete.setOnClickListener {
            if (kadapter.itemCount != 0) {
                this.kadapter.deleteDataObject(Random().nextInt(kadapter.itemCount))
            }
        }

        // RecyclerView
        this.kadapter = activityMain_recyclerView.init(
            dataList = defaultUserList,
            adapterItemLayoutId = R.layout.adapter_item_user,
            layoutManager = LinearLayoutManager(applicationContext),
            bindViewData = {
                adapterItemUser_textView_someText.text = it.id.toString().plus(", ").plus(it.name)
            },
            onClick = {
                info { "onClick => ID: ${this.id}, NAME: ${this.name}" }
            },
            onLongClick = {
                info { "onLongClick => ID: ${this.id}, NAME: ${this.name}" }
            }
        )
    }
}

data class User(
    var id: Int,
    var name: String
)
