package com.umang.emicalc

import android.os.Bundle
import android.text.TextUtils
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startPoint = 0
        var endPoint = 0
        var numberOfYear = 1

        sbNumberOfYear.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                numberOfYear = progress.toString().toInt()
                tvShowNOY.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPoint = seekBar.progress
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPoint = seekBar.progress
                }
            }

        })

        btnCalculate.setOnClickListener {
            if (TextUtils.isEmpty(etAmount.text.toString()) || TextUtils.isEmpty(etROI.text.toString())) {
                Toast.makeText(this, "Empty fields not allowed", Toast.LENGTH_SHORT).show()
            } else {
                val amount = etAmount.text.toString().toInt()
                val roi = etROI.text.toString().toDouble()

                val rate = roi / 12 / 100
                val month = numberOfYear * 12
                val numerator = (1 + rate).pow(month)
                val denominator = numerator - 1
                val emiResult = ((amount * rate * numerator) / denominator).toFloat()

                Toast.makeText(this, "EMI calculated is $emiResult", Toast.LENGTH_LONG).show()
            }
        }
    }
}