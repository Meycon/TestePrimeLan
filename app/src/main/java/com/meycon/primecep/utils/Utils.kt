package com.meycon.primecep.utils

import android.app.Activity
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.net.ConnectivityManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import es.dmoral.toasty.Toasty


/**
 * Criado por Meycon em 17/04/2019.
 */

object Utils {

    //Esconder o teclado
    fun hideKeyboard(activity: Activity) {
        val view = activity.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    //Verifica conexão com a internet
    fun isConnectedInternet(activity: AppCompatActivity): Boolean {
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    /* Vibrar o Celular */
    fun vibrarCelular(context: Context) {
        val vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (vibrator != null) {
                vibrator.vibrate(VibrationEffect.createOneShot(150, 10))
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
            }
        } else {
            if (vibrator != null) {
                val mVibratePattern = longArrayOf(0, 400, 800, 600, 800, 800, 800, 1000)
                vibrator.vibrate(300)
                //  vibrator.vibrate(mVibratePattern,3);
            }
        }
    }

    /* Toast */
    fun exibirToast(context: Context, msg: String, modo: MODO_TOAST) {
        when (modo) {
            Utils.MODO_TOAST.SUCESSO -> Toasty.success(context, msg, Toast.LENGTH_SHORT, true).show()
            Utils.MODO_TOAST.ERRO -> Toasty.error(context, msg, Toast.LENGTH_SHORT, true).show()
            Utils.MODO_TOAST.INFO -> Toasty.info(context, msg, Toast.LENGTH_SHORT, true).show()
            Utils.MODO_TOAST.WARNING -> Toasty.warning(context, msg, Toast.LENGTH_SHORT, true).show()
            Utils.MODO_TOAST.NORMAL -> Toasty.normal(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    /* Modo do Toast */
    enum class MODO_TOAST {
        SUCESSO, ERRO, INFO, NORMAL, WARNING
    }

    /* Verificar se o Cep é valido */
    fun isCepValid(cep: String?): Boolean {
        return cep != null && cep.length == 8
    }

}