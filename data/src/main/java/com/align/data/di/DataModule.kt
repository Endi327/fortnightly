package com.align.data.di

import android.content.Context
import androidx.room.Room
import com.align.core.threads.BackgroundDispatcher.Background
import com.align.data.database.AppDatabase
import com.align.data.network.FortnightlyService
import com.align.domain.ConstantsProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            callTimeout(5, TimeUnit.MINUTES)
            connectTimeout(20, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.MINUTES)
            writeTimeout(5, TimeUnit.MINUTES)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    internal fun provideApsService(
        httpClient: OkHttpClient,
        constantsProvider: ConstantsProvider,
        gson: Gson
    ): FortnightlyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(constantsProvider.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()

        return retrofit.create(FortnightlyService::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Background
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "fortnightly-database"
        ).build()
    }

    @Singleton
    @Provides
    internal fun provideContext(
        @ApplicationContext context: Context
    ): Context {
        return context
    }
}