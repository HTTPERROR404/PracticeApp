package com.example.pocpracticeapp.ui.datalist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imageloader.ImageLoader
import com.example.pocpracticeapp.R
import com.example.pocpracticeapp.databinding.DataListFragmentBinding
import com.example.pocpracticeapp.utils.isNetworkAvailable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DataListFragment : Fragment(), CharactersAdapter.CharacterItemListener {
    companion object {
        fun newInstance() = DataListFragment()
    }

    private lateinit var viewModel: DataListViewModel
    private var _binding: DataListFragmentBinding? = null

    private val binding get() = _binding!!

    var adapter: CharactersAdapter? = null

    @Inject
    lateinit var imageLoader : ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataListFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DataListViewModel::class.java]
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter(this, imageLoader)
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.postsRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        if (context?.isNetworkAvailable() == true) {
            viewModel.getPosts()
        } else {
            Toast.makeText(
                context,
                "Internet not available",
                Toast.LENGTH_SHORT
            ).show()
        }

        with(viewModel) {

            postsData.observe(this@DataListFragment, Observer {
                binding.postsProgressBar.visibility = GONE
                adapter?.setItems(it.results)
            })

            messageData.observe(this@DataListFragment, Observer {
                Toast.makeText(context, it, LENGTH_LONG).show()
            })

            showProgressbar.observe(this@DataListFragment, Observer { isVisible ->
                binding.postsProgressBar.visibility = if (isVisible) VISIBLE else GONE
            })
        }
    }


    override fun onClickedCharacter(characterId: String) {
        findNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("listing" to characterId)
        )
    }
}