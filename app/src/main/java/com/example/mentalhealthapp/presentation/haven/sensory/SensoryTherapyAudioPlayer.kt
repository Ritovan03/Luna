package com.example.mentalhealthapp.presentation.haven.sensory

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class SensoryTherapyAudioPlayer(
    private val context: Context,
    private val audioResourceId: Int
) {
    private var player: ExoPlayer? = null

    init {
        player = ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${audioResourceId}")
            setMediaItem(mediaItem)
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            prepare()
            playWhenReady = false
        }
    }

    fun play() {
        player?.playWhenReady = true
    }

    fun stop() {
        player?.stop()
        player?.seekTo(0)
    }

    fun release() {
        player?.release()
        player = null
    }
}