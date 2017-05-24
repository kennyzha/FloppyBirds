package com.zhaokenny.floppybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zhaokenny.floppybird.FloppyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FloppyBird.WIDTH;
		config.height = FloppyBird.HEIGHT;
		config.title = FloppyBird.TITLE;
		new LwjglApplication(new FloppyBird(), config);
	}
}
