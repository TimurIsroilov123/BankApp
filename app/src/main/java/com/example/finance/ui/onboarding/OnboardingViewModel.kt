package com.example.finance.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OnboardingViewModel: ViewModel() {

    private val imageEventChannel = Channel<ImageEvent>()
    val imageEvent = imageEventChannel.receiveAsFlow()

    var currentImg = 1

    fun onNextClicked() = viewModelScope.launch {
        if (currentImg < 3) {
            imageEventChannel.send(ImageEvent.Next)
            currentImg++
        } else {
            imageEventChannel.send(ImageEvent.Finish)
        }
    }

    fun onSkipClicked() = viewModelScope.launch {
        imageEventChannel.send(ImageEvent.Skip)
    }


    sealed class ImageEvent {
        object Skip: ImageEvent()
        object Next: ImageEvent()
        object Finish: ImageEvent()
    }
}

