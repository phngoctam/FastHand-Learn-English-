package com.example.game1;

import android.content.Context;
import android.media.MediaPlayer;

public class CommonMethod {
	public static MediaPlayer player;
	public static void SoundPlayer(Context ctx, int raw_id) {
		player = MediaPlayer.create(ctx, raw_id);
		player.setLooping(false); // Set looping
		player.setVolume(100, 100);

		// player.release();
		player.start();
	}
}