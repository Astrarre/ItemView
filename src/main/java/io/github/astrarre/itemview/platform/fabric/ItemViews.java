package io.github.astrarre.itemview.platform.fabric;

import java.util.List;

import io.github.astrarre.itemview.internal.access.AbstractListTagAccess;
import io.github.astrarre.itemview.v0.api.item.ItemView;
import io.github.astrarre.itemview.v0.api.nbt.NBTagView;
import io.github.astrarre.itemview.v0.api.nbt.NbtType;
import it.unimi.dsi.fastutil.bytes.ByteList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.LongList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.AbstractNumberTag;
import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;

/**
 * A utility class for fabric mods
 */
@SuppressWarnings ("ConstantConditions")
public class ItemViews {
	/**
	 * @return an unmodifiable ItemStack view
	 */
	public static ItemView view(ItemStack stack) {
		return (ItemView) (Object) stack;
	}

	/**
	 * @return an immutable ItemStack view
	 */
	public static ItemView immutableView(ItemStack stack) {
		return view(stack.copy());
	}

	/**
	 * @return an immutable copy of the ItemView
	 */
	public static ItemView immutable(ItemView view) {
		return view(from(view));
	}


	/**
	 * @return copy an ItemView to an ItemStack
	 */
	public static ItemStack from(ItemView view) {
		return fromUnsafe(view).copy();
	}

	/**
	 * @return an unmodifiable compound tag view
	 */
	public static NBTagView view(CompoundTag tag) {
		return (NBTagView) tag;
	}

	/**
	 * @return an immutable compound tag view
	 */
	public static NBTagView immutableView(CompoundTag tag) {
		return (NBTagView) tag.copy();
	}

	public static NBTagView immutable(NBTagView view) {
		return view(from(view));
	}

	/**
	 * @return the NBTagView converted to a compound tag
	 */
	public static CompoundTag from(NBTagView view) {
		return fromUnsafe(view).copy();
	}

	/**
	 * @deprecated unsafe
	 * @see #from(NBTagView)
	 */
	@Deprecated
	public static CompoundTag fromUnsafe(NBTagView view) {
		// todo check for ImmutableCompoundTag when that is implemented
		return (CompoundTag) view;
	}

	/**
	 * @deprecated unsafe
	 * @see #from(ItemView)
	 */
	@Deprecated
	public static ItemStack fromUnsafe(ItemView view) {
		// todo check for ImmutableItemStack when that is implemented
		return (ItemStack) (Object) view;
	}

	public static <T> T immutableView(Tag tag, NbtType<T> type) {
		return view(tag.copy(), type);
	}

	@SuppressWarnings ("unchecked")
	public static <T> T view(Tag tag, NbtType<T> type) {
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

	public static <T> List<T> immutableView(ListTag tag, NbtType<T> componentType) {
		return view(tag.copy(), componentType);
	}

	public static <T> List<T> view(ListTag tags, NbtType<T> componentType) {
		return (List<T>) ((AbstractListTagAccess) tags).itemview_getListTag(componentType);
	}

	public static ByteList immutableView(ByteArrayTag tag) {
		return view((ByteArrayTag) tag.copy());
	}

	public static ByteList view(ByteArrayTag tags) {
		return (ByteList) ((AbstractListTagAccess) tags).itemview_getListTag(NbtType.BYTE_ARRAY);
	}

	public static IntList immutableView(IntArrayTag tag) {
		return view(tag.copy());
	}

	public static IntList view(IntArrayTag tags) {
		return (IntList) ((AbstractListTagAccess) tags).itemview_getListTag(NbtType.INT_ARRAY);
	}

	public static LongList immutableView(LongArrayTag tag) {
		return view(tag.copy());
	}

	public static LongList view(LongArrayTag tags) {
		return (LongList) ((AbstractListTagAccess) tags).itemview_getListTag(NbtType.LONG_ARRAY);
	}
}
