package com.example.agenta.viewmodel

import androidx.lifecycle.*
import com.example.agenta.data.local.AgentaRepository
import com.example.agenta.data.model.User
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AgentaRepository) : ViewModel() {

    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess: LiveData<Boolean> get() = _registrationSuccess

    private val _loginUser = MutableLiveData<User?>()
    val loginUser: LiveData<User?> get() = _loginUser

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val existing = repository.getUserByEmail(email)
                if (existing != null) {
                    _error.postValue("El correo ya está registrado")
                } else {
                    val user = User(name = name, email = email, password = password)
                    repository.registerUser(user)
                    _registrationSuccess.postValue(true)
                }
            } catch (e: Exception) {
                _error.postValue("Error al registrar: ${e.message}")
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = repository.getUserByEmail(email)
                if (user != null && user.password == password) {
                    _loginUser.postValue(user)
                } else {
                    _error.postValue("Credenciales incorrectas")
                }
            } catch (e: Exception) {
                _error.postValue("Error al iniciar sesión: ${e.message}")
            }
        }
    }
}

class AuthViewModelFactory(private val repository: AgentaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}