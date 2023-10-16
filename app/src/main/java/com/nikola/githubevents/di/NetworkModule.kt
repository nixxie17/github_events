package com.nikola.githubevents.di

import com.nikola.githubevents.BASE_URL
import com.nikola.githubevents.CREATE_EVENT
import com.nikola.githubevents.DELETE_EVENT
import com.nikola.githubevents.OTHER_EVENT
import com.nikola.githubevents.PULL_REQUEST_EVENT
import com.nikola.githubevents.PUSH_EVENT
import com.nikola.githubevents.data.dto.CreateEventDto
import com.nikola.githubevents.data.dto.DeleteEventDto
import com.nikola.githubevents.data.dto.GitHubEventDto
import com.nikola.githubevents.data.dto.OtherEventDto
import com.nikola.githubevents.data.dto.PullRequestEventDto
import com.nikola.githubevents.data.dto.PushEventDto
import com.nikola.githubevents.data.repository.GitHubEventsRepositoryImpl
import com.nikola.githubevents.data.source.ApiService
import com.nikola.githubevents.domain.repository.GitHubEventsRepository
import com.nikola.githubevents.domain.usecase.GetGitHubEventsUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L
private const val NETWORK_SCOPE = "Network"

val NetworkModule = module {

    single { createService(get()) }

    single { MoshiConverterFactory.create() }

    single { createRetrofit(get(), getBaseUrl(), get(named(NETWORK_SCOPE))) }

    single { createOkHttpClient() }

    single(named(NETWORK_SCOPE)) { createMoshiParserConfig() }

}

//Should be coming from the BuildConfig object for different application builds
fun getBaseUrl() = BASE_URL

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createMoshiParserConfig(): Moshi =
    Moshi.Builder()
    .add(
        PolymorphicJsonAdapterFactory.of(GitHubEventDto::class.java, "type")
            .withSubtype(DeleteEventDto::class.java, DELETE_EVENT)
            .withSubtype(CreateEventDto::class.java, CREATE_EVENT)
            .withSubtype(PullRequestEventDto::class.java, PULL_REQUEST_EVENT)
            .withSubtype(PushEventDto::class.java, PUSH_EVENT)
            .withDefaultValue(OtherEventDto(null, OTHER_EVENT, null, null, null))
)
.addLast(KotlinJsonAdapterFactory())
.build()

fun createRetrofit(okHttpClient: OkHttpClient, url: String, moshiConfig: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshiConfig)).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createGitHubEventsRepository(apiService: ApiService): GitHubEventsRepository {
    return GitHubEventsRepositoryImpl(apiService)
}

fun createGetPostsUseCase(gitHubEventsRepository: GitHubEventsRepository): GetGitHubEventsUseCase {
    return GetGitHubEventsUseCase(gitHubEventsRepository)
}
