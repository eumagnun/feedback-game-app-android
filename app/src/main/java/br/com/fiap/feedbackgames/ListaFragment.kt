package br.com.fiap.feedbackgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.feedbackgames.adapter.ItemAdapter
import br.com.fiap.feedbackgames.databinding.FragmentListaBinding
import br.com.fiap.feedbackgames.network.FeedbackApi
import kotlinx.coroutines.*
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

        carregarDados()

    }

    override fun onResume() {
        carregarDados()
        super.onResume()
    }

    private fun atualizarTela() {
        recyclerView.adapter = ItemAdapter(listaFeedbacks)

        binding.tvQuantidade.text = listaFeedbacks.size.toString()

        binding.fab.setOnClickListener() {
            val action = ListaFragmentDirections.actionListaFragmentToCadastroFragment()
            this.findNavController().navigate(action)
        }
    }

    private fun carregarDados(){
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                val result = FeedbackApi.retrofitService.getFeedbacks()
                //println("retornoApi: Success: ${result.size} registros recuperados")
                listaFeedbacks = mutableListOf<FeedbackGame>()
                result.forEach {listaFeedbacks.add((it))}

                withContext(Dispatchers.Main){
                    atualizarTela()
                }
            }catch (e: Exception){
                //println("retornoApi: " + e.message)
            }
        }
    }
}