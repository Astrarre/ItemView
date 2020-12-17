package io.github.astrarre.itemview.platform;

import io.github.astrarre.itemview.platform.fabric.FabricItemViews;
import io.github.astrarre.itemview.v0.api.nbt.NBTagView;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Util;

/**
 * platform specific implementations
 */
public interface Platform {
	Platform ACTIVE = Util.make(() -> {
		try {
			Class.forName("net.fabricmc.loader.FabricLoader");
			return new Platform.Fabric();
		} catch (Exception e) {
			throw new IllegalStateException("Unknown platform!");
		}
	});

	class Fabric implements Platform {
		@Override
		public NBTagView createNewTag() {
			return FabricItemViews.view(new CompoundTag());
		}
	}

	/**
	 * @return an empty tag view
	 */
	NBTagView createNewTag();
}
