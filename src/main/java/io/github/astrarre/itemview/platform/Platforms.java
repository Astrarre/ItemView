package io.github.astrarre.itemview.platform;

public class Platforms {
	public static final Platform ACTIVE;
	static {
		try {
			Class.forName("net.fabricmc.loader.FabricLoader");
			ACTIVE = new Platform.Fabric();
		} catch (Exception e) {
			throw new IllegalStateException("Unknown platform!");
		}
	}
}
