package ru.skillbranch.devintensive.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile

class ProfileVM : ViewModel() {

    private var repository: PreferencesRepository = PreferencesRepository
    private var profileData = MutableLiveData<Profile>()

    init {
        Log.d("PROFILE_ACTIVITY_VM", "init ProfileVM")
        profileData.value = repository.getProfile()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("PROFILE_ACTIVITY_VM", "cleared VM")
    }

    fun getProfileData(): LiveData<Profile> = profileData

    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }
}