package com.adam

data class Mahasiswa(var nrp:String, var nama:String, var umur:Int) {
    fun summary():String = "$nrp - $nama - $umur"
}