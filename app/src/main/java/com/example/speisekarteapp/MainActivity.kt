package com.example.speisekarteapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.speisekarteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var bill: Float = 0f
    val menuMap: Map<String, Float> = hashMapOf("Kaffee" to 3f, "Cola" to 2.5f, "Tee" to 2f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var coffeeCount: Int = 0
        var cokeCount: Int = 0
        var teaCount: Int = 0

        val coffePrice = menuMap["Kaffee"]
        val cokePrice = menuMap["Cola"]
        val teaPrice = menuMap["Tee"]

        binding.cofPrice = coffePrice.toString()
        binding.cokPrice = cokePrice.toString()
        binding.tPrice = teaPrice.toString()

        binding.coffeeBtn.setOnClickListener(
            View.OnClickListener {
                addToBill(coffePrice)
                coffeeCount++
                binding.anzahlKaffeeView.text = coffeeCount.toString()
                binding.total = ""
            }
        )
        binding.teaBtn.setOnClickListener(
            View.OnClickListener {
                addToBill(teaPrice)
                teaCount++
                binding.anzahlTeeView.text = teaCount.toString()
                binding.total = ""
            }
        )
        binding.cokeBtn.setOnClickListener(
            View.OnClickListener {
                addToBill(cokePrice)
                cokeCount++
                binding.cokeCount = cokeCount.toString()
                binding.total = ""
            }
        )

        binding.billBtn.setOnClickListener(
            View.OnClickListener {
                binding.total = bill.toString()
            }
        )

        binding.newBtn.setOnClickListener(
            View.OnClickListener {
                cokeCount = 0
                coffeeCount = 0
                teaCount = 0
                bill = 0F

                binding.total = ""
                binding.coffeeCount = ""
                binding.teaCount = ""
                binding.cokeCount = ""
            }
        )
    }

    fun addToBill(price: Float?) {
        if (price != null) {
            bill += price
        }
        println(bill)
    }
}
