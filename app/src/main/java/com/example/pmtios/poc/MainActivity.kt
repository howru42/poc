package com.example.pmtios.poc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import android.support.v4.view.MenuItemCompat.getActionView
import android.app.SearchManager
import android.os.Build
import android.widget.SearchView


class MainActivity : AppCompatActivity() {
    private var menu: Menu? = null
    private var itemsList: ArrayList<FolderItem> = ArrayList()
    var tempList: ArrayList<FolderItem> = ArrayList()
    private var mAdapter: CustomFoldersAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv_folders.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onResume() {
        super.onResume()

        itemsList.clear()
        val preferences = getSharedPreferences("images", Context.MODE_PRIVATE)

        var selectedDrawables = JSONArray()

        for (mutableEntry in preferences.all) {

            val json = JSONObject(mutableEntry.value as String)
            selectedDrawables = JSONArray(json.getString("values"))
            val drawables = intArrayOf(R.drawable.dummy_2, R.drawable.dummy_2, R.drawable.dummy_2, R.drawable.dummy_2)
            if (selectedDrawables.length() >= 1) {
                drawables[0] = selectedDrawables.getInt(0);
            }
            if (selectedDrawables.length() >= 2) {
                drawables[1] = selectedDrawables.getInt(1);
            }
            if (selectedDrawables.length() >= 3) {
                drawables[2] = selectedDrawables.getInt(2);
            }
            if (selectedDrawables.length() > 3) {
                drawables[3] = selectedDrawables.getInt(3);
            }
            val title = json.getString("name")
            itemsList.add(FolderItem().setDrawables(drawables).setTitle(title).setId(mutableEntry.key))
        }
        tempList.clear()
        tempList.addAll(itemsList)
        mAdapter = CustomFoldersAdapter(this@MainActivity, itemsList)
        iv_folders.adapter = mAdapter

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateNewFolderActivity::class.java))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        this.menu = menu;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

            val search = menu.findItem(R.id.app_bar_search).actionView as SearchView

            search.setSearchableInfo(manager.getSearchableInfo(componentName))

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
//                    Toast.makeText(this@MainActivity, "new----" + newText, Toast.LENGTH_SHORT).show()
                    searchTitles(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
//                    Toast.makeText(this@MainActivity, "search---" + query, Toast.LENGTH_SHORT).show()
                    return true
                }


            })

        }

        return true
    }

    private fun searchTitles(newText: String?) {
        if (newText != null && newText.toString().trim().isNotEmpty()) {
            itemsList?.clear()
            itemsList.addAll(tempList
                    .filter { it.getTitle().contains(newText.toString()) })
        } else {
            itemsList.clear()
            itemsList.addAll(tempList)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_search -> {
                //                getGalleryItems();

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
