package com.gogcompany.myapplication.Fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.DataSource
import com.gogcompany.myapplication.MainActivity
import com.gogcompany.myapplication.R

class LoginFragment() : Fragment() {

    lateinit var layout: View;
    lateinit var btnToggleLogin: ToggleButton;
    lateinit var btnToggleRegister: ToggleButton;
    lateinit var cardRegister: LinearLayout;
    lateinit var cardLogin: LinearLayout;
    lateinit var btnRegister: Button;
    lateinit var btnLogin: Button;
    lateinit var edtUserNameRegister: EditText;
    lateinit var edtUserFamilyRegister: EditText;
    lateinit var edtUserEmailRegister: EditText;
    lateinit var edtUserPasswordRegister: EditText;
    lateinit var edtUserEmailLogin: EditText;
    lateinit var edtUserPasswordLogin: EditText;

    lateinit var dataSource: DataSource;
    lateinit var requestQueue: RequestQueue;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragmnet_login , container , false);
        cast();
        setViews()
        return layout;
    }

    private fun cast() {
        btnToggleLogin = layout.findViewById(R.id.element_id_login_fragment_btn_login_card);
        btnToggleRegister = layout.findViewById(R.id.element_id_login_fragment_btn_register_card);

        cardRegister = layout.findViewById(R.id.element_id_login_fragment_linear_form_register);
        btnRegister = layout.findViewById(R.id.element_id_login_fragment_btn_register);
        edtUserNameRegister = layout.findViewById(R.id.element_id_login_fragment_edit_user_name_register)
        edtUserFamilyRegister = layout.findViewById(R.id.element_id_login_fragment_edit_user_family_register)
        edtUserEmailRegister = layout.findViewById(R.id.element_id_login_fragment_edit_user_email_register)
        edtUserPasswordRegister = layout.findViewById(R.id.element_id_login_fragment_edit_user_password_register)

        cardLogin = layout.findViewById(R.id.element_id_login_fragment_linear_form_login);
        btnLogin = layout.findViewById(R.id.element_id_login_fragment_btn_login);
        edtUserEmailLogin = layout.findViewById(R.id.element_id_login_fragment_edit_user_email_login)
        edtUserPasswordLogin = layout.findViewById(R.id.element_id_login_fragment_edit_user_password_login)

        dataSource = DataSource();
        requestQueue = Volley.newRequestQueue(Base.activitySplash);
    }

    private fun setViews() {

        btnToggleLogin.setOnClickListener {
            btnToggleRegister.isChecked = false;
            btnToggleLogin.isChecked = true;

            cardRegister.visibility = View.GONE;
            cardLogin.visibility = View.VISIBLE;

            btnRegister.visibility = View.GONE;
            btnLogin.visibility = View.VISIBLE;
        }


        btnToggleRegister.setOnClickListener {
            btnToggleLogin.isChecked = false;
            btnToggleRegister.isChecked = true;

            cardRegister.visibility = View.VISIBLE
            cardLogin.visibility = View.GONE

            btnRegister.visibility = View.VISIBLE;
            btnLogin.visibility = View.GONE;
        }



        btnRegister.setOnClickListener{
            register();
        }

        btnLogin.setOnClickListener{
            login();
        }
    }


    private fun register(){
        val userName = edtUserNameRegister.text.toString();
        val userFamily = edtUserFamilyRegister.text.toString();
        val userEmail = edtUserEmailRegister.text.toString();
        val userPassword = edtUserPasswordRegister.text.toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(Base.activitySplash , "فیلد نام را پر کنید" , Toast.LENGTH_LONG) .show();
        }
        else if (TextUtils.isEmpty(userFamily)){
            Toast.makeText(Base.activitySplash , "فیلد نام خانوادگی را پر کنید" , Toast.LENGTH_LONG) .show();
        }
        else if  (TextUtils.isEmpty(userEmail)){
            Toast.makeText(Base.activitySplash , "فیلد ایمیل را پر کنید" , Toast.LENGTH_LONG) .show();
        }
        else if  (TextUtils.isEmpty(userPassword)){
            Toast.makeText(Base.activitySplash , "فیلد رمز عبور را پر کنید" , Toast.LENGTH_LONG) .show();
        }
        else{
            dataSource.registerClient(requestQueue , Base.activitySplash , userName , userFamily , userEmail , userPassword, object: DataSource.OnFinishRegisterOrLogin{
                override fun onFinish() {
                    startActivity(Intent(Base.activitySplash , MainActivity::class.java))
                    Base.activitySplash?.finish()
                }
            })
        }
    }

    private fun login(){
        val userEmail = edtUserEmailLogin.text.toString();
        val userPassword = edtUserPasswordLogin.text.toString();

        if  (TextUtils.isEmpty(userEmail)){
            Toast.makeText(Base.activitySplash , "فیلد ایمیل را پر کنید" , Toast.LENGTH_LONG) .show();
        }
        else if  (TextUtils.isEmpty(userPassword)){
            Toast.makeText(Base.activitySplash , "فیلد رمز عبور را پر کنید" , Toast.LENGTH_LONG) .show();
        }
        else{
            dataSource.loginLogin(requestQueue , Base.activitySplash , userEmail , userPassword , object: DataSource.OnFinishRegisterOrLogin{
                override fun onFinish() {
                    startActivity(Intent(Base.activitySplash , MainActivity::class.java))
                    Base.activitySplash?.finish()
                }
            })

        }
    }



}
