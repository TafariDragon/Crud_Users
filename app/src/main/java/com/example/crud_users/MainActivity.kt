package com.example.crud_users

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_users.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private  lateinit var  binding: ActivityMainBinding
  var pos =-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listaUsers = ArrayList<Usuario>()
        listaUsers.add(Usuario("Tafari",126172))
        listaUsers.add(Usuario("José",36621))
        listaUsers.add(Usuario("Kelly",90028))

        var adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaUsers)
        binding.listaUtilizadores.adapter=adapter

        binding.listaUtilizadores.setOnItemClickListener { parent, view, position, id ->
            binding.inputNome.setText(listaUsers.get(position).nome)
            binding.inputPass.setText(listaUsers.get(position).pass.toString())
            pos=position;

        }

        binding.btnInserir.setOnClickListener {
            if(binding.inputNome.text.trim().isEmpty() || binding.inputPass.text.trim().isEmpty())
            {
                Toast.makeText(this,"Preencha os campos",Toast.LENGTH_SHORT).show()
            }else{
                listaUsers.add(Usuario(binding.inputNome.text.toString(), binding.inputPass.text.toString().toInt()))
                adapter.notifyDataSetChanged()//Esse comando serve para actualizar a ListView
                binding.inputNome.setText("")
                binding.inputPass.setText("")
            }
        }

        binding.btnAlterar.setOnClickListener {
            if(pos>=0)
            {
                listaUsers.get(pos).nome=binding.inputNome.text.toString()
                listaUsers.get(pos).pass=binding.inputPass.text.toString().toInt()
                adapter.notifyDataSetChanged()
                binding.inputNome.setText("")
                binding.inputPass.setText("")
                pos=-1
            }
        }


        binding.btnRemover.setOnClickListener {

            if(pos>=0)
            {
                listaUsers.removeAt(pos)
                adapter.notifyDataSetChanged()
                binding.inputNome.setText("")
                binding.inputPass.setText("")
                pos=-1
            }

        }

        binding.btnDeleteAll.setOnClickListener {
            listaUsers.clear()
            adapter.notifyDataSetChanged()
            binding.inputNome.setText("")
            binding.inputPass.setText("")
            pos=-1
        }
    }
}