package br.com.fiap.cadastrodepessoas

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
import model.FeedbackGame


//TODO EXTRA-CLICK_LISTENER-5-//implementar OnItemClickListener
class ListaFragment : Fragment(){

    private var binding: FragmentListaBinding? =null
    //TODO OPCIONAL:
    //  private var _binding: FragmentCadastroBinding? =null
    //  private val binding get() = _binding!!
    private var listaFeedbacks = mutableListOf<FeedbackGame>()

    private lateinit var  recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding!!.rvItems
        recyclerView.layoutManager = LinearLayoutManager(context)

        getDados()

    }

    override fun onResume() {
        getDados()
        super.onResume()
    }

    private fun updateUI() {
        recyclerView.adapter = ItemAdapter(requireContext(), listaFeedbacks)

        binding?.tvQuantidade?.text = listaFeedbacks.size.toString()

        binding?.fab?.setOnClickListener() {
            val action = ListaFragmentDirections.actionListaFragmentToCadastroFragment()
            this.findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

    private fun getDados(){
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch() {
            try {
                val result = FeedbackApi.retrofitService.getFeedbacks()
                println("retornoApi: Success: ${result.size} registros recuperados")
                listaFeedbacks = mutableListOf<FeedbackGame>()
                result.forEach {listaFeedbacks.add((it))}

                withContext(Dispatchers.Main){
                    updateUI()
                }
            }catch (e: Exception){
                println("retornoApi: " + e.message)
            }
        }
    }


}