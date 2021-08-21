package pl.gungnir.linkmanager.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseDatabaseRepoImpl @Inject constructor() : FirebaseDatabaseRepo {

    private var db: FirebaseFirestore? = null

    private fun initFirebaseIfNeeded() {
        db ?: let {
            db = Firebase.firestore
        }
    }
}