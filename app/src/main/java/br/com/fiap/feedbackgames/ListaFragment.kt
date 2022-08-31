package br.com.fiap.feedbackgames

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.feedbackgames.adapter.ItemAdapter
import br.com.fiap.feedbackgames.databinding.FragmentListaBinding
import br.com.fiap.feedbackgames.model.FeedbackGame

class ListaFragment : Fragment(){

    private lateinit var binding: FragmentListaBinding
    private lateinit var listaFeedbacks: MutableList<FeedbackGame>

    private lateinit var  recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listaFeedbacks = mutableListOf()
        recyclerView = binding.rvItems
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.fab.setOnClickListener() {
            val action = ListaFragmentDirections.actionListaFragmentToCadastroFragment()
            this.findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        carregarDados()
    }

    private fun atualizarTela() {
        recyclerView.adapter = ItemAdapter(listaFeedbacks)
        binding.tvQuantidade.text = listaFeedbacks.size.toString()
    }

    private fun carregarDados(){
        Log.i("EVENTO_API","Consumindo dados da API ")
    }
}