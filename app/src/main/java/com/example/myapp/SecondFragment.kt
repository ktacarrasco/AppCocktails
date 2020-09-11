package com.example.myapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.ui.main.Adapter
import com.example.myapp.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment(), Adapter.IAdapter {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

            //Iniciando el ViewModel
            mViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view :View=inflater.inflate(R.layout.fragment_second, container, false)

        param1?.let {
            mViewModel.getIdDataFromDB(it).observe(viewLifecycleOwner, Observer {
                Log.d("cant", it.toString())


                view.titleTV.text = " ${it.name_cocktails}"
                //view.titleTV.text = it.name

                view.ingredientsTV.text = "Ingredientes: \n ${it.ingredients}"
               // setMovementMethod(new ScrollingMovementMethod())
                view.preparationTV.text = "Preparacion:  \n ${it.preparation}"
                Picasso.get()
                    .load(it.url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(view.photoTV)

            })
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {

                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun getFromAdapter(id:  Int) {
        mViewModel.getDataFromDB(id)
    }
}