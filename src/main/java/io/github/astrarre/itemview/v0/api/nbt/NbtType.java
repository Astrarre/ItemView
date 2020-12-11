package io.github.astrarre.itemview.v0.api.nbt;

import java.util.List;

import it.unimi.dsi.fastutil.bytes.ByteList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.LongList;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings ({
		"unchecked",
		"rawtypes"
})
public final class NbtType<T> {
	public static final NbtType<Object> ANY = new NbtType<>(Object.class, 0, -1);
	public static final NbtType<List<Object>> ANY_LIST = NbtType.listOf(NbtType.ANY);

	// start
	public static final NbtType<Boolean> BOOL = new NbtType<>(boolean.class, -3, 1);
	public static final NbtType<Byte> BYTE = new NbtType<>(byte.class, 1);
	public static final NbtType<Short> SHORT = new NbtType<>(short.class, 2);
	public static final NbtType<Character> CHAR = new NbtType<>(char.class, -2, 2);
	public static final NbtType<Integer> INT = new NbtType<>(int.class, 3);
	public static final NbtType<Long> LONG = new NbtType<>(long.class, 4);
	public static final NbtType<Float> FLOAT = new NbtType<>(float.class, 5);
	public static final NbtType<Double> DOUBLE = new NbtType<>(double.class, 6);
	public static final NbtType<ByteList> BYTE_ARRAY = new NbtType<>(ByteList.class, 7);
	public static final NbtType<String> STRING = new NbtType<>(String.class, 8);
	// list
	public static final NbtType<NBTagView> TAG = new NbtType<>(NBTagView.class, 10);
	public static final NbtType<IntList> INT_ARRAY = new NbtType<>(IntList.class, 11);
	public static final NbtType<LongList> LONG_ARRAY = new NbtType<>(LongList.class, 12);
	// gap
	public static final NbtType<Number> NUMBER = new NbtType<>(Number.class,99);

	// todo byte array and whatever
	private final Class<T> cls;
	@Nullable private final NbtType<?> component;
	private final int type, internalType;

	private NbtType(Class<T> cls, int type) {
		this(cls, null, type, type);
	}

	private NbtType(Class<T> cls, @Nullable NbtType<?> component, int type, int internalType) {
		this.cls = cls;
		this.type = type;
		this.component = component;
		this.internalType = internalType;
	}

	private NbtType(Class<T> cls, int type, int internalType) {
		this(cls, null, type, internalType);
	}

	public static <T> NbtType<List<T>> listOf(NbtType<T> type) {
		return new NbtType(List.class, type, type.type, 9);
	}

	public Class<T> getClassType() {
		return this.cls;
	}

	/**
	 * @return a unique integer for each root component type (lists will return their root type)
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * @deprecated internal
	 */
	@Deprecated
	public int getInternalType() {
		return this.internalType;
	}

	/**
	 * @deprecated internal
	 */
	@Deprecated
	public boolean internalTypeEquals(int type) {
		return this.internalType == -1 || this.internalType == type;
	}

	@Nullable
	public NbtType<?> getRootComponent() {
		NbtType<?> component = this.component;
		if(component == null) {
			return null;
		}

		while (true) {
			NbtType type = component.getComponent();
			if(type == null) {
				return component;
			} else {
				component = type;
			}
		}
	}

	/**
	 * @return null if not a list type
	 */
	@Nullable
	public NbtType<?> getComponent() {
		return this.component;
	}
}
