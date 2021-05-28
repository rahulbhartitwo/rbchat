package com.rbchat.di

import android.app.Application
import android.content.Context
import com.rbchat.db.SharedPreferenceUtil
import com.rbchat.inerfaceUtils.ServiceController
import com.rbchat.inerfaceUtils.ServiceControllerImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
public class MyModule {

    @Singleton
    @Provides
    fun isProvider() : ServiceController = ServiceControllerImp()

//    @Singleton
//    @Provides
//    fun isProvider1(@ApplicationContext context: Context) : SharedPreferenceUtil = SharedPreferenceUtil.getInstance(context)

}