package com.example.demo_kotlin.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo_kotlin.enums.ViewModelStatus
import com.example.demo_kotlin.helper.ValidatorData
import com.example.demo_kotlin.helper.ValidatorUtil
import io.konform.validation.ValidationResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _validatorUtil = ValidatorUtil();
    private val _viewModelStatus = MutableLiveData<ViewModelStatus>().apply {
        value = ViewModelStatus.INIT
    }
    val viewModelStatus: LiveData<ViewModelStatus> = _viewModelStatus;

    private val _email = MutableLiveData<String>().apply {
        value = "";
    }
    val email: LiveData<String> = _email;

    private val _password = MutableLiveData<String>().apply {
        value = "";
    }
    val password: LiveData<String> = _password;

    private val _emailValidate = MutableLiveData<ValidationResult<ValidatorData<String>>>()
    val emailValidate : LiveData<ValidationResult<ValidatorData<String>>> = _emailValidate;

    fun onEmailChanged(value : String) {
        _email.value = value;
    }

    fun onPasswordChanged(value : String) {
        _password.value = value;
    }

    fun onAttemptLogin() {
        _emailValidate.value = _validatorUtil.validateEmail(email.value ?: "");
        if (emailValidate.value?.isValid == false) return

        _viewModelStatus.value = ViewModelStatus.LOADING;
        CoroutineScope(Main).launch {
            delay(3000)
            _viewModelStatus.value = ViewModelStatus.SUCCESS
        }
    }
}