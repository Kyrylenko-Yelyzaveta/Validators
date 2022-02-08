package com.example.kvalificators

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kvalificators.cost.Const
import com.example.kvalificators.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  bind: ActivityMainBinding
    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var surname: String = "empty"
    private var patronymic: String = "empty"
    private var pictureAvatar: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Const.REQUEST_CODE_SING_IN)
        {
            val l = data?.getStringExtra(Const.LOGIN)
            val p = data?.getStringExtra(Const.PASSWORD)
            if (login == l && password==p) {
                
                bind.imAvatar.visibility = View.VISIBLE
                bind.imAvatar.setImageResource(pictureAvatar)
                val textInfo = "$name $surname $patronymic"
                bind.txStatus.text = textInfo
                bind.bt2.visibility =View.GONE
                bind.bt1.text ="Get Out"
            }
            else
            {
                bind.imAvatar.visibility = View.INVISIBLE
                bind.bt1.visibility =View.INVISIBLE
                bind.bt2.visibility =View.INVISIBLE
                bind.txStatus.text ="Такого аккаунта не существует"
            }

        }
        else if(requestCode == Const.REQUEST_CODE_SING_UP)
        {
            login = data?.getStringExtra(Const.LOGIN)!!
            password = data.getStringExtra(Const.PASSWORD)!!
            name = data.getStringExtra(Const.NAME)!!
            surname = data.getStringExtra(Const.SURNAME)!!
            patronymic = data.getStringExtra(Const.PATRONMIC)!!
            pictureAvatar = data.getIntExtra(Const.AVATAR, 0)
            bind.imAvatar.visibility = View.VISIBLE
            bind.imAvatar.setImageResource(pictureAvatar)
            val textInfo = "$name $surname $patronymic"
            bind.txStatus.text = textInfo
            bind.bt2.visibility =View.GONE
            bind.bt1.text ="Get Out"


        }
    }

    fun onClickSingIn(view: View)
    {
        if (bind.imAvatar.getVisibility() == View.VISIBLE && bind.txStatus.text.toString()
            !="Такого аккаунта не существует")
        {
            bind.imAvatar.visibility = View.INVISIBLE
            bind.txStatus.text = ""
            bind.bt2.visibility = View.VISIBLE
            bind.bt1.text = getString(R.string.sign_in)
        }
        else {

            val intent = Intent(this, SingUpIn::class.java)
            intent.putExtra(Const.SIGN_STATE, Const.SIGN_IN)
            startActivityForResult(intent, Const.REQUEST_CODE_SING_IN)
        }
    }
    fun onClickSingUp(view: View)
    {
        val intent =Intent(this, SingUpIn::class.java)
        intent.putExtra(Const.SIGN_STATE, Const.SIGN_UP)
        startActivityForResult(intent,  Const.REQUEST_CODE_SING_UP)
    }


}