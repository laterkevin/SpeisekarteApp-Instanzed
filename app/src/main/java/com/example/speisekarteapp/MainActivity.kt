package com.example.speisekarteapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.speisekarteapp.databinding.ActivityMainBinding
import kotlin.math.round

/**
 * Main Activity, dient als Einstiegspunkt für die App
 */
val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    /* -------------------- Klassen Variablen -------------------- */
    private var bill: Float = 0f
    private val drink1 = Drink("Kaffee", 3.95f)
    private val drink2 = Drink("Wein", 4.20f)
    private val drink3 = Drink("Cocktail", 6.90f)

    /* -------------------- Lifecycle -------------------- */
    /**
     * Lifecycle Funktion, wird aufgerufen wenn Activity erstellt wird
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lege hier die Binding Variable an
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Setze hier die Namen, Preise und Count in der View über die binding Variable
        binding.drink1Name.text = drink1.name
        binding.drink2Name.text = drink2.name
        binding.drink3Name.text = drink3.name

        binding.drink1Price.text = "%.2f".format(drink1.price)
        binding.drink2Price.text = "%.2f".format(drink2.price)
        binding.drink3Price.text = "%.2f".format(drink3.price)

        binding.drink1Count.text = drink1.count.toString()
        binding.drink2Count.text = drink2.count.toString()
        binding.drink3Count.text = drink3.count.toString()
        binding.totalPrice.text = "0.0"

        // implementiere einen onClicklistener für das Kaffee Icon
        binding.drink1Button.setOnClickListener {
            try {
                addToBill(drink1.price)
                drink1.count++
                binding.drink1Count.text = drink1.count.toString()
                binding.totalPrice.text = bill.toString()
            } catch (ex: Exception) {
                Log.e(TAG, "Something went wrong: $ex")
            }

        }

        // implementiere einen onClicklistener für das Wein Icon
        binding.drink2Button.setOnClickListener {
            try {
                addToBill(drink2.price)
                drink2.count++
                binding.drink2Count.text = drink2.count.toString()
                binding.totalPrice.text = bill.toString()
            } catch (ex: Exception) {
                Log.e(TAG, "Something went wrong: $ex")
            }

        }

        // implementiere einen onClicklistener für das Cocktail Icon
        binding.drink3Button.setOnClickListener {
            try {
                addToBill(drink3.price)
                drink3.count++
                binding.drink3Count.text = drink3.count.toString()
                binding.totalPrice.text = bill.toString()
            } catch (ex: Exception) {
                Log.e(TAG, "Something went wrong: $ex")
            }
        }

        // implementiere einen onClicListener für den ResetButton
        binding.resetButton.setOnClickListener {
            drink1.count = 0
            drink2.count = 0
            drink3.count = 0
            bill = 0f

            binding.drink1Count.text = drink1.count.toString()
            binding.drink2Count.text = drink2.count.toString()
            binding.drink3Count.text = drink3.count.toString()
            binding.totalPrice.text = "0.0"
        }

        if (savedInstanceState != null) {
            bill = savedInstanceState.getFloat("Bill", 0F)
            drink1.count = savedInstanceState.getInt("drink1", 0)
            drink2.count = savedInstanceState.getInt("drink2", 0)
            drink3.count = savedInstanceState.getInt("drink3", 0)
            binding.totalPrice.text = bill.toString()
            binding.drink1Count.text = drink1.count.toString()
            binding.drink2Count.text = drink2.count.toString()
            binding.drink3Count.text = drink3.count.toString()
        }
    }

    //Gibt im Log DC für Instanzen aus
    override fun onStart() {
        super.onStart()
        Log.e(TAG, "Lifecycle onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "Lifecycle onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "Lifecycle onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "Lifecycle onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Lifecycle onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "Lifecycle onDestroy")
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
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "Lifecycle onSaveInstanceState called")

        outState.putFloat("Bill", bill)
        outState.putInt("drink1", drink1.count)
        outState.putInt("drink2", drink2.count)
        outState.putInt("drink3", drink3.count)
    }
}
