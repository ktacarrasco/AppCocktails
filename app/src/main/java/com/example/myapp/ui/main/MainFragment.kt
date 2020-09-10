package com.example.myapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.pojo.Cocktails
import kotlinx.android.synthetic.main.item_cocktails.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() , Adapter.MyClickListener {

    private var cocktailsList =  ArrayList<Cocktails>()

    private lateinit var viewAdapter: Adapter
    private lateinit var mViewModel: MainViewModel
    private lateinit var mFragment: MainFragment


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view :View=inflater.inflate(R.layout.main_fragment,container,false)


        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Iniciando el ViewModel
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Iniciando el adapter
        viewAdapter = Adapter(cocktailsList,this)
        RecyclerView.layoutManager = LinearLayoutManager(context)
        RecyclerView.adapter = viewAdapter

        mViewModel.fetchFromServer()
        mViewModel.getDataFromDB(id).observe(viewLifecycleOwner, Observer {
            Log.d("cant", it.toString())
            viewAdapter.updateData(it)


        })
    }

    override fun onItemClick(cocktails: Cocktails) {

            val bundle=Bundle()
            bundle.putInt("id",cocktails.id)

       /* if (container2.visibility == View.GONE) {
            TransitionManager.beginDelayedTransition(cardView, AutoTransition())
            container2.visibility = View.VISIBLE

        } else {
            TransitionManager.beginDelayedTransition(cardView, AutoTransition())
            container2.visibility = View.GONE

        }*/

           // findNavController().navigate(R.id.action_mainFragment_to_secondFragment,bundle)


    }


}