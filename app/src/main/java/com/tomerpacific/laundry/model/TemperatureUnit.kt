package com.tomerpacific.laundry.model

enum class TemperatureUnit {
    CELSIUS,
    FAHRENHEIT;

    fun convertFromCelsiusToFahrenheit(celsius: Int): Int {
        return (celsius * 9/5) + 32
    }
}