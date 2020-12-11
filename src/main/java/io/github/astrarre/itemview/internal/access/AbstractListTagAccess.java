package io.github.astrarre.itemview.internal.access;

import io.github.astrarre.itemview.v0.api.nbt.NbtType;

public interface AbstractListTagAccess {
	Object itemview_getListTag(NbtType<?> type);
}
