package com.guagua.wan.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

/**
 * Copyright (C), 2020-2020, guagua
 * Author: lixiangchao
 * Date: 2020/6/5 13:46
 * Version: 1.0.0
 * Description:
 * History:
 * <author> <time> <version> <desc>
 */
class MyContextWrapper(base: Context?) : ContextWrapper(base) {
    companion object {

        fun wrap(context: Context, language: String): ContextWrapper {
            var context = context
            val configuration = context.resources.configuration
            var sysLocale: Locale? = null
            sysLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getSystemLocale(configuration)
            } else {
                getSystemLocaleLegacy(configuration)
            }
            if (!language.isNullOrEmpty() && !sysLocale?.language.equals(language)) {
                val locale = Locale(language)
                Locale.setDefault(locale)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    setSystemLocale(configuration, locale)
                } else {
                    setSystemLocaleLegacy(configuration, locale)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    context = context.createConfigurationContext(configuration)
                } else {
                    context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
                }
            }

            return MyContextWrapper(context)
        }

        private fun getSystemLocaleLegacy(config: Configuration): Locale? {
            return config.locale
        }

        @RequiresApi(Build.VERSION_CODES.N)
        fun getSystemLocale(config: Configuration): Locale {
            return config.locales[0]
        }

        private fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
            config.locale = locale
        }

        private fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLocale(locale)
        }
    }
}