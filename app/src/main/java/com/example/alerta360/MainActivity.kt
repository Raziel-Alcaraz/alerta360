package com.example.alerta360

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.app.JobIntentService

import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        var mPrefs = getSharedPreferences("Iustus", Context.MODE_PRIVATE)
        var mString = mPrefs.getString("tag", "1")
        var testo = "No puedes acceder a Iustus."
        if (mString !== "0") {
            testo= "Enviando alerta"
        }
        else{
            testo="Debes registrar tus datos en Iustus para enviar alertas"
        }
        fab.setOnClickListener {
                view ->
              //  sendGetRequest("usr","pass")
            crearcoladetrabajo()
            Snackbar.make(view, testo, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun sendPostRequest(userName:String, password:String) {

        var reqParam = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8")
        reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
        val mURL = URL("https://miticher.com")

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "POST"

            val wr = OutputStreamWriter(getOutputStream());
            wr.write(reqParam);
            wr.flush();

            println("URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response : $response")
                val testo: TextView = findViewById(R.id.testo) as TextView
                testo.text = response
            }
        }
    }
    fun sendGetRequest(userName:String, password:String) {

        var reqParam = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8")
        reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")

        val mURL = URL("https://miticher.com?")
//+reqParam
        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            println("URL : $url")
          //  println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response : $response")
            }
        }
    }

    fun crearcoladetrabajo(){
        val serviceIntent = Intent().apply {
            putExtra("download_url", "UURRLL")
        }
  // private const val RS_JOB_ID = 1000

    }

}
