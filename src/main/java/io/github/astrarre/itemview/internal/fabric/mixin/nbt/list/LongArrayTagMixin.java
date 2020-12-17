package io.github.astrarre.itemview.internal.fabric.mixin.nbt.list;

import io.github.astrarre.itemview.internal.fabric.access.AbstractListTagAccess;
import io.github.astrarre.itemview.internal.fabric.nbt.list.LongArrayView;
import io.github.astrarre.itemview.v0.api.nbt.NBTType;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.nbt.LongArrayTag;

@Mixin (LongArrayTag.class)
public class LongArrayTagMixin implements AbstractListTagAccess {
	private Object view;

	@Override
	public Object itemview_getListTag(NBTType<?> type) {
		if(type != NBTType.LONG_ARRAY) {
			throw new IllegalArgumentException("type is not of int array!");
		}

		Object view = this.view;
		if (view == null) {
			this.view = view = LongArrayView.create((LongArrayTag) (Object) this);
		}
		return view;
	}
}
