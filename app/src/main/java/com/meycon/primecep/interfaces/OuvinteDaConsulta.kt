package com.meycon.primecep.interfaces

/**
 * Criado por Meycon em 17/04/2019.
 *
 * Interface responsável ouvir os dados da Consulta
 *
 */

interface OuvinteDaConsulta<T> {

    fun onSucess(data : T)
    fun onFailured(error: String?)
}