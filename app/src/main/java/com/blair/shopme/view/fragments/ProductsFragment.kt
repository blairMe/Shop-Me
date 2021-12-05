package com.blair.shopme.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.blair.shopme.R

class ProductsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //forcing display of the add product appbar icon
        setHasOptionsMenu(true)
    }

    //Creating the appbar action(adding product)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_new_product, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //Giving the appbar icon function
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.newProductFragment -> {
                findNavController().navigate(R.id.action_productsFragment_to_newProductFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}