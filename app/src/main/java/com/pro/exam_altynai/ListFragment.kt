package com.pro.exam_altynai

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pro.exam_altynai.database.CharActer
import com.pro.exam_altynai.databinding.FragmentListBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListFragment: Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val rickMortyApi get() = Injector.rickMortyApi
    private val dbInstance get() = Injector.database
    private lateinit var listener : OnItemClicked
    private lateinit var adapter: ItemAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnItemClicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val layoutManager = LinearLayoutManager(activity)
        adapter = ItemAdapter {
            Toast.makeText(activity, "Character -$it", Toast.LENGTH_SHORT).show()
            listener.onClick(it)
        }

        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))

        thread()

        //обновление
        binding.swiperefresh.setOnRefreshListener {
            thread()
        }

    }


    private fun thread(){
        rickMortyApi.getAllChar()
            .subscribeOn(Schedulers.io())
            .doFinally {
                binding.swiperefresh.isRefreshing = false
            }
            .map {
                val list = mutableListOf<CharActer>()

                it.results.forEach {
                    val character = CharActer(
                        id = it.id,
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        image = it.image,
                        created = it.created,
                        gender = it.gender,
                        url = it.url,
                        type = it.type,
                        episode = it.episode,
                        location = it.location.name
                    )
                    list.add(character)
                }
                list.toList()
            }
            .map {
                dbInstance.characterDao().insertAll(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                adapter.setData(it)
                Log.e("TAG", "fragmentMain doOnNext ${Thread.currentThread().name}")
            }
            .onErrorResumeNext ( dbInstance.characterDao().getAll() )
            .subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}