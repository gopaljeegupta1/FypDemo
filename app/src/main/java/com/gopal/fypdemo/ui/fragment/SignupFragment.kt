package com.gopal.fypdemo.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.gopal.fypdemo.R
import com.gopal.fypdemo.databinding.FragmentSignupBinding
import com.gopal.fypdemo.ui.vm.SignupViewModel
import com.gopal.fypdemo.utils.Utils
import com.gopal.fypdemo.utils.Utils.Companion.showToast
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.layout_persistent_bottom_sheet.*


class SignupFragment : Fragment(R.layout.fragment_signup) {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignupViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignupBinding.bind(view)

        binding.apply {
            binds(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun binds(view: View?) {
        btGetOtp.setEnabled(false)
        editTextNumber.addTextChangedListener {
            val phoneNumber = editTextNumber.text.toString().trim()
            btGetOtp.setEnabled(phoneNumber.isNotEmpty() && it.toString().isNotEmpty())
        }

        btGetOtp.setOnClickListener {
            signup(it)
        }


    }

    /*For set slide transitions*/
    private fun setTransAnim(_context: Context) {
        val inflater = TransitionInflater.from(_context)
        val transitions = inflater.inflateTransition(R.transition.slide_top)
        exitTransition = transitions
    }

    fun onSignupSuccess(it: Any?) {
        btGetOtp!!.isEnabled = true

        Handler().postDelayed({
            setTransAnim(requireContext())
            Utils.moveToNext(
                it as View,
                SignupFragmentDirections.actionSignUpFragmentToVerficationFragment()
            )
        }, 500)
    }

    fun onSignupFailed() {
        showToast(context, "Login failed")
        btGetOtp!!.isEnabled = true
    }

    private fun signup(view: View) {
        if (!validate()) {
            onSignupFailed()
            return
        }

        btGetOtp!!.isEnabled = false
        val phone = editTextNumber!!.text.toString().trim()

        // On complete call either onLoginSuccess or onLoginFailed
        onSignupSuccess(view)
        // onLoginFailed();


    }

    fun validate(): Boolean {
        var valid = true

        val mobile = binding.editTextNumber!!.text.toString().trim()
        var _errorText: String? = null

        if (mobile.isEmpty() || mobile.length != 10) {
            binding.editTextNumber!!.error = "Enter Valid Mobile Number"
            valid = false
        } else {
            binding.editTextNumber!!.error = null
        }

        return valid
    }
}