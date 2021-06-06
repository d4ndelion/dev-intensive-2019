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
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private var isEdit = false
    private var hideRepos = false
    private var isDarkMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
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
        /*binding.darkmode.setOnClickListener {
            isDarkMode = !isDarkMode
            if (isDarkMode) {

            } else {

            }
        }*/
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
            Log.d("asdasd", "${item.text}")
        }

        binding.buttonEdit.let {
            if (isEdit) {
                it.setImageResource(R.drawable.button_save)
                it.background.setColorFilter(Color.MAGENTA, PorterDuff.Mode.MULTIPLY)
            } else {
                it.setImageResource(R.drawable.ic_baseline_edit_24)
                it.background.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
            }
        }
        binding.buttonEye.visibility = if (isEdit) View.GONE else View.VISIBLE
    }
}