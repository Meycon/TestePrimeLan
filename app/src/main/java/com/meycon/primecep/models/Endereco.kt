package com.meycon.primecep.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Criado por Meycon em 17/04/2019.
 *
 * Classe Endereço do tipo Parcelize, notação para criar os objetos Parcelable para transferências
 * entre activities
 *
 */

@Parcelize
data class Endereco(
    var cep: String,
    var logradouro: String,
    var complemento : String,
    var bairro : String,
    var localidade: String,
    var uf: String,
    var unidade : String,
    var ibge : String,
    var gia : String) : Parcelable