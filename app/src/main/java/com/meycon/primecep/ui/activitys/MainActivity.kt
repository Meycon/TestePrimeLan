package com.meycon.primecep.ui.activitys

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.meycon.primecep.R
import com.meycon.primecep.helpers.MascaraEditText
import com.meycon.primecep.models.Endereco
import com.meycon.primecep.repository.RepositorioEnderecoRede
import com.meycon.primecep.ui.fragments.EnderecoFragment
import com.meycon.primecep.utils.Utils
import com.meycon.primecep.viewmodels.EnderecoViewModel
import com.meycon.primecep.viewmodels.FabricaViewModelEndereco
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_endereco.*


/**
 * Criado por Meycon em 17/04/2019.
 *
 */

class MainActivity : AppCompatActivity() {

    private var cepMask: TextWatcher? = null
    private var pDialog: SweetAlertDialog? = null
    private val fragmentManager = supportFragmentManager
    private var enderecoFragment: EnderecoFragment? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurando a Toolbar Principal
        val toolbarPrincipal = findViewById(R.id.toolbarPrincipal) as Toolbar
        setSupportActionBar(toolbarPrincipal)
        toolbarPrincipal.setContentInsetStartWithNavigation(0)

        // Toolbar Customizada
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true)
            actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            val customView = layoutInflater.inflate(R.layout.logo_toolbar, null)
            actionBar.setCustomView(customView)

        }

        val enderecoViewModelFactory = FabricaViewModelEndereco(RepositorioEnderecoRede())
        val viewenderecoViewModel = ViewModelProviders.of(this, enderecoViewModelFactory).get(EnderecoViewModel::class.java)

        viewenderecoViewModel.getEndereco().observe(this, Observer<Endereco> { endereco ->
            SweetAlertDialog.DARK_STYLE = true
            pDialog?.dismissWithAnimation()
            if (endereco == null) {
                cep?.text = "00000-000"
                exibirSnackBar()
            }
            Utils.hideKeyboard(this)
            exibicaoEndereco(endereco)
        })

        btn_cep.setOnClickListener {
            if (!et_cep.text.isNullOrEmpty()) {
                if (et_cep.text.length < 9) {
                    et_cep.error = getString(R.string.campo_corretamente)
                    et_cep.requestFocus()
                } else if (et_cep?.text.toString() != cep?.text.toString()) {

                    if (Utils.isConnectedInternet(this)) {
                        viewenderecoViewModel.consultar(et_cep.text.toString())
                        SweetAlertDialog.DARK_STYLE = true
                        pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        pDialog!!.setTitleText("Carregando")
                        pDialog!!.setCancelable(false)
                        pDialog!!.getProgressHelper().barColor = Color.parseColor("#A5DC86")
                        if (!pDialog!!.isShowing)
                            pDialog!!.show()
                    }else{
                        Utils.exibirToast(this,"Sem conexão com a Internet",Utils.MODO_TOAST.WARNING)
                    }

                }
            } else {
                et_cep.error = getString(R.string.campo_obrigatorio)
                et_cep.requestFocus()
            }
        }

        txtCep.visibility = View.INVISIBLE
        val observableCep = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (et_cep.text.toString().isNotEmpty()) {
                    txtCep.visibility = View.VISIBLE
                } else {
                    txtCep.visibility = View.INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }
        cepMask = MascaraEditText.insert("#####-###", et_cep)
        et_cep.addTextChangedListener(observableCep)
        et_cep.addTextChangedListener(cepMask)


    }

    private fun exibicaoEndereco(endereco: Endereco?) {
        if (enderecoFragment == null) {
            val transaction = fragmentManager.beginTransaction()
            enderecoFragment = EnderecoFragment.newInstance(endereco)
            transaction.replace(R.id.fragment_endereco, enderecoFragment!!)
            transaction.commit()
        } else {
            enderecoFragment!!.bindData(endereco)
        }
    }

    private fun exibirSnackBar() {
        Snackbar.make(conteudo_principal, getString(R.string.nao_encontrados), Snackbar.LENGTH_LONG).setAction(getString(R.string.tentar_novamente)) { btn_cep.performClick() }.show()
    }
}
