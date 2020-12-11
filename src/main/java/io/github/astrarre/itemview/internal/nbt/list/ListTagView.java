package io.github.astrarre.itemview.internal.nbt.list;

import java.util.AbstractList;

import io.github.astrarre.itemview.platform.fabric.ItemViews;
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
	 * @see ItemViews#view(ListTag, NbtType)
	 * @deprecated internal
	 */
	@Deprecated
	public static ListTagView create(ListTag tag, NbtType<?> component) {
		return new ListTagView(tag, component);
	}

	@Override
	public Object get(int index) {
		return ItemViews.view(this.tag.get(index), this.component);
	}

	@Override
	public int size() {
		return this.tag.size();
	}
}
