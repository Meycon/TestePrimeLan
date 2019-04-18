package com.meycon.primecep.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.meycon.primecep.interfaces.OuvinteDaConsulta
import com.meycon.primecep.models.Endereco
import com.meycon.primecep.repository.RepositorioEndereco

/**
 * Criado por Meycon em 17/04/2019.
 *
 * Classe Endereço View Model, responsável por manter os dados em memória enquanto há uma outra activity ou fragment está vinculado a ela;
 * Ela é responsável também em conjunto com a API do LiveData, pelas invocações da camada de modelo, de domínio e API de busca de dados;
 *
 */

class EnderecoViewModel(private val repository: RepositorioEndereco) : ViewModel() {

    private var enderecoLiveData  = MutableLiveData<Endereco>()

    fun getEndereco() : MutableLiveData<Endereco> = enderecoLiveData

    fun consultar(cep: String)  {
        enderecoLiveData.value.let { endereco -> if (cep == endereco?.cep) return }

        repository.getEndereco(cep, object : OuvinteDaConsulta<Endereco?> {
           override fun onSucess(data : Endereco?)  {
               if (data?.logradouro != null)
                    enderecoLiveData.value = data
               else
                   enderecoLiveData.value = null
           }

            override fun onFailured(error: String?) {
                enderecoLiveData.value = null

            }
        })
    }
}