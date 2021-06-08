package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.databinding.ActivityProfileBinding
import ru.skillbranch.devintensive.models.Profile

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileVM
    private var isEdit = false
    private var hideRepos = false
    private var isDarkMode = false
    lateinit var viewFields: Map<String, TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        initViewModel()
        initViews(savedInstanceState)
        val view = binding.root
        setContentView(view)
        binding.buttonEdit.setOnClickListener {
            isEdit = !isEdit
            enterEditMode(isEdit)
        }
        binding.buttonEye.setOnClickListener {
            hideRepos = !hideRepos
            binding.editAboutRepos.transformationMethod = if (hideRepos) PasswordTransformationMethod.getInstance() else HideReturnsTransformationMethod.getInstance()
        }
        binding.darkmode.setOnClickListener {
            isDarkMode = !isDarkMode
            if (isDarkMode) {
                Log.d("asdasd", "$isDarkMode")
                binding.respectRating.setBackgroundColor(Color.BLACK)
                binding.father.setBackgroundColor(Color.rgb(188, 143,157))
                binding.editName.setBackgroundColor(Color.rgb(188, 143,157))
                binding.editSecondName.setBackgroundColor(Color.rgb(188, 143,157))
                binding.editAboutDescr.setBackgroundColor(Color.rgb(188, 143,157))
                binding.editAboutRepos.setBackgroundColor(Color.rgb(188, 143,157))
            } else {
                Log.d("asdasd", "$isDarkMode")
                binding.respectRating.setBackgroundColor(Color.rgb(35, 26,57))
                binding.father.setBackgroundColor(Color.WHITE)
                binding.editName.setBackgroundColor(Color.WHITE)
                binding.editSecondName.setBackgroundColor(Color.WHITE)
                binding.editAboutDescr.setBackgroundColor(Color.WHITE)
                binding.editAboutRepos.setBackgroundColor(Color.WHITE)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun enterEditMode(idEdit: Boolean) {
        val field = listOf<EditText>(
            binding.editName,
            binding.editSecondName,
            binding.editAboutRepos,
            binding.editAboutDescr
        )

        for (item in field) {
            item.isFocusable = isEdit
            item.isFocusableInTouchMode = isEdit
            item.isEnabled = isEdit
        }

        binding.buttonEdit.let {
            if (isEdit) {
                it.setImageResource(R.drawable.button_save)
                it.background.setColorFilter(Color.MAGENTA, PorterDuff.Mode.MULTIPLY)
            } else {
                saveProfileInfo()
                it.setImageResource(R.drawable.ic_baseline_edit_24)
                it.background.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
            }
        }
        binding.buttonEye.visibility = if (isEdit) View.GONE else View.VISIBLE
    }

    private fun initViews(savedInstanceState: Bundle?) {
        binding.let {
            viewFields = mapOf(
                "nickname" to it.profileName,
                "rank" to it.profileRank,
                "firstName" to it.editName,
                "secondName" to it.editSecondName,
                "about" to it.editAboutDescr,
                "repository" to it.editAboutRepos,
                "rating" to it.profileRating,
                "respect" to it.profileRespect
                )
        }
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ProfileVM::class.java)
        viewModel.getProfileData().observe(this, { updateUI(it) })
    }

    private fun updateUI(profile: Profile?) {
        profile?.toMap().also{
            for ((key, item) in viewFields) {
                item.text = it?.get(key).toString()
            }
        }
    }

    private fun saveProfileInfo(){
        Profile(
            firstName = binding.editName.text.toString(),
            secondName = binding.editSecondName.text.toString(),
            description = binding.editAboutDescr.text.toString(),
            repository = binding.editAboutRepos.text.toString()
            ).apply {
                viewModel.saveProfileData(this)
                Log.d("asdasd", "${this.firstName} ${this.secondName} ${this.description} ${this.repository}")
        }
    }
}