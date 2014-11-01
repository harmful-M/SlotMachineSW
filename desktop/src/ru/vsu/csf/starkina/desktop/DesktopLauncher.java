package ru.vsu.csf.starkina.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.vsu.csf.starkina.SlotMachine;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = SlotMachine.WIDTH;
        config.height = SlotMachine.HEIGHT;
		new LwjglApplication(new SlotMachine(), config);
	}
}
