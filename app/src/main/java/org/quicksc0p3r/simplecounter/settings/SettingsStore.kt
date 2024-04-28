package org.quicksc0p3r.simplecounter.settings

import android.content.Context
import android.os.Build
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.settingsStore by preferencesDataStore("settings")

class SettingsManager(context: Context) {
    private val settingsStore = context.settingsStore

    companion object {
        val colorSetting = intPreferencesKey("colorSetting")
        val lightDarkSetting = intPreferencesKey("lightDarkSetting")
        val oledModeSetting = booleanPreferencesKey("oledModeSetting")
        val counterCardStyleSetting = intPreferencesKey("counterCardStyleSetting")
        val resetSnackbarTipWasShown = booleanPreferencesKey("resetSnackbarTipWasShown")
        val volumeKeysSnackbarTipWasShown = booleanPreferencesKey("volumeKeysSnackbarTipWasShown")
        val hapticFeedbackOnTouch = booleanPreferencesKey("hapticFeedbackOnTouch")
        val hapticFeedbackOnVolume = booleanPreferencesKey("hapticFeedbackOnVolume")
    }

    suspend fun storeColorSetting(newSetting: Int) {
        settingsStore.edit { it[colorSetting] = newSetting }
    }

    suspend fun storeLightDarkSetting(newSetting: Int) {
        settingsStore.edit { it[lightDarkSetting] = newSetting }
    }

    suspend fun storeOledModeSetting(newSetting: Boolean) {
        settingsStore.edit { it[oledModeSetting] = newSetting }
    }

    suspend fun storeCounterCardStyleSetting(newSetting: Int) {
        settingsStore.edit { it[counterCardStyleSetting] = newSetting }
    }

    suspend fun storeResetSnackbarTipWasShown(newSetting: Boolean) {
        settingsStore.edit { it[resetSnackbarTipWasShown] = newSetting }
    }

    suspend fun storeVolumeKeysSnackbarTipWasShown(newSetting: Boolean) {
        settingsStore.edit { it[volumeKeysSnackbarTipWasShown] = newSetting }
    }

    suspend fun storeHapticFeedbackOnTouchSetting(newSetting: Boolean) {
        settingsStore.edit { it[hapticFeedbackOnTouch] = newSetting }
    }

    suspend fun storeHapticFeedbackOnVolumeSetting(newSetting: Boolean) {
        settingsStore.edit { it[hapticFeedbackOnVolume] = newSetting }
    }

    val colorSettingFlow = settingsStore.data.map {
        it[colorSetting] ?:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            ColorSetting.SYSTEM.ordinal
        else ColorSetting.PURPLE.ordinal
    }
    val lightDarkSettingFlow = settingsStore.data.map {
        it[lightDarkSetting] ?:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            LightDarkSetting.SYSTEM.ordinal
        else LightDarkSetting.LIGHT.ordinal
    }
    val oledModeSettingFlow = settingsStore.data.map {
        it[oledModeSetting] ?: false
    }
    val counterCardStyleSettingFlow = settingsStore.data.map {
        it[counterCardStyleSetting] ?: CounterCardStyleSetting.NORMAL.ordinal
    }
    val resetSnackbarTipWasShownFlow = settingsStore.data.map {
        it[resetSnackbarTipWasShown] ?: false
    }
    val volumeKeysSnackbarTipWasShownFlow = settingsStore.data.map {
        it[volumeKeysSnackbarTipWasShown] ?: false
    }
    val hapticFeedbackOnTouchFlow = settingsStore.data.map {
        it[hapticFeedbackOnTouch] ?: false
    }
    val hapticFeedbackOnVolumeFlow = settingsStore.data.map {
        it[hapticFeedbackOnVolume] ?: false
    }
}