package br.com.fiap.feedbackgames.network

import br.com.fiap.feedbackgames.model.FeedbackGame
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FeedbackApi {

    @GET("feedback-games/v1/api/feedbacks")
    suspend fun getFeedbacks(): List<FeedbackGame>

    @POST("feedback-games/v1/api/feedbacks")
    suspend fun gravarFeedback(@Body feedbackGame: FeedbackGame)
}