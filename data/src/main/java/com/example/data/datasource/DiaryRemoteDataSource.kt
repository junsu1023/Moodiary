package com.example.data.datasource

import android.util.Log
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
    fun saveDiary(diaryDto: DiaryDto) {
        val uid = auth.currentUser?.uid ?: throw IllegalStateException("로그인된 회원이 아닙니다.")
        val docRef = firestore.collection("users")
            .document(uid)
            .collection("diaries")
            .document()
        val generatedId = docRef.id

        val diary = diaryDto.copy(diaryId = generatedId)

        docRef.set(diary).addOnSuccessListener {
            Log.d("DiaryRemoteDataSource", "Diary saved success")
        }.addOnFailureListener {
            Log.d("DiaryRemoteDataSource", "Diary saved failed: ${it.message}")
        }
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

    suspend fun getDiaryById(diaryId: String): DiaryDto {
        val uid = auth.currentUser?.uid ?: throw IllegalStateException("로그인된 회원이 아닙니다.")

        val document = firestore.collection("users")
            .document(uid)
            .collection("diaries")
            .document(diaryId)
            .get()
            .await()

        return if(document.exists()) {
            println("test-kjs: success")
            document.toObject(DiaryDto::class.java) ?: throw IllegalStateException("일기 데이터를 변환할 수 없습니다.")
        } else {
            println("test-kjs: failed")
            throw NoSuchElementException("해당 ID의 일기를 찾을 수 없습니다: $diaryId")
        }
    }
}