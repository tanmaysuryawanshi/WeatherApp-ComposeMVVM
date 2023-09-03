package com.tanmaysuryawanshi.weatherforecastmvvm.utils

import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(timeStamp:Int):String{

    val sdf=SimpleDateFormat("E, d MMM")
    val date=Date(timeStamp.toLong()*1000)
    return sdf.format(date)
}
fun formatDay(timeStamp:Int):String{

    val sdf=SimpleDateFormat("E")
    val date=Date(timeStamp.toLong()*1000)
    return sdf.format(date)
}

fun formatDecimals(num:Double):String{

    return "%.0f".format(num)
}