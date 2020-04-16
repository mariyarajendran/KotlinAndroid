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

package absa.cgs.com.di.component

import android.app.Application
import android.content.Context


import javax.inject.Singleton

import absa.cgs.com.MyApplication
import absa.cgs.com.di.annotation.ApplicationContext
import absa.cgs.com.di.module.ApplicationModule
import absa.cgs.com.ui.screens.apis.addexpenseapicall.AddExpenseInteractor
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.DeleteExpenseInteractor
import absa.cgs.com.ui.screens.apis.loginapicall.LoginInteractor
import absa.cgs.com.ui.screens.apis.logoutapicall.LogoutInteractor
import absa.cgs.com.ui.screens.apis.readexpenseapicall.ReadExpenseInteractor
import absa.cgs.com.ui.screens.apis.readprofileapicall.ReadProfileInteractor
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.UpdateExpenseInteractor
import absa.cgs.com.ui.screens.apis.updateprofileapicall.UpdateProfileInteractor
import absa.cgs.com.ui.screens.mainbaseactivity.MainInteractor
import dagger.Component

/**
 * Created by amitshekhar on 13/01/17.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(myApplication: MyApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application
    fun mainInteractor(): MainInteractor
    fun loginInteractor(): LoginInteractor
    fun logoutInteractor(): LogoutInteractor
    fun addExpenseInteractor(): AddExpenseInteractor
    fun updateExpenseInteractor(): UpdateExpenseInteractor
    fun deleteExpenseInteractor(): DeleteExpenseInteractor
    fun updateProfileInteractor(): UpdateProfileInteractor
    fun readProfileInteractor(): ReadProfileInteractor
    fun readExpenseInteractor(): ReadExpenseInteractor




    // DataManager dataManager();

}
