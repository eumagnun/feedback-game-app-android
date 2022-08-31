package br.com.fiap.feedbackgames.model

data class FeedbackGame
    ( val id: Int = 0,
      val nota: String="",
      val nome: String="",
      val console: String="",
      val urlImagem: String = "",
      val urlVideo: String = ""
    )