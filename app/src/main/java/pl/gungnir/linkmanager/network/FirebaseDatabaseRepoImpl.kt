package pl.gungnir.linkmanager.network

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import pl.gungnir.linkmanager.domain.model.Link
import pl.gungnir.linkmanager.domain.model.Result
import javax.inject.Inject

class FirebaseDatabaseRepoImpl @Inject constructor() : FirebaseDatabaseRepo {

    private var db: FirebaseFirestore? = null

    private fun initFirebaseIfNeeded() {
        db ?: let {
            db = Firebase.firestore
        }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getLinks(): Flow<Result> {
        return channelFlow {
            initFirebaseIfNeeded()
            db?.let {
                it.collection("links")
                    .addSnapshotListener { value, _ ->
                        val data = value?.documents?.get(0)?.data
                        data?.let {
                            val links = arrayListOf<Link>()
                            it.keys.forEach { key ->
                                val values = it[key] as Map<String, Any>

                                links.add(
                                    Link(
                                        label = key,
                                        url = (values["link"] as String?) ?: "",
                                        visited = (values["timestamp"] as Timestamp).toDate()
                                    )
                                )
                            }
                            sendBlocking(Result.Success(links))
                        } ?: sendBlocking(Result.Error)
                    }
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }
}