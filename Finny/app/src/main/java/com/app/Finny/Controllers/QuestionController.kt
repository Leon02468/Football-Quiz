package com.app.Finny.Controllers

import com.app.Finny.Models.QuestionModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.local.QueryEngine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await


class QuestionController {
    // Create a firestore instance
    private val db = FirebaseFirestore.getInstance()

    fun getAllByDifficulty(difficulty: String, callback: (res: List<QuestionModel>) -> Unit) {
        var questions = mutableListOf<QuestionModel>()

        db.collection("${difficulty}_questions").get()
            .addOnSuccessListener { documents ->
                var i = 0
                for(document in documents) {
                    val data = document.data
                    val option_list: List<String> = data.get("options") as List<String>

                    val question = QuestionModel(document.id, data.get("image_url").toString(), data.get("question").toString(), option_list, data.get("correct").toString())
                    questions.add(question)
                }

                callback.invoke(questions)
            }
    }

    fun getOne(id: String) {

    }

    fun update(question: QuestionModel) {

    }

    fun delete(id: String) {

    }





    /***
    // DEPRECATED , DO NOT RUN
    // Find all questions at each difficulty and put them in their respective difficulty collective
    fun filterQuestions() {
        questionCol.get().addOnSuccessListener { documents ->
            for (document in documents) {
                val data = document.data

                if(document.id.contains("easy")) {
                    val question_list = listOf<String>(data.get("wrong_1").toString(), data.get("wrong_2").toString(), data.get("wrong_3").toString(), data.get("correct").toString())

                    val question: QuestionModel = QuestionModel(
                        document.id,
                        "",
                        data.get("text").toString(),
                        question_list,
                        data.get("correct").toString()
                    )

                    val quesRef = db.collection("easy_questions").document(document.id)
                    quesRef.set(question)

                } else if(document.id.contains("norm")) {
                    val question_list = listOf<String>(data.get("wrong_1").toString(), data.get("wrong_2").toString(), data.get("wrong_3").toString(), data.get("correct").toString())

                    val question: QuestionModel = QuestionModel(
                        document.id,
                        "",
                        data.get("text").toString(),
                        question_list,
                        data.get("correct").toString()
                    )

                    val quesRef = db.collection("medium_questions").document(document.id)
                    quesRef.set(question)

                } else if(document.id.contains("hard")) {
                    val question_list = listOf<String>(data.get("wrong_1").toString(), data.get("wrong_2").toString(), data.get("wrong_3").toString(), data.get("correct").toString())

                    val question: QuestionModel = QuestionModel(
                        document.id,
                        "",
                        data.get("text").toString(),
                        question_list,
                        data.get("correct").toString()
                    )

                    val quesRef = db.collection("expert_questions").document(document.id)
                    quesRef.set(question)
                }
            }
        }
    }

    ***/
}