package com.example.kvalificators

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kvalificators.cost.Const
import com.example.kvalificators.databinding.ActivitySingUpInBinding

class SingUpIn : AppCompatActivity() {
    private lateinit var bind: ActivitySingUpInBinding
    private var signState = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySingUpInBinding.inflate(layoutInflater)
        setContentView(bind.root)

        signState = intent.getStringExtra(Const.SIGN_STATE)!!

        if (signState == Const.SIGN_IN) {
            bind.edName.visibility = View.GONE
            bind.edSurname.visibility = View.GONE
            bind.edFatherName.visibility = View.GONE
            bind.bAvatar.visibility = View.INVISIBLE
        }
    }

    fun onClickDone(view: View) {
        if (signState == Const.SIGN_UP) {
            val intent = Intent()
            intent.putExtra(Const.LOGIN, bind.edLogIn.text.toString())
            intent.putExtra(Const.PASSWORD, bind.edPassword.text.toString())
            intent.putExtra(Const.NAME, bind.edName.text.toString())
            intent.putExtra(Const.SURNAME, bind.edSurname.text.toString())
            intent.putExtra(Const.PATRONMIC, bind.edFatherName.text.toString())
            intent.putExtra(Const.AVATAR, R.drawable.face_co)
            setResult(RESULT_OK, intent)
            finish()

        } else if (signState == Const.SIGN_IN) {
            intent.putExtra(Const.LOGIN, bind.edLogIn.text.toString())
            intent.putExtra(Const.PASSWORD, bind.edPassword.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
        fun onClickAvatar(view: View) {
            bind.imAvatarSign.visibility = View.VISIBLE
        }
    }
}