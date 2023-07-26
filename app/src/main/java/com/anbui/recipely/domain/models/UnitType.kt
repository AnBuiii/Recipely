package com.anbui.recipely.domain.models

sealed class UnitType(private val unitString: String){
    object Unit: UnitType("")
    object Can: UnitType("can")
    object Pgk: UnitType("pkg.")
    object Bag: UnitType("Bag")
    object Btl: UnitType("btl.")
    object Box: UnitType("box")
    object Block: UnitType("block")
    object Jar: UnitType("jar")
    object Oz: UnitType("oz.")
    object Lb: UnitType("lb.")
    object Cup: UnitType("cup")
    object Pt: UnitType("pt.")
    object Qt: UnitType("qt.")
    object Gal: UnitType("gal.")

    override fun toString(): String {
        return this.unitString
    }
}

