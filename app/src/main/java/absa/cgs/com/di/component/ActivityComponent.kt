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


import absa.cgs.com.di.annotation.PerActivity
import absa.cgs.com.di.module.ActivityModule
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import absa.cgs.com.ui.screens.register.CustomerRegister
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.DialogUtils
import dagger.Component


/**
 * Created by amitshekhar on 13/01/17.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(customerRegister: CustomerRegister)

    fun commonUtils(): CommonUtils
    fun dialogUtils(): DialogUtils


}
