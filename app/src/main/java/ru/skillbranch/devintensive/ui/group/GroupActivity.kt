package ru.skillbranch.devintensive.ui.group

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.R

class GroupActivity : AppCompatActivity() {
    private lateinit var usersAdapter: UserAdapter
    private lateinit var viewModel: GroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        initToolbar()
        initViews()
        initViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Введите имя пользователя"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.handleSearchQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.handleSearchQuery(newText)
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(GroupViewModel::class.java)
        viewModel.getUserData().observe(this, { usersAdapter.updateData(it) })
        viewModel.getSelectedData().observe(this, { /*updateChip(it)*/ })
    }

    private fun initViews() {
        usersAdapter = UserAdapter { viewModel.handleSelectedItem(it.id) }
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        with(rv_user_list) {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(this@GroupActivity)
            addItemDecoration(divider)
        }

        fab.setOnClickListener {
            viewModel.handleCreateGroup()
            finish()
            overridePendingTransition(R.anim.idle, R.anim.bottom_down)
        }
    }

/*    private fun addChipToGroup(user: UserItem) {
        val chip = Chip(this).apply {
            text = user.fullName
            isCloseIconVisible = true
            tag = user.id
            isClickable = true
        }
        chip.setOnCloseIconClickListener {
            viewModel.handleRemoveChip(it.tag.toString())
            chip_group.addView(chip)
        }
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item?.itemId == android.R.id.home){
            finish()
            overridePendingTransition(R.anim.idle, R.anim.bottom_down)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

/*    private fun updateChip(listUsers: List<UserItem>) {
        chip_group.visibility = if(listUsers.isEmpty()) View.GONE else View.VISIBLE
        val users = listUsers.associateBy { user -> user.id }
            .toMutableMap()
        val views = chip_group.children.associateBy { view -> view.tag }

        for ((k, v) in views) {
            if (!users.containsKey(k)) chip_group.removeView(v)
            else users.remove(k)
        }

        users.forEach { (_, v) ->
            addChipToGroup(v)
        }
    }*/
}