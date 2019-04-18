package com.meycon.primecep.repository

import com.meycon.primecep.interfaces.OuvinteDaConsulta
import com.meycon.primecep.models.Endereco


/**
 * Criado por Meycon em 17/04/2019.
 *
 * Interface responsável por armazenar a assinatura do método getEndereco
 *
 */

interface RepositorioEndereco {
    fun getEndereco(cep : String, listener : OuvinteDaConsulta<Endereco?>)
}