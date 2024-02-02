package com.example.binding_calc

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import com.example.binding_calc.databinding.ActivityMainBinding
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.plus.setOnClickListener {
            add()
        }
        binding.minus.setOnClickListener {
            subtract()
        }
        binding.multiply.setOnClickListener {
            multiply()
        }
        binding.divide.setOnClickListener {
            divide()
        }
    }

    private fun inputIsNotEmpty(): Boolean {
        if (TextUtils.isEmpty(binding.input1.text.toString().trim())) {
            binding.input1.error = "required"
            return false
        }
        if (TextUtils.isEmpty(binding.input2.text.toString().trim())) {
            binding.input2.error = "required"
            return false
        }
        return true
    }

    private fun getInputValue(input: EditText): BigDecimal {
        return input.text.toString().trim().toBigDecimal()
    }

    private fun add() {
        if (inputIsNotEmpty()) {
            val inputdata1 = getInputValue(binding.input1)
            val inputdata2 = getInputValue(binding.input2)
            binding.result.text = inputdata1.add(inputdata2).toString()
        }
    }

    private fun subtract() {
        if (inputIsNotEmpty()) {
            val inputdata1 = getInputValue(binding.input1)
            val inputdata2 = getInputValue(binding.input2)
            binding.result.text = inputdata1.subtract(inputdata2).toString()
        }
    }

    private fun multiply() {
        if (inputIsNotEmpty()) {
            val inputdata1 = getInputValue(binding.input1)
            val inputdata2 = getInputValue(binding.input2)
            binding.result.text = inputdata1.multiply(inputdata2).toString()
        }
    }

    private fun divide() {
        if (inputIsNotEmpty()) {
            val inputdata1 = getInputValue(binding.input1)
            val inputdata2 = getInputValue(binding.input2)
            if (inputdata2.compareTo(BigDecimal.ZERO) == 0) {
                binding.input2.error = "Invalid input"
            } else {
                binding.result.text =
                    inputdata1.divide(inputdata2, 2, RoundingMode.HALF_UP).toString()
            }
        }
    }
}