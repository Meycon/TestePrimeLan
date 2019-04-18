package com.meycon.primecep.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meycon.primecep.repository.RepositorioEndereco

/**
 * Criado por Meycon em 17/04/2019.
 *
 * Classe responsável por implementar a Factory que criará o ViewModel de Endereço a partir do parâmetro .class
 * Quando nós utilizamos com o método get(), o méotod create dessa classe é invocado, retornando assim a instância
 * do ViewModel do Endereço
 */

class FabricaViewModelEndereco(private val repository: RepositorioEndereco) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = EnderecoViewModel(repository) as T
}