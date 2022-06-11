package com.example.android.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var btnOne: Button? = null
    private var btnTwo: Button? = null
    private var btnThree: Button? = null
    private var btnDivide: Button? = null
    private var btnFour: Button? = null
    private var btnFive: Button? = null
    private var btnSix: Button? = null
    private var btnMultiply: Button? = null
    private var btnSeven: Button? = null
    private var btnEight: Button? = null
    private var btnNine: Button? = null
    private var btnMinus: Button? = null
    private var btnZero: Button? = null
    private var btnDot: Button? = null
    private var btnClear: Button? = null
    private var btnAdd: Button? = null


    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    private var lastMinus: Boolean = false

    //after appending the digits or operators,
    // make sure to change the boolean values of lastNumeric and lastDot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        btnOne?.setOnClickListener {
            tvInput?.append("1")
            lastNumeric = true
            lastDot = false
        }
        btnTwo?.setOnClickListener {
            tvInput?.append("2")
            lastNumeric = true
            lastDot = false
        }
        btnThree?.setOnClickListener {
            tvInput?.append("3")
            lastNumeric = true
            lastDot = false
        }
        btnDivide?.setOnClickListener {
            //if TV isnt empty then append the operator sign, provided that TV contains a no.
            // and any operator is not present in the TV
            tvInput?.text?.let {
                if (lastNumeric && !isOperatorAdded(it.toString())){
                    tvInput?.append("/")
                }
            }
        }
        btnFour?.setOnClickListener {
            tvInput?.append("4")
            lastNumeric = true
            lastDot = false
        }
        btnFive?.setOnClickListener {
            tvInput?.append("5")
            lastNumeric = true
            lastDot = false
        }
        btnSix?.setOnClickListener {
            tvInput?.append("6")
            lastNumeric = true
            lastDot = false
        }
        btnMultiply?.setOnClickListener {
            //if TV isnt empty then append the operator sign, provided that TV contains a no.
            // and any operator is not present in the TV
            tvInput?.text?.let {
                if (lastNumeric && !isOperatorAdded(it.toString())){
                    tvInput?.append("x")
                }
            }
        }
        btnSeven?.setOnClickListener {
            tvInput?.append("7")
            lastNumeric = true
            lastDot = false
        }
        btnEight?.setOnClickListener {
            tvInput?.append("8")
            lastNumeric = true
            lastDot = false
        }
        btnNine?.setOnClickListener {
            tvInput?.append("9")
            lastNumeric = true
            lastDot = false
        }
        btnMinus?.setOnClickListener {
            tvInput?.append("-")

        }


        btnZero?.setOnClickListener {
            tvInput?.append("0")
            lastNumeric = true
            lastDot = false
        }
        btnDot?.setOnClickListener {
            if (lastNumeric && !lastDot ){
                tvInput?.append(".")
                lastNumeric = false
                lastDot = true
            }
        }
        btnClear?.setOnClickListener {
            tvInput?.text = ""
        }
        btnAdd?.setOnClickListener {
            //if TV isnt empty then append the operator sign, provided that TV contains a no.
            // and any operator is not present in the TV
            tvInput?.text?.let {
                if (lastNumeric && !isOperatorAdded(it.toString())){
                    tvInput?.append("+")
                }
            }
        }

    }

    private fun initViews() {
        tvInput = findViewById(R.id.tvInput)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnDivide = findViewById(R.id.btnDivide)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)
        btnMinus = findViewById(R.id.btnMinus)
        btnZero = findViewById(R.id.btnZero)
        btnDot = findViewById(R.id.btnDot)
        btnClear = findViewById(R.id.btnClear)
        btnAdd = findViewById(R.id.btnAdd)

    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())                 }

                else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())                 }

                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())                 }

                else if (tvValue.contains("x")) {
                    val splitValue = tvValue.split("x")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result:String):String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }

    private fun isOperatorAdded(value:String): Boolean{
        return if (value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    ||value.contains("x")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }
}