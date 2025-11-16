package com.example.data.datasource

import com.example.data.dto.DiaryDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
}