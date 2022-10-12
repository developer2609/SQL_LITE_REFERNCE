package com.example.dbreference.db

import com.example.dbreference.models.Buyurtma
import com.example.dbreference.models.Sotuvchi
import com.example.dbreference.models.Xaridor

interface MyDbInterface {

    fun addSalesman(sotuvchi:Sotuvchi)

    fun addCustomer(xaridor: Xaridor)


    fun addOlder(buyurtma: Buyurtma)
    fun getAllSalesman():List<Sotuvchi>
    fun getAllCustomer():List<Xaridor>
     fun getAllOrders():List<Buyurtma>


     fun getSalesmanById(id:Int):Sotuvchi
     fun getCustomerById(id:Int):Xaridor
}