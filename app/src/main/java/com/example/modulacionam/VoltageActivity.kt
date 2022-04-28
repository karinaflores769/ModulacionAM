package com.example.modulacionam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.modulacionam.databinding.ActivityVoltageBinding

class VoltageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoltageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVoltageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = binding.spnVolt
        val list = resources.getStringArray(R.array.Opc3)
        val adapt = ArrayAdapter(this, R.layout.spn_item, list)
        spinner.adapter = adapt

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (list[p2]){
                    list[1] -> {
                        val intent = Intent(this@VoltageActivity, MainActivity::class.java) //Invoco a la Main Activity
                        startActivity(intent)
                        finish()
                    }
                    list[2] -> {
                        val intent = Intent(this@VoltageActivity, PotenciaActivity::class.java) //Invoco a la Main Activity
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

    fun clickvolt(view: View) {
        with(binding) {
            if (etIm.text.toString().isNotEmpty() && etVbl.text.toString().isNotEmpty()) {
                val indmod = etIm.text.toString().toFloat()
                val voltbl = etVbl.text.toString().toFloat()
                if (indmod == 0f && voltbl == 0f) {
                    Toast.makeText(this@VoltageActivity, getString(R.string.err_cero), Toast.LENGTH_SHORT).show()
                }else if (indmod == 0f){
                    etIm.error = getString(R.string.err_difcero)
                    etIm.requestFocus()
                }else{
                    val rest = voltc(indmod,voltbl)
                    val intent = Intent(this@VoltageActivity, VoltageResultActivity::class.java)

                    val parametros = Bundle() //Creo la caja
                    parametros.putFloat("indmod", indmod) //En la caja meto los valores de entrada
                    parametros.putFloat("volt", voltbl)
                    parametros.putFloat("result", rest)

                    intent.putExtras(parametros) //Al intent le paso la caja
                    startActivity(intent)
                }
            } else {
                if (etIm.text.toString().isEmpty() && etVbl.text.toString().isEmpty()) {
                    Toast.makeText(this@VoltageActivity, getString(R.string.ingrese_val), Toast.LENGTH_SHORT).show()

                } else if (etIm.text.toString().isEmpty()){
                    etIm.error = getString(R.string.req_val)
                    etIm.requestFocus()
                } else {
                    etVbl.error = getString(R.string.req_val)
                    etVbl.requestFocus()
                }
            }
        }
    }
    fun voltc(indmod: Float, voltbl: Float): Float{
        return ((voltbl*2)/indmod)
    }
}