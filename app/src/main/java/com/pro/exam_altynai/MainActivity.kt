package com.pro.exam_altynai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pro.exam_altynai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnItemClicked {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFragments()
    }

    private fun initFragments(){
        supportFragmentManager.beginTransaction()
            .add(R.id.frContainer, ListFragment())
            .commit()
    }

    override fun onClick(id: Long) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        if (id != null) {
            bundle.putLong("id", id)
        }
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.frContainer,fragment)
            .addToBackStack(null)
            .commit()
    }
}