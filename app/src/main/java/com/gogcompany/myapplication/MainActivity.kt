package com.gogcompany.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.Volley
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.DataSource
import com.gogcompany.myapplication.App.DialogPermission
import com.gogcompany.myapplication.DataBase.DBApp
import com.gogcompany.myapplication.Fragment.MainFragment
import java.util.Objects

class MainActivity : AppCompatActivity() {

    private val requestCodeStorage = 100;


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Base.activity = this
        Base.fragmentManager=supportFragmentManager;


        var requestQueue = Volley.newRequestQueue(this);
        Base.dbApp = DBApp.getInstance(this);

        val dataSource = DataSource();
        dataSource.getAllArtists(requestQueue);
        dataSource.getAllMusics(requestQueue);
        dataSource.getAllVideo(requestQueue);

        ChangeFragment(supportFragmentManager).replaceFragment(MainFragment(),false);

        val permission = checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission == PackageManager.PERMISSION_DENIED){
            checkPermission();
        }
    }

    private fun checkPermission() {
        var permissions = arrayOf(
            "android.permission.WRITE_EXTERNAL_STORAGE"
        )
        requestPermissions(permissions , requestCodeStorage);
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            requestCodeStorage->{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //ChangeFragment(supportFragmentManager).replaceFragment(MainFragment(),false);
                }
                else{
                    DialogPermission(
                        Base.activity ,
                        "اجازه دسترسی به فایل ها" ,
                        "این برنامه نیاز به دسترسی فایل ها دارد!!!" ,
                        object :DialogPermission.OnBtnDialogAction{
                            override fun onAccept() {
                                checkPermission();
                            }

                            override fun onCancel() {
                                //finish()
                            }
                        }
                    ).show();
                }
            }
        }
    }
}

