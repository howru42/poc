package com.example.pmtios.poc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_folders.layoutManager = GridLayoutManager(this, 2)

        var itemsList = ArrayList<FolderItem>()
        itemsList.add(FolderItem().setDraw_one(R.drawable.dummy_2).setDraw_two(R.drawable.dummy_3).setDraw_three(R.drawable.dummy_4)
                .setDraw_four(R.drawable.dummy_1).setTitle("Folder 1"))
        itemsList.add(FolderItem().setDraw_one(R.drawable.dummy_1).setDraw_two(R.drawable.dummy_4).setDraw_three(R.drawable.dummy_4)
                .setDraw_four(R.drawable.dummy_1).setTitle("Folder 2"))
        itemsList.add(FolderItem().setDraw_one(R.drawable.dummy_3).setDraw_two(R.drawable.dummy_1).setDraw_three(R.drawable.dummy_4)
                .setDraw_four(R.drawable.dummy_4).setTitle("Folder 3"))
        itemsList.add(FolderItem().setDraw_one(R.drawable.dummy_2).setDraw_two(R.drawable.dummy_4).setDraw_three(R.drawable.dummy_4)
                .setDraw_four(R.drawable.dummy_3).setTitle("Folder 4"))
        itemsList.add(FolderItem().setDraw_one(R.drawable.dummy_1).setDraw_two(R.drawable.dummy_3).setDraw_three(R.drawable.dummy_2)
                .setDraw_four(R.drawable.dummy_1).setTitle("Folder 5"))

        var mAdapter = CustomFoldersAdapter(this@MainActivity, itemsList)
        iv_folders.adapter = mAdapter

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateNewFolderActivity::class.java))

        }

    }
}
