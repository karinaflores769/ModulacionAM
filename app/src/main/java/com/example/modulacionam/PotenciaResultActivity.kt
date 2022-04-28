package com.example.modulacionam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.modulacionam.databinding.ActivityPotenciaResultBinding

class PotenciaResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPotenciaResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPotenciaResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras //Abro la caja
        val indmod = bundle?.getFloat("indmod",0f) //Tomo lo de la caja considerando la llave que escribí antes
        val potc = bundle?.getFloat("potc",0f)
        val result = bundle?.getFloat("result", 0f)

        with(binding){
            tvIndmod.text = getString(R.string.res_ind, indmod)
            tvPotc.text = getString(R.string.res_potc, potc)
            tvRPot.text = getString(R.string.res_potam, result)
        }
    }
}