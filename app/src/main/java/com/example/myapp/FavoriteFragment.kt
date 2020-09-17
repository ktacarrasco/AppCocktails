package com.example.myapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.pojo.Cocktails
import com.example.myapp.ui.main.Adapter
import com.example.myapp.ui.main.MainFragment
import com.example.myapp.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_second.view.*
import kotlinx.android.synthetic.main.main_fragment.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"
private const val ARG_PARAM2 = "param2"

 private lateinit var viewAdapter: Adapter
private lateinit var mViewModel: MainViewModel
private lateinit var mFragment: FavoriteFragment

class FavoriteFragment : Fragment(), Adapter.MyClickListener  {

    private var param1: Int? = null
    private var param2: String? = null
    private var cocktailsList =  ArrayList<Cocktails>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //update adapter
        //viewAdapter.notifyDataSetChanged()
        //Iniciando el ViewModel
        mViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        // Iniciando el adapter
        viewAdapter = Adapter(cocktailsList, this)
         }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View= inflater.inflate(R.layout.fragment_favorite, container, false)

        view.RecyclerViewFav.layoutManager = LinearLayoutManager(this.context)
        view.RecyclerViewFav.adapter = viewAdapter
        mViewModel.fetchFromServer()
        mViewModel.getFavFromDB(id).observe(viewLifecycleOwner, Observer {
            Log.d("fav", it.toString())
            viewAdapter.updateData(it)

        })

           return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(cocktails: Cocktails) {
        val bundle=Bundle()
        bundle.putInt("id", cocktails.id)

        findNavController().navigate(R.id.action_favoriteFragment_to_secondFragment, bundle)

    }

    override fun favClick(cocktails: Cocktails) {
        cocktails.favStatus = true
        mViewModel.updateFav(cocktails)
    }

    override fun desfavClick(cocktails: Cocktails) {
        cocktails.favStatus = false
        mViewModel.updateFav(cocktails)
    }


}