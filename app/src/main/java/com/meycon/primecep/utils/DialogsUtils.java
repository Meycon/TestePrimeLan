package com.meycon.primecep.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Criado por Meycon em 17/04/2019.
 *
 * Classe DialogsUtils, responsável por exibir o Dialog de Loading ao usuário;
 * Foi implementado nessa classe o padrão de projeto chamado Singleton, permitindo criar apenas um
 * único objeto e apenas uma instância. Oferecendo um padrão global.
 *
 */

public class DialogsUtils {

    private static final DialogsUtils instance = new DialogsUtils();
    SweetAlertDialog pDialog;

    public static synchronized DialogsUtils getInstance(){
        return instance;
    }

    public SweetAlertDialog getAlertDialog(Context context){
        if(pDialog == null)
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        return pDialog;
    }

    public void setAlertDialog(SweetAlertDialog pDialog){
        this.pDialog = pDialog;
    }

    public static void exibirDialogCarregandoEscuro(SweetAlertDialog pDialog, String msg, int flag){

        if(flag == 1){
            SweetAlertDialog.DARK_STYLE = true;
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText(msg);
            pDialog.setCancelable(false);
            pDialog.show();
        }else{
            SweetAlertDialog.DARK_STYLE = true;
            pDialog.dismissWithAnimation();
        }

    }


}
