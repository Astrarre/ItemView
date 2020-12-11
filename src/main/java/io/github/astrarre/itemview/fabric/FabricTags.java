package io.github.astrarre.itemview.fabric;

import java.util.List;

import io.github.astrarre.itemview.internal.access.AbstractListTagAccess;
import io.github.astrarre.itemview.v0.api.nbt.NbtType;
import it.unimi.dsi.fastutil.bytes.ByteList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.LongList;

import net.minecraft.nbt.AbstractNumberTag;
import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

/**
 * A utility class for fabric mods to deal with nbt tags
 */
public class FabricTags {
	@SuppressWarnings ("unchecked")
	public static <T> T wrap(Tag tag, NbtType<T> type) {
		Object ret = null;
		if (tag instanceof AbstractNumberTag) {
			Number number = ((AbstractNumberTag) tag).getNumber();
			if (type == NbtType.BOOL) {
				ret = number.byteValue() != 0;
			} else if (type == NbtType.CHAR) {
				ret = (char) number.shortValue();
			} else {
				ret = number;
			}
		} else if (tag instanceof AbstractListTagAccess) {
			ret = ((AbstractListTagAccess) tag).itemview_getListTag(type);
		} else if (tag instanceof CompoundTag) {
			// compound tag implements NBTagView, shhh
			ret = tag;
		} else if (tag instanceof StringTag) {
			ret = tag.asString();
		}

		if (ret == null) {
			throw new IllegalArgumentException("unknown tag type " + tag + "(" + tag.getClass() + ")");
		}

		if (type.getClassType().isInstance(ret)) {
			return (T) ret;
		} else {
			throw new IllegalArgumentException(tag.getClass() + " != " + type.getClassType());
		}
	}

	public static <T> List<T> wrap(ListTag tags, NbtType<T> componentType) {
		return (List<T>) ((AbstractListTagAccess) tags).itemview_getListTag(componentType);
	}

	public static <T> List<T> createCopy(ListTag tag, NbtType<T> componentType) {
		return wrap(tag.copy(), componentType);
	}

	public static ByteList createCopy(ByteArrayTag tag) {
		return wrap((ByteArrayTag) tag.copy());
	}

	public static ByteList wrap(ByteArrayTag tags) {
		return (ByteList) ((AbstractListTagAccess) tags).itemview_getListTag(NbtType.BYTE_ARRAY);
	}

	public static IntList createCopy(IntArrayTag tag) {
		return wrap(tag.copy());
	}

	public static IntList wrap(IntArrayTag tags) {
		return (IntList) ((AbstractListTagAccess) tags).itemview_getListTag(NbtType.INT_ARRAY);
	}

	public static LongList createCopy(LongArrayTag tag) {
		return wrap(tag.copy());
	}

	public static LongList wrap(LongArrayTag tags) {
		return (LongList) ((AbstractListTagAccess) tags).itemview_getListTag(NbtType.LONG_ARRAY);
	}
}
