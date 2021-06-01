package com.gopal.fypdemo.utils

import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

open class Utils {
    companion object {

        fun View.hide() {
            visibility = View.GONE
        }

        fun View.show() {
            visibility = View.VISIBLE
        }

        fun View.slideUp(duration: Int = 1000) {
            visibility = View.VISIBLE
            val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
            animate.duration = duration.toLong()
            animate.fillAfter = true
            this.startAnimation(animate)
        }

        fun View.slideUpDelay(duration: Int = 100) {
            Handler().postDelayed({
                slideUp()
            }, duration.toLong())
        }

        fun View.hideDelay(duration: Int = 1000) {
            Handler().postDelayed({
                visibility = View.GONE
            }, duration.toLong())

        }

        fun View.slideDown(duration: Int = 500) {
            visibility = View.VISIBLE
            val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
            animate.duration = duration.toLong()
            animate.fillAfter = true
            this.startAnimation(animate)
        }

        /*move to next screen */
        fun moveToNext(view: View, directions: NavDirections) {
            val navController: NavController = Navigation.findNavController(view)
//            val action = SplashFragmentDirections.actionSplashFragmentToSignUpFragment()
            navController.navigate(directions)
        }

        fun showToast(_context: Context?, _msg: String) {
            Toast.makeText(_context, _msg, Toast.LENGTH_LONG).show()
        }

        fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
            this.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(editable: Editable?) {
                    afterTextChanged.invoke(editable.toString())
                }
            })
        }


    }
}