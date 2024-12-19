package com

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.data.Repository
import com.di.Injection
import com.ui.bookedform.FormViewModel
import com.ui.detailbooked.DetailBookedViewModel
import com.ui.listbooked.BookedViewModel
import com.ui.login.LoginViewModel
import com.ui.pilih.PilihViewModel
import com.ui.pilihparkir.PilihParkirViewModel
import com.ui.register.RegisterViewModel

class ViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                return LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                return RegisterViewModel(repository) as T
            }

            modelClass.isAssignableFrom(FormViewModel::class.java) -> {
                return FormViewModel(repository) as T
            }

            modelClass.isAssignableFrom(PilihViewModel::class.java) -> {
                return PilihViewModel(repository) as T
            }

            modelClass.isAssignableFrom(PilihParkirViewModel::class.java) -> {
                return PilihParkirViewModel(repository) as T
            }

            modelClass.isAssignableFrom(BookedViewModel::class.java) -> {
                return BookedViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailBookedViewModel::class.java) -> {
                return DetailBookedViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.repositoryProvide(context))
            }.also { instance = it }
    }

}