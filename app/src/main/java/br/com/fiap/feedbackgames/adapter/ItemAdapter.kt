package br.com.fiap.feedbackgames.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.feedbackgames.R
import com.squareup.picasso.Picasso
import br.com.fiap.feedbackgames.model.FeedbackGame

class ItemAdapter(
    private val dataset: List<FeedbackGame>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val tvItemId: TextView = view.findViewById(R.id.tvItemId)
        val tvItemNome: TextView = view.findViewById(R.id.tvItemNome)
        val tvItemConsole: TextView = view.findViewById(R.id.tvItemConsole)
        val ivItemFoto: ImageView = view.findViewById(R.id.ivItemFoto)
        val tvItemUrlVideo: TextView = view.findViewById(R.id.tvItemUrlVideo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val gameFeedback = dataset[position]
        holder.tvItemId.text = gameFeedback.id.toString()
        holder.tvItemNome.text = gameFeedback.nome
        holder.tvItemConsole.text = gameFeedback.console
        holder.tvItemUrlVideo.text = gameFeedback.urlVideo

        if(!gameFeedback.urlImagem.equals("")) {
            Picasso.get().load(gameFeedback.urlImagem).into(holder.ivItemFoto)
        }
    }

    override fun getItemCount() = dataset.size
}