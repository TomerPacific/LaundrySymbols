package com.tomerpacific.laundry

data class SymbolData(val symbols: List<Symbol>) {

    fun get(index: Int) : Symbol {
        return symbols.get(index)
    }
}

//data class SymbolData(val symbolName: String, val symbolImageSrc : Drawable)