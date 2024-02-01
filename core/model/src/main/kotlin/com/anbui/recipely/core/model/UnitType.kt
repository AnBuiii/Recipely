package com.anbui.recipely.core.model

import kotlinx.serialization.Serializable

@Serializable
sealed class UnitType(val unitString: String) {
    object Unit : UnitType("")
    object Can : UnitType("can")
    object Pgk : UnitType("pkg.")
    object Bag : UnitType("Bag")
    object Btl : UnitType("btl.")
    object Box : UnitType("box")
    object Block : UnitType("block")
    object Jar : UnitType("jar")
    object Oz : UnitType("oz.")
    object Lb : UnitType("lb.")
    object Cup : UnitType("cup")
    object Pt : UnitType("pt.")
    object Qt : UnitType("qt.")
    object Gal : UnitType("gal.")

    override fun toString(): String {
        return this.unitString
    }

    companion object {

        fun indices(): List<UnitType> {
            return listOf(Unit, Can, Pgk, Bag, Btl, Box, Block, Jar, Oz, Lb, Cup, Pt, Qt, Gal)
        }

        fun String.toUnitType(): UnitType {
            return when (this) {
                Unit.unitString -> Unit
                Can.unitString -> Can
                Pgk.unitString -> Pgk
                Bag.unitString -> Bag
                Btl.unitString -> Btl
                Box.unitString -> Box
                Block.unitString -> Block
                Jar.unitString -> Unit
                Oz.unitString -> Oz
                Lb.unitString -> Lb
                Cup.unitString -> Cup
                Pt.unitString -> Pt
                Qt.unitString -> Qt
                Gal.unitString -> Gal

                else -> Unit
            }
        }
    }
}
