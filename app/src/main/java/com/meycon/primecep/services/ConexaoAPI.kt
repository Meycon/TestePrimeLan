package com.meycon.primecep.services

import com.meycon.primecep.utils.Constants
import com.meycon.primecep.models.Endereco
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Criado por Meycon em 17/04/2019.
 *
 * Classe responsável por configurar a conexão do padrão RESTful utilizando a lib retrofit2
 */

object ConexaoAPI {

    var retrofit = Retrofit.Builder()
        .baseUrl(Constants.URL_PRINCIPAL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getEndereco(cep : String) : Call<Endereco> {
        val service = retrofit.create(ServicoEndereco::class.java)
        return service.getEndereco(cep)
    }

}