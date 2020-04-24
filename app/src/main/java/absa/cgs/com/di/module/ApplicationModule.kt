/*
 *    Copyright (C) 2018 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package absa.cgs.com.di.module

import android.app.Application
import android.content.Context


import absa.cgs.com.di.annotation.ApplicationContext
import android.app.Activity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.SharedPreferences


/**
 * Created by amitshekhar on 13/01/17.
 */
@Module
class ApplicationModule(protected val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }


    @Singleton
    @Provides
    fun context(): Context {
        return mApplication
    }


    @Provides
    fun provideSharedPrefs(): SharedPreferences {
        return mApplication.getSharedPreferences("cabelsoft-prefs", Context.MODE_PRIVATE)
    }


}