## Kadapter - RecyclerView Adapter

#### Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

#### Step 2. Add the dependency:

```
dependencies {
    implementation 'com.github.zabochen:kadapter:0.1'
}
```

##

## How to use:

### Data Model
```
data class User(var id: Int, var name: String)
```

### Init RecyclerView & Adapter
```
this.kadapter = activityMain_recyclerView.init(
    // Data List
    dataList = defaultUserList,

    // Adapter Layout
    adapterItemLayoutId = R.layout.adapter_item_user,

    // Layout Manager
    layoutManager = LinearLayoutManager(applicationContext),

    // Layout Items
    bindViewData = {
        adapterItemUser_textView_someText.text = it.id.toString().plus(", ").plus(it.name)
    },

    // Clicks
    onClick = {
        info { "onClick => ID: ${this.id}, NAME: ${this.name}" }
    },
    onLongClick = {
        info { "onLongClick => ID: ${this.id}, NAME: ${this.name}" }
    }
)
```

### Update RecyclerView
```
// updateDataList(newDataList)
activityMain_button_update.setOnClickListener {
    this.kadapter.updateDataList(newUserList)
}

// addDataObject(NewDataObject())
activityMain_button_add.setOnClickListener {
    this.kadapter.addDataObject(User(10, "User_100"))
}

// deleteDataObject(position)
activityMain_button_delete.setOnClickListener {
    if (kadapter.itemCount != 0) {
        this.kadapter.deleteDataObject(Random().nextInt(kadapter.itemCount))
    }
}
```