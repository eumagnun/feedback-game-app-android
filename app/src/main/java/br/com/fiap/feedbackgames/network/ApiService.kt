package br.com.fiap.feedbackgames.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import model.FeedbackGame
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


//TODO Troque a url abaixo pela url da a api que voce publicou
private const val BASE_URL = "http://ip172-18-0-15-c4pponfnjsv000acpqr0-8080.direct.labs.play-with-docker.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FeedbackApiService {

    @GET("/feedback-games/v1/api/feedbacks")
    suspend fun getFeedbacks(): List<FeedbackGame>

    @POST("/feedback-games/v1/api/feedbacks")
    suspend fun gravarFeedback(@Body feedbackGame:FeedbackGame )

}


object FeedbackApi {
    val retrofitService: FeedbackApiService by lazy {
        retrofit.create(FeedbackApiService::class.java)
    }
}