package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOne.setOnClickListener{ append("1",true) }
        tvTwo.setOnClickListener{ append("2",true) }
        tvThree.setOnClickListener{ append("3",true) }
        tvFour.setOnClickListener{ append("4",true) }
        tvFive.setOnClickListener{ append("5",true) }
        tvSix.setOnClickListener{ append("6",true) }
        tvSeven.setOnClickListener{ append("7",true) }
        tvEight.setOnClickListener{ append("8",true) }
        tvNine.setOnClickListener{ append("9",true) }
        tvDot.setOnClickListener{ append(".",true) }
        tvZero.setOnClickListener{ append("0",true) }

        tvAdd.setOnClickListener{ append("+",false) }
        tvSub.setOnClickListener{ append("-",false) }
        tvMulti.setOnClickListener{ append("*",false) }
        tvDivide.setOnClickListener{ append("/",false) }
        tvopen.setOnClickListener{ append("(",false) }
        tvClose.setOnClickListener{ append(")",false) }

        tvClear.setOnClickListener {
            tvCalculate.text = ""
            tvResult.text = ""
        }
        ivBack.setOnClickListener {
            val string = tvCalculate.text.toString()
            if(string.isNotEmpty()) {
                tvCalculate.text = string.substring(0,string.length-1)
            }
        }

        tvEqual.setOnClickListener {
            if(tvCalculate.text.toString().isEmpty()) {
                tvResult.text ="0"
            }else {
                try {
                    val expression = ExpressionBuilder(tvCalculate.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()

                    if(result == longResult.toDouble()) {
                       tvResult.text == longResult.toString()
                    } else {
                        tvResult.text = result.toString()
                    }

                   }catch (e:Exception) {
                    tvResult.text = "Error"
                }
            }
            tvCalculate.text = ""
        }


}

    private fun append(string: String, canClear: Boolean) {
        if (canClear) {
            tvResult.text = ""
            tvCalculate.append(string)
        } else {
            if (tvResult.text.toString().contains("Error")) {
                tvCalculate.append(string)
                tvResult.text = ""
            } else {
                tvCalculate.append(tvResult.text.toString())
                tvCalculate.append(string)
                tvResult.text = ""
            }
        }
    }
}