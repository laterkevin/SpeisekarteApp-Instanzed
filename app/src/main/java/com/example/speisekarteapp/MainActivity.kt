package com.example.speisekarteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.speisekarteapp.databinding.ActivityMainBinding
import kotlin.math.round

/**
 * Main Activity, dient als Einstiegspunkt für die App
 */
class MainActivity : AppCompatActivity() {

    /* -------------------- Klassen Variablen -------------------- */
    private var bill: Float = 0f
    private val coffee = Drink("Kaffee", 3.95f)
    private val wine = Drink("Wein", 4.20f)
    private val cocktail = Drink("Cocktail", 6.90f)

    /* -------------------- Lifecycle -------------------- */
    /**
     * Lifecycle Funktion, wird aufgerufen wenn Activity erstellt wird
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lege hier die Binding Variable an
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Setze hier die Namen, Preise und Count in der View über die binding Variable
        binding.drink1Name.text = coffee.name
        binding.drink2Name.text = wine.name
        binding.drink3Name.text = cocktail.name

        binding.coffeePrice.text = coffee.price.toString()
        binding.winePrice.text = wine.price.toString()
        binding.cocktailPrice.text = cocktail.price.toString()

        binding.coffeeCount.text = coffee.count.toString()
        binding.wineCount.text = wine.count.toString()
        binding.cocktailCount.text = cocktail.count.toString()
        binding.totalPrice.text = "0.0"

        // implementiere einen onClicklistener für das Kaffee Icon
        binding.coffeeButton.setOnClickListener {
            addToBill(coffee.price)
            coffee.count++
            binding.coffeeCount.text = coffee.count.toString()
            binding.totalPrice.text = bill.toString()
        }

        // implementiere einen onClicklistener für das Wein Icon
        binding.wineButton.setOnClickListener {
            addToBill(wine.price)
            wine.count++
            binding.wineCount.text = wine.count.toString()
            binding.totalPrice.text = bill.toString()
        }

        // implementiere einen onClicklistener für das Cocktail Icon
        binding.cocktailButton.setOnClickListener {
            addToBill(cocktail.price)
            cocktail.count++
            binding.cocktailCount.text = cocktail.count.toString()
            binding.totalPrice.text = bill.toString()
        }

        // implementiere einen onClicListener für den ResetButton
        binding.resetButton.setOnClickListener {
            coffee.count = 0
            wine.count = 0
            cocktail.count = 0
            bill = 0f

            binding.coffeeCount.text = coffee.count.toString()
            binding.wineCount.text = wine.count.toString()
            binding.cocktailCount.text = cocktail.count.toString()
            binding.totalPrice.text = "0.0"
        }
    }

    /**
     * Diese Funktion addiert den Preis zur Rechnung
     */
    private fun addToBill(price: Float?) {
        if (price != null) {
            bill += price
            bill = round(bill * 100) / 100
        }
    }
}
