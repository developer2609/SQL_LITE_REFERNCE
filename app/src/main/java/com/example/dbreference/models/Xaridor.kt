package com.example.dbreference.models

class Xaridor {


    var id:Int=0
    var name=""
    var number=""
    var address=""

    constructor(id: Int, name: String, number: String, address: String) {
        this.id = id
        this.name = name
        this.number = number
        this.address = address
    }

    constructor(name: String, number: String, address: String) {
        this.name = name.toString()
        this.number = number
        this.address = address
    }

    override fun toString(): String {
        return "Xaridor(id=$id, name='$name', number='$number', address='$address')"
    }


}