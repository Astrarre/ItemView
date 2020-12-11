package io.github.astrarre.itemview.internal.nbt.list;

import java.util.AbstractList;

import io.github.astrarre.itemview.fabric.FabricTags;
import io.github.astrarre.itemview.v0.api.nbt.NbtType;

import net.minecraft.nbt.ListTag;

public class ListTagView extends AbstractList<Object> {
	private final ListTag tag;
	private final NbtType<?> component;

	public ListTagView(ListTag tag, NbtType<?> component) {
		this.tag = tag;
		this.component = component;
	}

	/**
	 * @see FabricTags#wrap(ListTag)
	 * @deprecated internal
	 */
	@Deprecated
	public static ListTagView create(ListTag tag, NbtType<?> component) {
		return new ListTagView(tag, component);
	}

	@Override
	public Object get(int index) {
		return FabricTags.wrap(this.tag.get(index), this.component);
	}

	@Override
	public int size() {
		return this.tag.size();
	}
}
