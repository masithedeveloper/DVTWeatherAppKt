package com.dvt.weatherapp.app

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dvt.weatherapp.app.ui.dashboard.PresentWeatherViewState
import com.dvt.weatherapp.app.utils.domain.Status
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by Masi on 2019-12-20
 */

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class PresentWeatherViewStateTest {

    @Test
    fun `should return loading true when status is loading`() {
        // Given
        val givenViewState =
            PresentWeatherViewState(status = Status.LOADING)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `should not return loading false when status is error`() {
        // Given
        val givenViewState =
            PresentWeatherViewState(status = Status.SUCCESS)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should not return loading false when status is success`() {
        // Given
        val givenViewState = PresentWeatherViewState(status = ERROR)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should return correct error message when status is error`() {
        // Given
        val givenViewState =
            PresentWeatherViewState(
                status = ERROR,
                error = "500 Internal Server Error"
            )

        // When
        val actualResult = givenViewState.getErrorMessage()

        // Then
        Truth.assertThat(actualResult).isEqualTo("500 Internal Server Error")
    }

    @Test
    fun `should return true for error placeholder visibility  when status is error`() {
        // Given
        val givenViewState =
            PresentWeatherViewState(
                status = Status.ERROR,
                error = "500 Internal Server Error"
            )

        // When
        val actualResult = givenViewState.shouldShowErrorMessage()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }
}
