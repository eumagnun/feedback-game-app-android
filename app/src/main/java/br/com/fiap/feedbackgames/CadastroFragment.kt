package br.com.fiap.cadastrodepessoas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fiap.feedbackgames.databinding.FragmentCadastroBinding
import br.com.fiap.feedbackgames.network.FeedbackApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.FeedbackGame


class CadastroFragment : Fragment() {

    private var binding: FragmentCadastroBinding? =null

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btCadastrar?.setOnClickListener(){
            gravar()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

    private fun gravar(){

        var feedback = montarFeedback()

        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch() {
            try {
                val result = FeedbackApi.retrofitService.gravarFeedback(feedback)
                println("retornoApi: Successo: ${result}")
                withContext(Dispatchers.Main){
                    updateUI()
                }
            }catch (e: Exception){
                println("retornoApi: " + e.printStackTrace())
            }
        }
    }

    private fun updateUI() {
        binding?.etNome?.text?.clear()
        binding?.etNota?.text?.clear()
        binding?.etConsole?.text?.clear()
        binding?.etLinkImagem?.text?.clear()
        binding?.etlinkVideo?.text?.clear()

        esconderTeclado(requireView())

        val toast = Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_LONG)
        toast.show()
    }

    private fun montarFeedback(): FeedbackGame {
        val nome:String = binding?.etNome?.text.toString()
        val nota:String = binding?.etNota?.text.toString()
        val console:String = binding?.etConsole?.text.toString()
        val linkImagem:String=binding?.etLinkImagem?.text.toString()
        val linkVideo:String=binding?.etlinkVideo?.text.toString()

        val feedbackGame = FeedbackGame(nome = nome, nota = nota, console = console, urlImagem = linkImagem, urlVideo = linkVideo)

        return feedbackGame;
    }


    fun esconderTeclado(view: View) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

}