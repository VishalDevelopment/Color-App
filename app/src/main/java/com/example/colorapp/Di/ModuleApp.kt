package com.example.colorapp.Di

import android.content.Context
import androidx.room.Room
import com.example.colorapp.Room.Color_Db.ColorDB
import com.example.colorapp.Room.Pending_Db.PendingRoomDb
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
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun ColorDaoRef(db: ColorDB) = db.colorDao()


    @Singleton
    @Provides
    fun PendingDbRef(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        PendingRoomDb::class.java,
        "PendingDatabase"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun PendingDaoRef(db: PendingRoomDb) = db.pendingDao()

    @Singleton
    @Provides
    fun provideFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}