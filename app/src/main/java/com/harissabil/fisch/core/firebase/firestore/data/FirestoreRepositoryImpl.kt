package com.harissabil.fisch.core.firebase.firestore.data

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.GeoPoint
    override fun getLogbooks(): Flow<Resource<List<LogbookResponse>>> = callbackFlow {
        val logbookListener = logbooksRef.orderBy(LOGBOOKS).addSnapshotListener { snapshot, e ->
            val logbooksResponse = if (snapshot != null) {
                val logbooks = snapshot.toObjects(Logbook::class.java)
                Resource.Success(logbooks)
            } else {
                Resource.Error("Error", e)
            }
            trySend(logbooksResponse as Resource<List<LogbookResponse>>)
        }
        awaitClose {
            logbookListener.remove()
        }
    }

    override fun getMap(): Flow<Resource<List<MapResponse>>> = callbackFlow {
        val mapsListener = mapsRef.orderBy(MAPS).addSnapshotListener { snapshot, e ->
            val mapsResponse = if (snapshot != null) {
                val maps = snapshot.toObjects(Map::class.java)
                Resource.Success(maps)
            } else {
                Resource.Error("Error", e)
            }
            trySend(mapsResponse as Resource<List<MapResponse>>)
        }
        awaitClose {
            mapsListener.remove()
        }
    }

    override suspend fun addLogbook(logbook: Logbook): Resource<Boolean> = try {
        val id = logbooksRef.document().id
        val tambahlogbook = Logbook(
            id = logbook.id,
            email = logbook.email,
            jenisIkan = logbook.jenisIkan,
            jumlahIkan = logbook.jumlahIkan,
            waktuPenangkapan = logbook.waktuPenangkapan,
            tempatPenangkapan = logbook.tempatPenangkapan,
            fotoIkan = logbook.fotoIkan,
            catatan = logbook.catatan
        )
        logbooksRef.document(id).set(tambahlogbook).await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error("Error", false)
    }

    override suspend fun updateLogbook(logbook: Logbook): Resource<Boolean> = try {
        val id = logbooksRef.document().id
        val newlogbook = Logbook(
            id = logbook.id,
            email = logbook.email,
            jenisIkan = logbook.jenisIkan,
            jumlahIkan = logbook.jumlahIkan,
            waktuPenangkapan = logbook.waktuPenangkapan,
            tempatPenangkapan = logbook.tempatPenangkapan,
            fotoIkan = logbook.fotoIkan,
            catatan = logbook.catatan
        )
        logbooksRef.document(id).set(newlogbook).await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error("Error", false)
    }

    override suspend fun deleteLogbook(logbookId: String): Resource<Boolean> = try {
        logbooksRef.document(logbookId).delete().await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error("Error", false)
    }

    override suspend fun addMap(map: Map): Resource<Boolean> = try {
        val id = mapsRef.document().id
        val maps = Map(
            id = id
        )
        mapsRef.document(id).set(maps).await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error("Error", false)
    }
            logbookListener.remove()
        }
    }

    override fun getMap(): Flow<Resource<List<MapResponse>>> = callbackFlow{
        val mapsListener = mapsRef.orderBy(MAPS).addSnapshotListener { snapshot, e ->
            val mapsResponse = if (snapshot != null) {
                val maps = snapshot.toObjects(Map::class.java)
                Resource.Success(maps)
            } else {
                Resource.Error("Error", e)
            }
            trySend(mapsResponse as Resource<List<MapResponse>>)
        }
        awaitClose {
            mapsListener.remove()
        }
    }

    override suspend fun addLogbook(logbook: Logbook): Resource<Boolean> = try{
        val id = logbooksRef.document().id
        val tambahlogbook = Logbook(
            id = logbook.id,
            email = logbook.email,
            jenisIkan = logbook.jenisIkan,
            jumlahIkan = logbook.jumlahIkan,
            waktuPenangkapan = logbook.waktuPenangkapan,
            tempatPenangkapan = logbook.tempatPenangkapan,
            fotoIkan = logbook.fotoIkan,
            catatan = logbook.catatan
        )
        logbooksRef.document(id).set(tambahlogbook).await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error("Error", false)
    }

    override suspend fun updateLogbook(logbook: Logbook): Resource<Boolean> = try{
        val id = logbooksRef.document().id
        val newlogbook = Logbook(
>>>>>>> e2c2c9c (Update FirestoreRepositoryImpl.kt)
            id = logbook.id,
            email = logbook.email,
            jenisIkan = logbook.jenisIkan,
            jumlahIkan = logbook.jumlahIkan,
            waktuPenangkapan = logbook.waktuPenangkapan,
            tempatPenangkapan = logbook.tempatPenangkapan,
<<<<<<< HEAD
            fotoIkan = fishImageUrl,
            catatan = logbook.catatan,
        )

        Timber.d("logbookToUpdate: $logbookToUpdate")

        logbooksRef.document(logbook.id!!).set(logbookToUpdate).await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Something went wrong!", false)
    }

    override suspend fun deleteLogbook(logbookId: String, imageUrl: String?): Resource<Boolean> =
        try {
            if (imageUrl != null) {
                val deleteImage =
                    storageRepository.deleteImage(path = imageUrl.getFilenameFromUrl())
                if (deleteImage is Resource.Error) {
                    throw Exception(deleteImage.message)
                }
            }

            logbooksRef.document(logbookId).delete().await()

            val mapsQuerySnapshot = mapsRef.whereEqualTo("logbook", logbookId).get().await()
            for (document in mapsQuerySnapshot.documents) {
                document.reference.delete().await()
            }
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Something went wrong!")
        }

    private fun String.getFilenameFromUrl(): String {
        val urlToken = this.split('?')
        val url = urlToken[0].split('/')
        return url.last().replace("%2F", "/")
    }
}

=======
            fotoIkan = logbook.fotoIkan,
            catatan = logbook.catatan
        )
        logbooksRef.document(id).set(newlogbook).await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error("Error", false)
    }

    override suspend fun deleteLogbook(logbookId: String): Resource<Boolean> = try{
        logbooksRef.document(logbookId).delete().await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error("Error", false)
    }

    override suspend fun addMap(map: Map): Resource<Boolean> = try{
        val id = mapsRef.document().id
        val maps = Map(
            id = id
        )
        mapsRef.document(id).set(maps).await()
        Resource.Success(true)
    } catch (e: Exception) {
        Resource.Error("Error", false)
    }

}
>>>>>>> e2c2c9c (Update FirestoreRepositoryImpl.kt)
