package com.gogcompany.myapplication.App

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.gogcompany.myapplication.R

class DialogPermission(
    private val context: Context ,
    private val title: String ,
    private val message: String ,
    private val callback: OnBtnDialogAction ,
) {

    lateinit var layout: View;
    var dialog: Dialog? = null;
    lateinit var txtTitle : TextView;
    lateinit var txtMessage : TextView;
    lateinit var btnAccept : Button;
    lateinit var btnCancel : Button;

    interface OnBtnDialogAction{
        fun onAccept();
        fun onCancel();
    }


    fun show(){
        dialog = Dialog(context)
        layout = LayoutInflater.from(context).inflate(R.layout.item_dialog_permission , null);
        cast();
        setViews();
        dialog?.setContentView(layout)
        dialog?.show();
    }


    private fun cast() {
        txtTitle = layout.findViewById(R.id.element_id_layout_dialog_txt_title)
        txtMessage = layout.findViewById(R.id.element_id_layout_dialog_txt_message)
        btnAccept = layout.findViewById(R.id.element_id_layout_dialog_btn_accept)
        btnCancel = layout.findViewById(R.id.element_id_layout_dialog_btn_cancel)
    }


    private fun setViews() {
        txtTitle.text = title;
        txtMessage.text = message;

        btnAccept.setOnClickListener {
            callback.onAccept();
            dismiss();
        }
        btnCancel.setOnClickListener {
            callback.onCancel();
            dismiss();
        }
    }

    private fun dismiss(){
        if (dialog != null){
            dialog?.dismiss();
        }
        dialog = null;
    }
}