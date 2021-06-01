package com.gopal.fypdemo.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.transition.TransitionInflater
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.gopal.fypdemo.R
import com.gopal.fypdemo.databinding.FragmentSplashBinding
import com.gopal.fypdemo.ui.vm.SplashViewModel
import com.gopal.fypdemo.utils.Utils.Companion.hide
import com.gopal.fypdemo.utils.Utils.Companion.hideDelay
import com.gopal.fypdemo.utils.Utils.Companion.moveToNext
import com.gopal.fypdemo.utils.Utils.Companion.slideDown
import com.gopal.fypdemo.utils.Utils.Companion.slideUp
import com.gopal.fypdemo.utils.Utils.Companion.slideUpDelay
import kotlinx.android.synthetic.main.layout_persistent_bottom_sheet.*

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SplashViewModel>()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)
        binding.apply {
            binds(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun binds(view: View?) {
        // initBottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.isDraggable = false // disable dragging

        /*for up button*/
        btSwipeUp.setOnClickListener {
            btSwipeUp.slideUp(500)
            /*hide all after expand*/
            tvSwipeUpDes.hideDelay()
            btSwipeUp.hideDelay()
            layoutFirst.hideDelay()

            bottomSheet.slideUp(1000)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }


        btSignup.setOnClickListener {
            bottomSheet.slideDown()

            Handler().postDelayed({
                bottomSheet.hide()
                setTransAnim(requireContext())
                moveToNext(it, SplashFragmentDirections.actionSplashFragmentToSignUpFragment())
            }, 500)


        }
    }

    /*For set slide transitions*/
    private fun setTransAnim(_context: Context) {
        val inflater = TransitionInflater.from(_context)
        val transitions = inflater.inflateTransition(R.transition.slide_top)
        exitTransition = transitions
    }

}