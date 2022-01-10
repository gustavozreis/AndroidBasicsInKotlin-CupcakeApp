package com.example.cupcake.model

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.cupcake.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {

    // quantidade do pedido
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // sabor do pedido
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // lista de datas possíveis
    //val dateOptions: List<String> = getPickupOptions()
    val dateOptions: MutableList<String> = mutableListOf("")

    // data de retirada
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // preço total
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        // seta ordens iniciais do pedido
        resetOrder()
    }

    // setar quantidade
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    // setar sabor do pedido
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    // setar data de retirada
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    // verificar se o sabor foi escolhido
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    // função para pegar a data do local do usuário
    fun getPickupOptions() {
        // Create a list of dates starting with the current date and the following 3 dates
        //val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        dateOptions.clear()

        // checar se o sabor é 'special flavor'
        if (flavor.value.toString() == "Special Flavor") {
            repeat(4) {
                calendar.add(Calendar.DATE, 1)
                dateOptions.add(formatter.format(calendar.time))

            }
        } else {
            repeat(4) {
                dateOptions.add(formatter.format(calendar.time))
                calendar.add(Calendar.DATE, 1)
            }
        }
    }

    // redefinir MutableLiveData no modelo de visualização
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    // definir preço total
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (dateOptions[0] == date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

}