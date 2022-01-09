package com.example.cupcake.model

import android.icu.number.IntegerWidth
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel: ViewModel() {

    // quantidade do pedido
    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity
    // setar quantidade
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    // sabor do pedido
    private val _flavor = MutableLiveData<String>("")
    val flavor: LiveData<String> = _flavor
    // setar sabor do pedido
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    // data de retirada
    private val _date = MutableLiveData<String>("")
    val pickup: LiveData<String> = _date
    // setar data de retirada
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

    // preço total
    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price

    // verificar se o sabor foi escolhido
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

}