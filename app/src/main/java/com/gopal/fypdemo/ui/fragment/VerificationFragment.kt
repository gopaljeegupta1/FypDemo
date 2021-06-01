package com.gopal.fypdemo.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.gopal.fypdemo.R
import com.gopal.fypdemo.databinding.FragmentVerificationBinding
import com.gopal.fypdemo.ui.vm.VerficationViewModel
import com.gopal.fypdemo.utils.Utils
import com.gopal.fypdemo.utils.Utils.Companion.afterTextChanged
import kotlinx.android.synthetic.main.custom_keyboard.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_verification.*


class VerificationFragment : Fragment(R.layout.fragment_verification) {

    private var _binding: FragmentVerificationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<VerficationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVerificationBinding.bind(view)
        binding.apply {
            binds(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun binds(view: View?) {

        /*for text view listners*/
        btVarify.setEnabled(false)
        etOtp1.addTextChangedListener {
            val etOtp1 = etOtp1.text.toString().trim()
            btVarify.setEnabled(etOtp1.isNotEmpty() && it.toString().isNotEmpty())
        }
        etOtp2.addTextChangedListener {
            val etOtp2 = etOtp2.text.toString().trim()
            btVarify.setEnabled(etOtp2.isNotEmpty() && it.toString().isNotEmpty())
        }
        etOtp3.addTextChangedListener {
            val etOtp3 = etOtp3.text.toString().trim()
            btVarify.setEnabled(etOtp3.isNotEmpty() && it.toString().isNotEmpty())
        }
        etOtp4.addTextChangedListener {
            val etOtp4 = etOtp4.text.toString().trim()
            btVarify.setEnabled(etOtp4.isNotEmpty() && it.toString().isNotEmpty())
        }
        initCustomKeyboard()



        btVarify.setOnClickListener {
            varifyOtp(it)
        }


    }


    /*For set slide transitions*/
    private fun setTransAnim(_context: Context) {
        val inflater = TransitionInflater.from(_context)
        val transitions = inflater.inflateTransition(R.transition.slide_top)
        exitTransition = transitions
    }


    fun onVarifySuccess(it: Any?) {
        btVarify!!.isEnabled = true

        Handler().postDelayed({
            setTransAnim(requireContext())
            Utils.moveToNext(
                it as View,
                VerificationFragmentDirections.actionVerificationFragmentToOtpSucessFragment()
            )
        }, 500)

    }

    fun onVarifyFailed() {
        Utils.showToast(context, "Verification failed")
        btVarify!!.isEnabled = true
    }

    private fun varifyOtp(view: View) {
        if (!validate()) {
            onVarifyFailed()
            return
        }

        btVarify!!.isEnabled = false

        // On complete call either onSuccess or onFailed
        onVarifySuccess(view)


    }

    fun validate(): Boolean {
        var valid = true

        val etOtp1 = binding.etOtp1!!.text.toString().trim()
        val etOtp2 = binding.etOtp2!!.text.toString().trim()
        val etOtp3 = binding.etOtp3!!.text.toString().trim()
        val etOtp4 = binding.etOtp4!!.text.toString().trim()

        if (etOtp1.isEmpty() || etOtp1.length != 1 ||
            etOtp2.isEmpty() || etOtp2.length != 1 ||
            etOtp3.isEmpty() || etOtp3.length != 1 ||
            etOtp4.isEmpty() || etOtp4.length != 1
        ) {
            Utils.showToast(context, "Enter otp first")
            valid = false
        }

        return valid
    }

    private fun initCustomKeyboard() {
        // prevent system keyboard from appearing when EditText is tapped
        etOtp1.setRawInputType(InputType.TYPE_CLASS_TEXT)
        // pass the InputConnection from the EditText to the keyboard
        val ic1: InputConnection = etOtp1.onCreateInputConnection(EditorInfo())
        keyboard.setInputConnection(ic1)

        etOtp1.setOnClickListener { doSomethingWithText(etOtp1) }
        etOtp2.setOnClickListener { doSomethingWithText(etOtp2) }
        etOtp3.setOnClickListener { doSomethingWithText(etOtp3) }
        etOtp4.setOnClickListener { doSomethingWithText(etOtp4) }
    }

    private fun doSomethingWithText(_component: androidx.appcompat.widget.AppCompatEditText) {
        // prevent system keyboard from appearing when EditText is tapped
        _component.setRawInputType(InputType.TYPE_CLASS_TEXT)
        // pass the InputConnection from the EditText to the keyboard
        val ic1: InputConnection = _component.onCreateInputConnection(EditorInfo())
        keyboard.setInputConnection(ic1)
    }

}