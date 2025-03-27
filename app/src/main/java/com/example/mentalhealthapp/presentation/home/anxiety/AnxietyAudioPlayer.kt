package com.example.mentalhealthapp.presentation.home.anxiety

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.mentalhealthapp.R

class AnxietyAudioPlayer(private val context: Context) {
    private var player: ExoPlayer? = null

    init {
        player = ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${R.raw.calming_audio}")
            setMediaItem(mediaItem)
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            prepare()
            playWhenReady = false
        }
    }

    fun play() {
        player?.playWhenReady = true
    }

    fun pause() {
        player?.playWhenReady = false
    }

    fun stop() {
        player?.stop()
        player?.seekTo(0)
    }

    fun setVolume(volume: Float) {
        player?.volume = volume
    }

    fun release() {
        player?.release()
        player = null
    }
}