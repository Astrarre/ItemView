package io.github.astrarre.itemview.v0.api.item;

import io.github.astrarre.itemview.platform.Platforms;
import io.github.astrarre.itemview.platform.fabric.FabricItemViews;
import io.github.astrarre.itemview.v0.api.nbt.NBTagView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * do NOT implement this interface!
 *
 * an unmodifiable view of an ItemStack
 */
public interface ItemView {
	ItemView EMPTY = FabricItemViews.create(null);
	boolean isEmpty();
	Item getItem();
	int getCount();

	/**
	 * @return serializes the ItemView
	 * @see FabricItemViews#fromTag(NBTagView)
	 */
	NBTagView toTag();

	/**
	 * @return the maximum the ItemStack (that this ItemView represents) can stack to
	 */
	int getMaxCount();
	ItemView copy();

	/**
	 * @return true if the nbt tags are equal to each other
	 */
	boolean areTagsEqual(ItemView view);

	boolean areTagsEqual(ItemStack stack);

	/**
	 * @return true if the item, count and nbt tags are equal
	 */
	boolean equals(ItemView view);

	boolean equals(ItemStack stack);

	/**
	 * @return true if the nbt tags and items are equal, it does not check max stack size!
	 */
	boolean canStackWith(ItemView view);

	boolean canStackWith(ItemStack stack);

	/**
	 * @return true if the ItemStack has nbt data
	 */
	boolean hasTag();

	/**
	 * @return the nbt data of the ItemStack
	 */
	@Nullable
	NBTagView getTag();

	/**
	 * @return the nbt tag at a given key
	 */
	@Nullable
	NBTagView getSubTag(String key);

	@NotNull
	default NBTagView getOrNewTag() {
		if(this.hasTag()) {
			return this.getTag();
		} else {
			return Platforms.ACTIVE.createNewTag();
		}
	}
}
