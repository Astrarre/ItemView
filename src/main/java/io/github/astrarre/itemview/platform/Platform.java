package io.github.astrarre.itemview.platform;

import io.github.astrarre.itemview.internal.mixin.nbt.CompoundTagMixin;
import io.github.astrarre.itemview.platform.fabric.FabricItemViews;
import io.github.astrarre.itemview.v0.api.nbt.NBTagView;

import net.minecraft.nbt.CompoundTag;

/**
 * platform specific implementations
 */
public interface Platform {
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
