package ru.skillbranch.devintensive.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initRecycler()
        initToolbar()
        setGroupClick()
    }

    private fun initRecycler() {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val chatAdapter = viewModel.getChatData().value?.let { it ->
            ChatAdapter(it) {
                Snackbar.make(recyclerView, "Clicked on ${it.title}", Snackbar.LENGTH_LONG).show()
            }
        }
        recyclerView = findViewById(R.id.recycler_chat_list)
        recyclerView.apply {
            viewModel.getChatData().observe(this@MainActivity, {
                chatAdapter?.updateData(it)
            })
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(divider)
        }
        val touchCallback = chatAdapter?.let { ChatItemTouchHelperCallback(it){
            viewModel.addToArchive(it.id)
            Snackbar.make(recyclerView, "Are you sure?", Snackbar.LENGTH_LONG).show()
        } }
        val touchHelper = touchCallback?.let { ItemTouchHelper(it) }
        touchHelper?.attachToRecyclerView(recyclerView)
    }

    private fun setGroupClick() {
        val buttonSetGroup: FloatingActionButton = findViewById(R.id.set_group)
        buttonSetGroup.setOnClickListener {
            viewModel.addItems()
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}