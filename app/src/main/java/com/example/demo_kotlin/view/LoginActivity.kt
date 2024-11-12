package com.example.demo_kotlin.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.ContentLoadingProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demo_kotlin.R
import com.example.demo_kotlin.databinding.ActivityLoginBinding
import com.example.demo_kotlin.enums.ViewModelStatus
import com.example.demo_kotlin.enums.isLoading
import com.example.demo_kotlin.extension.hideProgress
import com.example.demo_kotlin.extension.showProgress
import com.example.demo_kotlin.view_model.LoginViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel;
    private lateinit var emailTextField : TextInputEditText;
    private lateinit var passwordTextField : TextInputEditText;
    private lateinit var loginBtn : MaterialButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java);

        // Initialize DataBinding
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupUI();
        setupViewModel();
        setupListener()
    }

    private fun setupUI() {
        loginBtn = findViewById(R.id.loginBtn);
        emailTextField = findViewById(R.id.editTextEmailAddress);
        passwordTextField = findViewById(R.id.editTextPassword);
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar));
        supportActionBar?.title = "Login";
    }

    private fun setupListener() {
        loginBtn.setOnClickListener {
            viewModel.onAttemptLogin()
        }

        emailTextField.addTextChangedListener {
            text -> viewModel.onEmailChanged(text.toString());
        }

        passwordTextField.addTextChangedListener {
            text -> viewModel.onPasswordChanged(text.toString());
        }

    }

    private fun setupViewModel() {
        viewModel.viewModelStatus.observe(this, onViewModelStatusChanged);
    }

    private val onViewModelStatusChanged = Observer<ViewModelStatus> {
        val buttonTitle = "Login";
        if (it.isLoading()) {
            Log.d("LoginActivity", "Email: ${viewModel.email.value}")
            Log.d("LoginActivity", "Password: ${viewModel.password.value}")
            loginBtn.showProgress();
        } else {
            loginBtn.hideProgress(buttonTitle);
        }
    }
}