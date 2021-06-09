package ru.skillbranch.devintensive.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.databinding.ActivityMainBinding

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
    }

    private fun initRecycler() {
        recyclerView = findViewById(R.id.recycler_chat_list)
        recyclerView.adapter = ChatAdapter(viewModel.getChatData())
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }

}