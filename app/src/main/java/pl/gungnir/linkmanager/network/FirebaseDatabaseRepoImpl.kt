package pl.gungnir.linkmanager.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseDatabaseRepoImpl : FirebaseDatabaseRepo {

    private var db: FirebaseFirestore? = null

    private fun initFirebaseIfNeeded() {
        db ?: let {
            db = Firebase.firestore
        }
    }
}