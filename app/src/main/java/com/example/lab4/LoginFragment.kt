package com.example.lab4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.lab4.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.errorTextView.visibility = View.INVISIBLE
        binding.loginButton.setOnClickListener {
            if (validateInputs()) {
                val login = binding.login.text.toString()
                val password = binding.password.text.toString()
                // val remember = binding.rememberSwitch.isChecked
                viewModel.loginUser(login, password, { username ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, SuccessFragment(username), "SUCCESS_FRAGMENT_TAG")
                        .commit()
                }, { errorMessage ->
                    binding.errorTextView.text = errorMessage
                    binding.errorTextView.visibility = View.VISIBLE
                })
            }
        }
        binding.signupText.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterFragment(viewModel), "REGISTER_FRAGMENT_TAG")
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }

    private fun validateInputs(): Boolean {
        return when {
            binding.login.text.isEmpty() -> {
                binding.errorTextView.text = "Ошибка: Пустая строка логина"
                false
            }
            binding.password.text.isEmpty() -> {
                binding.errorTextView.text = "Ошибка: Пустая строка пароля"
                false
            }
            else -> true
        }.also { binding.errorTextView.visibility = if (it) View.INVISIBLE else View.VISIBLE }
    }
}