package com.feranstirman.labo4

import android.net.Uri
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {

    val MOVIES_API_BASEURL = "http://www.ombdapi.com/"
    val TOKEN_API = "e40521ed"

    fun buildSearchUrl(movieName:String):URL {
        val buildUri = Uri.parse(MOVIES_API_BASEURL).buildUpon().appendQueryParameter("apikey",TOKEN_API)
                .appendQueryParameter("t",movieName)
                .build()
        return try {
            URL(buildUri.toString())
        }catch (e:MalformedURLException){
            URL("")
        }
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url:URL):String{
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if(hasInput){
                scanner.next()
            }else{
                ""

            }
        }finally {
            urlConnection.disconnect()
        }
    }
}