package com.dvt.weatherapp.app.domain.datasource.searchCities

import android.util.Log
import com.algolia.search.saas.places.PlacesClient
import com.algolia.search.saas.places.PlacesQuery
import com.dvt.weatherapp.app.domain.model.SearchResponse
import com.dvt.weatherapp.app.utils.extensions.tryCatch
import com.squareup.moshi.Moshi
import io.reactivex.Single
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Masi on 2019-12-11
 */

class SearchCitiesRemoteDataSource @Inject constructor(private val client: PlacesClient, private val moshi: Moshi) {

    fun getCityWithQuery(query: String): Single<SearchResponse> {
        return Single.create { single ->
            val algoliaQuery = PlacesQuery(query)
                .setLanguage("en")
                .setHitsPerPage(25)

            client.searchAsync(algoliaQuery) { json, exception ->
                if (exception == null) {
                    tryCatch(
                        tryBlock = {
                            val adapter = moshi.adapter<SearchResponse>(SearchResponse::class.java)
                            val data = adapter.fromJson(json.toString())

                            if (data?.hits != null)
                                single.onSuccess(data)
                        },
                        catchBlock = {
                            Log.v("Algolia Search", ": ${it.message}")
                        }
                    )
                } else
                    single.onError(UnknownHostException())
            }
        }
    }
}
