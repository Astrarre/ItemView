package io.github.astrarre.itemview.internal.mixin.nbt;

import io.github.astrarre.itemview.fabric.FabricTags;
import io.github.astrarre.itemview.v0.api.nbt.NBTagView;
import io.github.astrarre.itemview.v0.api.nbt.NbtType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.nbt.AbstractNumberTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

@Mixin(CompoundTag.class)
public abstract class CompoundTagMixin implements NBTagView {
	@Shadow public abstract @Nullable Tag shadow$get(String key);

	@Override
	public byte getByte(String path, byte def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.BYTE);
		if(tag != null) {
			return tag.getByte();
		}
		return def;
	}

	@Override
	public boolean getBool(String path, boolean def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.BOOL);
		if(tag != null) {
			return tag.getByte() != 0;
		}
		return def;
	}

	@Override
	public short getShort(String path, short def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.SHORT);
		if(tag != null) {
			return tag.getShort();
		}
		return def;
	}

	@Override
	public char getChar(String path, char def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.CHAR);
		if(tag != null) {
			return (char) tag.getShort();
		}
		return def;
	}

	@Override
	public int getInt(String path, int def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.INT);
		if(tag != null) {
			return tag.getInt();
		}
		return def;
	}

	@Override
	public float getFloat(String path, float def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.FLOAT);
		if(tag != null) {
			return tag.getFloat();
		}
		return def;
	}

	@Override
	public long getLong(String path, long def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.LONG);
		if(tag != null) {
			return tag.getLong();
		}
		return def;
	}

	@Override
	public double getDouble(String path, double def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.DOUBLE);
		if(tag != null) {
			return tag.getDouble();
		}
		return def;
	}

	@Override
	public Number getNumber(String path, Number def) {
		AbstractNumberTag tag = this.itemview_getTag(path, NbtType.DOUBLE);
		if(tag != null) {
			return tag.getNumber();
		}
		return def;
	}

	@Nullable
	private AbstractNumberTag itemview_getTag(String path, NbtType<?> type) {
		Tag tag = this.shadow$get(path);
		if(tag != null && tag.getType() == type.getInternalType() && tag instanceof AbstractNumberTag) {
			return ((AbstractNumberTag)tag);
		}
		return null;
	}

	@Override
	public String getString(String path, String def) {
		Tag tag = this.shadow$get(path);
		if(tag != null && tag.getType() == NbtType.STRING.getInternalType()) {
			return tag.asString();
		}
		return def;
	}

	@Override
	public Object get(String key) {
		return FabricTags.wrap(this.shadow$get(key), NbtType.ANY);
	}

	@Override
	public <T> T get(String path, NbtType<T> type, T def) {
		return null;
	}
}
