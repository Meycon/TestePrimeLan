package com.meycon.primecep.services

import com.meycon.primecep.models.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Criado por Meycon em 17/04/2019.
 *
 * Interface responsável por armazenar as assinaturas dos métodos a ser utilizaada no Retrofit2, podendo ser GET, POST, DELETE;
 *
 */

interface ServicoEndereco {

    @GET("{cep}/json")
    fun getEndereco(@Path("cep") cep : String): Call<Endereco>
}