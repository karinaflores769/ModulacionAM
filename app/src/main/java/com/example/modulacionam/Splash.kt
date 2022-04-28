package com.example.modulacionam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.concurrent.thread

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //Genero el hilo para que solo se muestre 3 seg
        thread{
            Thread.sleep(3000) //solo se muestra por 3 seg
            val intent = Intent(this, MainActivity::class.java) //Invoco a la Main Activity
            startActivity(intent)
            finish()

        }


    }
}