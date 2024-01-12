package com.bsuir.kovko.app.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsuir.kovko.Singletons
import com.bsuir.kovko.app.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogoutViewModel(
    private val authRepository: AuthRepository = Singletons.authRepository
): ViewModel() {

    fun logout(){
        viewModelScope.launch {
            delay(5000)
            authRepository.logout();
        }

    }

}