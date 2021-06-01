package com.gopal.fypdemo.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.gopal.fypdemo.ui.vm.OtpSucessViewModel
import com.gopal.fypdemo.R
import com.gopal.fypdemo.databinding.FragmentOtpsuccessBinding
import com.gopal.fypdemo.databinding.FragmentSignupBinding
import kotlinx.android.synthetic.main.fragment_signup.*

class OtpSuccessFragment : Fragment(R.layout.fragment_otpsuccess) {

    private var _binding: FragmentOtpsuccessBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<OtpSucessViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOtpsuccessBinding.bind(view)
        binding.apply {
            binds(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun binds(view: View?) {
        setTransAnim(requireContext())
        // moveToNext(it)
    }

    /*For set slide transitions*/
    private fun setTransAnim(_context: Context) {
        val inflater = TransitionInflater.from(_context)
        val transitions = inflater.inflateTransition(R.transition.fade_center)
        enterTransition = transitions
    }

}