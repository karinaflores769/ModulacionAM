package com.example.modulacionam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.modulacionam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = binding.spn
        val list = resources.getStringArray(R.array.Opc)
        val adapt = ArrayAdapter(this, R.layout.spn_item, list)
        spinner.adapter = adapt

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (list[p2]){
                    list[1] -> {
                        val intent = Intent(this@MainActivity, PotenciaActivity::class.java) //Invoco a la Main Activity
                        startActivity(intent)
                        finish()
                    }
                    list[2] -> {
                        val intent = Intent(this@MainActivity, VoltageActivity::class.java) //Invoco a la Main Activity
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

    fun clickim(view: View) {
        with(binding) {
            if (etVmin.text.toString().isNotEmpty() && etVmax.text.toString().isNotEmpty()) {
                val vmax = etVmax.text.toString().toFloat()
                val vmin = etVmin.text.toString().toFloat()
                if (vmin>vmax) {
                    etVmax.error = getString(R.string.err_maxmin)
                    etVmax.requestFocus()
                }else if (vmax == 0f && vmin == 0f){
                    Toast.makeText(this@MainActivity, getString(R.string.err_cero), Toast.LENGTH_SHORT).show()
                }else{
                    val rest = indiceMod(vmax,vmin)
                    val intent = Intent(this@MainActivity, IndexActivity::class.java)

                    val parametros = Bundle() //Creo la caja
                    parametros.putFloat("vmax", vmax) //En la caja meto los valores de entrada
                    parametros.putFloat("vmin", vmin)
                    parametros.putFloat("result", rest)

                    intent.putExtras(parametros) //Al intent le paso la caja
                    startActivity(intent)
                }
            } else {
                if (etVmin.text.toString().isEmpty() && etVmax.text.toString().isEmpty()) {
                    Toast.makeText(this@MainActivity, getString(R.string.ingrese_val), Toast.LENGTH_SHORT).show()

                } else if (etVmin.text.toString().isEmpty()){
                    etVmin.error = getString(R.string.req_val)
                    etVmin.requestFocus()
                } else {
                    etVmax.error = getString(R.string.req_val)
                    etVmax.requestFocus()
                }
            }
        }
    }

    fun indiceMod(vmax: Float, vmin: Float): Float{
        return (vmax-vmin)/(vmax+vmin)
    }



}