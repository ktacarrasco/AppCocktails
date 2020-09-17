package com.example.myapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.pojo.Cocktails
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() , Adapter.MyClickListener {

    private var cocktailsList =  ArrayList<Cocktails>()

    private lateinit var viewAdapter: Adapter
    private lateinit var mViewModel: MainViewModel
    private lateinit var mFragment: MainFragment


    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        setHasOptionsMenu(true)//option menu
        val view :View=inflater.inflate(R.layout.main_fragment, container, false)


        return view

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Iniciando el ViewModel

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Iniciando el adapter
        viewAdapter = Adapter(cocktailsList, this)
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
            bundle.putInt("id", cocktails.id)

            findNavController().navigate(R.id.action_mainFragment_to_secondFragment, bundle)


    }

    override fun favClick(cocktails: Cocktails) {

            cocktails.favStatus = true
            mViewModel.updateFav(cocktails)



    }

    override fun desfavClick(cocktails: Cocktails) {
        cocktails.favStatus = false
        mViewModel.updateFav(cocktails)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when {

            item.itemId == R.id.add ->  findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)


            item.itemId == R.id.exit-> activity?.finish()
        }
        return true
    }


}