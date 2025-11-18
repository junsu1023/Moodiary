package com.example.data.datasource

import com.example.data.dto.DiaryDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DiaryRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
    suspend fun saveDiary(diaryDto: DiaryDto) {
        val uid = auth.currentUser?.uid ?: throw IllegalStateException("로그인된 회원이 아닙니다.")
        firestore.collection("users")
            .document(uid)
            .collection("diaries")
            .add(diaryDto)
            .await()
    }

    fun observeUserDiaries(userId: String): Flow<List<DiaryDto>> = callbackFlow {
        val ref = firestore.collection("users")
            .document(userId)
            .collection("diaries")
            .orderBy("timeStamp", Query.Direction.DESCENDING)

        val registration = ref.addSnapshotListener { snapshot, error ->
            if(error != null) {
                close(error)
                return@addSnapshotListener
            }

            val list = snapshot?.toObjects(DiaryDto::class.java) ?: emptyList()
            trySend(list)
        }

        awaitClose { registration.remove() }
    }

}