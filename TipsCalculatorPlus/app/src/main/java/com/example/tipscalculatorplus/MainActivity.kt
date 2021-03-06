package com.example.tipscalculatorplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipscalculatorplus.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip(){
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if(cost == null) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_20_percent -> 0.20
            R.id.option_18_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        if (binding.roundSwitch.isChecked) tip = ceil(tip)
        displayTip(tip)
    }

    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
