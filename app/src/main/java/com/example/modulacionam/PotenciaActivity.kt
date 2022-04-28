package com.example.modulacionam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.modulacionam.databinding.ActivityPotenciaBinding
import kotlin.math.pow

class PotenciaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPotenciaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPotenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = binding.spnPot
        val list = resources.getStringArray(R.array.Opc2)
        val adapt = ArrayAdapter(this, R.layout.spn_item, list)
        spinner.adapter = adapt

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (list[p2]){
                    list[1] -> {
                        val intent = Intent(this@PotenciaActivity, MainActivity::class.java) //Invoco a la Main Activity
                        startActivity(intent)
                        finish()
                    }
                    list[2] -> {
                        val intent = Intent(this@PotenciaActivity, VoltageActivity::class.java) //Invoco a la Main Activity
                        startActivity(intent)
                        finish()
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }
        }
    }

    fun clickpot(view: View) {
        with(binding) {
            if (etMi.text.toString().isNotEmpty() && etPc.text.toString().isNotEmpty()) {
                val indmod = etMi.text.toString().toFloat()
                val potc = etPc.text.toString().toFloat()
                val resp = potAM(potc,indmod)
                val intent = Intent(this@PotenciaActivity, PotenciaResultActivity::class.java)

                val parametros = Bundle() //Creo la caja
                parametros.putFloat("indmod", indmod) //En la caja meto los valores de entrada
                parametros.putFloat("potc", potc)
                parametros.putFloat("result", resp)

                intent.putExtras(parametros) //Al intent le paso la caja
                startActivity(intent)
            } else {
                if (etMi.text.toString().isEmpty() && etPc.text.toString().isEmpty()) {
                    Toast.makeText(this@PotenciaActivity, getString(R.string.ingrese_val), Toast.LENGTH_SHORT).show()

                } else if (etMi.text.toString().isEmpty()){
                    etMi.error = getString(R.string.req_val)
                    etMi.requestFocus()
                } else {
                    etPc.error = getString(R.string.req_val)
                    etPc.requestFocus()
                }
            }
        }
    }

    fun potAM(potc:Float, ind:Float) : Float{
        return potc*(1+((ind.pow(2))/2))
    }

}