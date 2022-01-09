package com.example.cupcake.model

import android.icu.number.IntegerWidth
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.text.NumberFormat
import java.util.*
import java.util.Calendar
import java.util.Locale

class OrderViewModel: ViewModel() {

    // quantidade do pedido
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // sabor do pedido
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // lista de datas possíveis
    val dateOptions:List<String> = getPickupOptions()

    // data de retirada
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // preço total
    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    init {
        // seta ordens iniciais do pedido
        resetOrder()
    }

    // setar quantidade
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    // setar sabor do pedido
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    // setar data de retirada
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

    // verificar se o sabor foi escolhido
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    // função para pegar a data do local do usuário
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Create a list of dates starting with the current date and the following 3 dates
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    // redefinir MutableLiveData no modelo de visualização
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

}