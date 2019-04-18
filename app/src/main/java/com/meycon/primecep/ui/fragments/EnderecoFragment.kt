package com.meycon.primecep.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_endereco.*
import com.meycon.primecep.R
import com.meycon.primecep.models.Endereco

private const val ARG_ENDERECO = "endereco"

/**
 * Criado por Meycon em 17/04/2019.
 *
 */

class EnderecoFragment : Fragment() {

    private var endereco: Endereco? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle -> endereco = bundle.getParcelable(ARG_ENDERECO)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_endereco, container, false)
    }

    override fun onStart() {
        super.onStart()
        bindData(endereco)
    }

    fun bindData(endereco: Endereco?) {
        if (endereco != null) {
            configureDataInViews(endereco)
        } else {
            configureEmptyState()
        }
    }

    private fun configureDataInViews(endereco : Endereco) {
        text_title.text = getString(R.string.informacoes_endereco)
        grupo.visibility = View.VISIBLE
        logradouro.text = endereco.logradouro
        localidade.text = endereco.localidade
        uf.text = endereco.uf
        bairro.text = endereco.bairro
        cep.text = endereco.cep

        cardViewEndereco.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${logradouro.text}, ${localidade.text}, ${uf.text}"))
            startActivity(intent)
        }
    }

    private fun configureEmptyState() {
        text_title.text = getString(R.string.sem_resultados_busca)
        grupo.visibility = View.GONE
        cardViewEndereco.setOnClickListener{}
    }

    companion object {
        @JvmStatic
        fun newInstance(endereco: Endereco?) =
            EnderecoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ENDERECO, endereco)
                }
            }
    }
}
