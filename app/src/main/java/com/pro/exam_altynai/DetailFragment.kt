package com.pro.exam_altynai

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pro.exam_altynai.databinding.FragmentDetailBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailFragment: Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val rickMortyApi get() = Injector.rickMortyApi

    private lateinit var listener : OnItemClicked

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnItemClicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val id = arguments?.getLong("id") ?: 1L

        binding.apply {
            if (id != null) {
                rickMortyApi.getCharacter(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess {
                        if (it.image != null) {
//                            Log.e("TAG", "Image $it")
                            Glide.with(requireContext()).load(it.image).into(image)
                        }
                        name.text = it.name
                        status.text = "status: " + it.status
                        species.text = "species: ${it.species}"
                        gender.text = "gender: " + it.gender
                        created.text = "created date: " + it.created
                        origin.text = "origin: " + it.origin.name
                        location.text = "location: " + it.location.name
                        if (it.type != null) {
                            type.text = "type: " + it.type
                        }
                        else {
                            type.text = "type: -"
                        }
                    }
                    .doOnError{
                        Log.e("TAG", "Error")
                    }
                    .subscribe()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}