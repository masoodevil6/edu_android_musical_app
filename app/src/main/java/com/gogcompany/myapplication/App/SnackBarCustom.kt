package com.gogcompany.myapplication.App

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.gogcompany.myapplication.R
import com.google.android.material.snackbar.Snackbar

class SnackBarCustom(
    private val context: Context ,
    private val view: View
) {

    private var snackBar: Snackbar? = null;
    lateinit var title: String;
    lateinit var msg: String;

    private var btnFirst:ModelSnack?= null;
    private var btnSecond:ModelSnack?= null;


    fun setTitle(title: String): SnackBarCustom{
        this.title=title;
        return this;
    }

    fun setMessage(msg: String): SnackBarCustom{
        this.msg=msg;
        return this;
    }

    fun setOnFirstClick(title: String , runnable: Runnable): SnackBarCustom{
        btnFirst = ModelSnack();
        btnFirst!!.setTitle(title)
        btnFirst!!.setRunnable(runnable)
        return this;
    }

    fun setOnSecondClick(title: String , runnable: Runnable): SnackBarCustom{
        btnSecond = ModelSnack();
        btnSecond!!.setTitle(title)
        btnSecond!!.setRunnable(runnable)
        return this;
    }


    @SuppressLint("RestrictedApi")
    fun show(duration: Int = Snackbar.LENGTH_INDEFINITE){
        snackBar = Snackbar.make(view , "" , duration);

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT);

        val layoutSnack = snackBar!!.view as Snackbar.SnackbarLayout;
        layoutSnack.setPadding(0 , 0 , 0 , 0);

        val layoutView = LayoutInflater.from(context).inflate(R.layout.layout_snack , null)
        layoutSnack.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val txtTitle: TextView = layoutView.findViewById(R.id.element_id_layout_snack_title)
        val txtMsg: TextView = layoutView.findViewById(R.id.element_id_layout_snack_msg)
        val buttonFirst: Button = layoutView.findViewById(R.id.element_id_layout_btm_first)
        val buttonSecond: Button = layoutView.findViewById(R.id.element_id_layout_btm_second)

        txtTitle.text = title;
        txtMsg.text = msg;

        if (btnFirst!= null){
            if (btnFirst!!.getTitle() != null){
                buttonFirst.text =btnFirst!!.getTitle();
            }
        }
        else{
            buttonFirst.visibility = View.GONE
        }

        if (btnSecond!= null){
            if (btnSecond!!.getTitle() != null){
                buttonSecond.text =btnSecond!!.getTitle();
            }
        }
        else{
            buttonSecond.visibility = View.GONE
        }


        val mess = txtMsg.text.toString().trim();
        if (mess.isEmpty()){
            txtMsg.visibility = View.GONE;
        }

        layoutSnack.addView(layoutView , params)

        buttonFirst.setOnClickListener {
            if (btnFirst!= null){
                if (btnFirst!!.getRunnable() != null){
                    btnFirst!!.getRunnable().run();
                }
            }

            this.dismiss();
        }

        buttonSecond.setOnClickListener {
            if (btnSecond!= null){
                if (btnSecond!!.getRunnable() != null){
                    btnSecond!!.getRunnable().run();
                }
            }

            this.dismiss();
        }

        snackBar!!.show();
    }

    private fun dismiss(){
        if (snackBar != null){
            snackBar!!.dismiss();
        }
        snackBar = null;
    }


    class ModelSnack{

        var btnTitle:String="";
        lateinit var btnRunnable:Runnable;


        fun setTitle(btnTitle: String){
            this.btnTitle = btnTitle
        }
        fun getTitle():String {
            return this.btnTitle;
        }


        fun setRunnable(btnRunnable: Runnable){
            this.btnRunnable = btnRunnable
        }

        fun getRunnable():Runnable{
            return this.btnRunnable;
        }
    }



}