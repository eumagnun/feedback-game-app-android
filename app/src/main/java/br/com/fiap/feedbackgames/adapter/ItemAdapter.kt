package br.com.fiap.feedbackgames.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.feedbackgames.R
import com.squareup.picasso.Picasso
import model.FeedbackGame

class ItemAdapter(
    private val context: Context, private val dataset: List<FeedbackGame>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    //TODO EXTRA-CLICK_LISTENER-3-Classe correspondente a um item da lista, também implementa a interface para monitoramento de clicks em items
    class ItemViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        val tvItemId: TextView = view.findViewById(R.id.tvItemId)
        val tvItemNome: TextView = view.findViewById(R.id.tvItemNome)
        val tvItemConsole: TextView = view.findViewById(R.id.tvItemConsole)
        val ivItemFoto: ImageView = view.findViewById(R.id.ivItemFoto)
        val tvItemUrlVideo: TextView = view.findViewById(R.id.tvItemUrlVideo)
    }

    //TODO EXTRA-CLICK_LISTENER-4-//inicializado ItemViewHolder com onItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    //função usada pelo layoutManager para aplicar os dados do dataset a cada item da lista
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val gameFeedback = dataset[position]
        holder.tvItemId.text = gameFeedback.id.toString()
        holder.tvItemNome.text = gameFeedback.nome
        holder.tvItemConsole.text = gameFeedback.console
        holder.tvItemUrlVideo.text = gameFeedback.urlVideo
        Picasso.get().load(gameFeedback.urlImagem).into(holder.ivItemFoto)
    }

    override fun getItemCount() = dataset.size


    interface OnItemClickListener {
        fun onItemClick(item: Int)
    }
}