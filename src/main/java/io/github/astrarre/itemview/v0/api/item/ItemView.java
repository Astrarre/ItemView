package io.github.astrarre.itemview.v0.api.item;

import io.github.astrarre.itemview.platform.fabric.FabricItemViews;

import net.minecraft.item.ItemStack;

/**
 * do NOT implement this interface!
 *
 * an unmodifiable view of an ItemStack
 */
public interface ItemView extends ItemKey {
	ItemView EMPTY = FabricItemViews.create(null);

	int getCount();

	ItemView copy();

	/**
	 * @return true if the item, count and nbt tags are equal
	 */
	boolean equals(ItemView view);

	boolean equals(ItemStack stack);
}
