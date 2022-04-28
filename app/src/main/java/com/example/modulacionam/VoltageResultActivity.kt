package com.example.modulacionam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.modulacionam.databinding.ActivityVoltageResultBinding

class VoltageResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoltageResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoltageResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras //Abro la caja
        val volbl = bundle?.getFloat("volt",0f) //Tomo lo de la caja considerando la llave que escribí antes
        val indmod = bundle?.getFloat("indmod",0f)
        val result = bundle?.getFloat("result", 0f)

        with(binding){
            tvBl.text = getString(R.string.res_vbl, volbl)
            tvIndmod2.text = getString(R.string.res_ind, indmod)
            tvRVolt.text = getString(R.string.res_voltc, result)
        }
    }
}