package com.jjh.pokeapp2.di.repository

import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository {
   val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
}