package com.example.speisekarteapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.speisekarteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var bill: Float = 0f
    val menueList: MutableList<Drink> = mutableListOf(
        Drink("Kaffe", 3f),
        Drink("Tea", 2f),
        Drink("Cola", 2.5f),
    )

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

        val coffePrice = menueList[0].price
        val cokePrice = menueList[1].price
        val teaPrice = menueList[2].price

        // setze die Preise in die vorgesehene View für die Gertänke über die binding Variable

        binding.coffeePrice = coffePrice.toString()
        binding.cokePrice = cokePrice.toString()
        binding.teaPrice = teaPrice.toString()

        // implementiere einen onClicklistener für das Kaffe Icon
        binding.coffeButton.setOnClickListener(
            View.OnClickListener {
                addToBill(coffePrice)
                coffeeCount++
                binding.coffeeCount = coffeeCount.toString()
                binding.total = ""
            }
        )

        // implementiere einen onClicklistener für das Tee Icon
        binding.teaButton.setOnClickListener(
            View.OnClickListener {
                addToBill(teaPrice)
                teaCount++
                binding.teaCount = teaCount.toString()
                binding.total = ""
            }
        )

        // implementiere einen onClicklistener für das Cola Icon
        binding.cokeButton.setOnClickListener(
            View.OnClickListener {
                addToBill(cokePrice)
                cokeCount++
                binding.cokeCount = cokeCount.toString()
                binding.total = ""
            }
        )

        // implementiere einen onClickListener für den Gesamtpreis
        binding.billButton.setOnClickListener(
            View.OnClickListener {
                binding.total = bill.toString()
            }
        )

        // implementiere einen onClicListener für neue Kunden
        binding.newButton.setOnClickListener(
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
