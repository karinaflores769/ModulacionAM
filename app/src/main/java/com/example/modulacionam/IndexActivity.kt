package com.example.modulacionam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.modulacionam.databinding.ActivityIndexBinding

class IndexActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIndexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIndexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras //Abro la caja
        val volmax = bundle?.getFloat("vmax",0f) //Tomo lo de la caja considerando la llave que escribí antes
        val volmin = bundle?.getFloat("vmin",0f)
        val result = bundle?.getFloat("result", 0f)


        with(binding){
            tvDVM.text = getString(R.string.res_vmax, volmax)
            tvDVm.text = getString(R.string.res_vmin, volmin)
            tvRIndex.text = getString(R.string.res_index, result)
        }


        //Toast.makeText(this, "Voltaje máximo: $volmax Voltaje mínimo: $volmin", Toast.LENGTH_SHORT).show()

    }


}