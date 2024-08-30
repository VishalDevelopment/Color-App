package com.example.colorapp.Utilse

import android.content.Context
import androidx.room.Room
import com.example.colorapp.Room.ColorDB
import com.example.colorapp.Room.PendingDb
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun ColorDbRef(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ColorDB::class.java,
        "color_db"
    ).build()

    @Singleton
    @Provides
    fun ColorDaoRef(db: ColorDB) = db.colorDao()


    @Singleton
    @Provides
    fun PendingDbRef(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        PendingDb::class.java,
        "PendingDatabase"
    ).build()

    @Singleton
    @Provides
    fun PendingDaoRef(db: PendingDb) = db.pendingDao()

    @Singleton
    @Provides
    fun provideFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}