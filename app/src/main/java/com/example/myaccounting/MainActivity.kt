package com.example.myaccounting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myaccounting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)//получаем наш врагмент, прописанный в activity_main.xml

        navController = navHostFragment?.findNavController()!!

        NavigationUI.setupActionBarWithNavController(this,navController)//добавляем на экшен баре какие-то события
    }

    override fun onSupportNavigateUp(): Boolean {//добавляет навигацию по кнопке назад , как для системной кнопки так и для стандартной назад
        return navController.navigateUp()
    }
}