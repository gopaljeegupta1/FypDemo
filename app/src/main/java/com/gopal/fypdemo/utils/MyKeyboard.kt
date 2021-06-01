package com.gopal.fypdemo.utils

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import com.gopal.fypdemo.R


public class MyKeyboard(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    // constructors
    constructor(context: Context?) : this(context, null, 0) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}

    // keyboard keys (buttons)
    private var mButton1: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton2: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton3: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton4: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton5: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton6: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton7: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton8: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton9: androidx.appcompat.widget.AppCompatButton? = null
    private var mButton0: androidx.appcompat.widget.AppCompatButton? = null
    private var mButtonDelete: androidx.appcompat.widget.AppCompatButton? = null
    private var mButtonEnter: androidx.appcompat.widget.AppCompatButton? = null

    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    var keyValues = SparseArray<String>()

    // Our communication link to the EditText
    internal var inputConnection: InputConnection? = null
    private fun init(context: Context?, attrs: AttributeSet?) {

        // initialize buttons
        LayoutInflater.from(context).inflate(R.layout.custom_keyboard, this, true)
        mButton1 = findViewById<View>(R.id.tvKey1) as AppCompatButton
        mButton2 = findViewById<View>(R.id.tvKey2) as AppCompatButton
        mButton3 = findViewById<View>(R.id.tvKey3) as AppCompatButton
        mButton4 = findViewById<View>(R.id.tvKey4) as AppCompatButton
        mButton5 = findViewById<View>(R.id.tvKey5) as AppCompatButton
        mButton6 = findViewById<View>(R.id.tvKey6) as AppCompatButton
        mButton7 = findViewById<View>(R.id.tvKey7) as AppCompatButton
        mButton8 = findViewById<View>(R.id.tvKey8) as AppCompatButton
        mButton9 = findViewById<View>(R.id.tvKey9) as AppCompatButton
        mButton0 = findViewById<View>(R.id.tvKey0) as AppCompatButton
        mButtonDelete =
            findViewById<View>(R.id.tvKeyDel) as AppCompatButton
        mButtonEnter =
            findViewById<View>(R.id.btVarify) as AppCompatButton

        // set button click listeners
        mButton1!!.setOnClickListener(this)
        mButton2!!.setOnClickListener(this)
        mButton3!!.setOnClickListener(this)
        mButton4!!.setOnClickListener(this)
        mButton5!!.setOnClickListener(this)
        mButton6!!.setOnClickListener(this)
        mButton7!!.setOnClickListener(this)
        mButton8!!.setOnClickListener(this)
        mButton9!!.setOnClickListener(this)
        mButton0!!.setOnClickListener(this)
        mButtonDelete!!.setOnClickListener(this)
        mButtonEnter!!.setOnClickListener(this)

        // map buttons IDs to input strings
        keyValues.put(R.id.tvKey1, "1")
        keyValues.put(R.id.tvKey2, "2")
        keyValues.put(R.id.tvKey3, "3")
        keyValues.put(R.id.tvKey4, "4")
        keyValues.put(R.id.tvKey5, "5")
        keyValues.put(R.id.tvKey6, "6")
        keyValues.put(R.id.tvKey7, "7")
        keyValues.put(R.id.tvKey8, "8")
        keyValues.put(R.id.tvKey9, "9")
        keyValues.put(R.id.tvKey0, "0")
        keyValues.put(R.id.btVarify, "\n")
    }

    override fun onClick(v: View) {

        // do nothing if the InputConnection has not been set yet
        if (inputConnection == null) return

        // Delete text or input key value
        // All communication goes through the InputConnection
        if (v.getId() === R.id.tvKeyDel) {
            val selectedText = inputConnection!!.getSelectedText(0)
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                inputConnection!!.deleteSurroundingText(1, 0)
            } else {
                // delete the selection
                inputConnection!!.commitText("", 1)
            }
        } else {
            val value = keyValues[v.getId()]
            inputConnection!!.commitText(value, 1)
        }
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    fun setInputConnection(ic: InputConnection?) {
        inputConnection = ic
    }

    init {
        init(context, attrs)
    }
}