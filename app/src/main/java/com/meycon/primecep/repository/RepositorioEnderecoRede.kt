package com.meycon.primecep.repository

import android.util.Log
import com.meycon.primecep.helpers.MascaraEditText
import com.meycon.primecep.models.Endereco
import com.meycon.primecep.services.ConexaoAPI
import com.meycon.primecep.interfaces.OuvinteDaConsulta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Criado por Meycon em 17/04/2019.
 *
 * Classe responsável por implementar a interface Repositório de Endereço e seu respectivo método
 * para obter o endereço, passando como parãmetro o cep e o Ouvite da Consulta do tipo Endereço;
 *
 */

class RepositorioEnderecoRede : RepositorioEndereco {

    override fun getEndereco(cep : String, listener: OuvinteDaConsulta<Endereco?>) {
        val call = ConexaoAPI.getEndereco(MascaraEditText.unmask(cep))

       call.enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                listener.onSucess(response.body())
            }
            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                listener.onFailured(t.message)
            }
        })



    }

}