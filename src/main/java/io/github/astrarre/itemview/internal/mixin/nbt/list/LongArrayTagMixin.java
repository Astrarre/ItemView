package io.github.astrarre.itemview.internal.mixin.nbt.list;

import io.github.astrarre.itemview.internal.access.AbstractListTagAccess;
import io.github.astrarre.itemview.internal.nbt.list.LongArrayView;
import io.github.astrarre.itemview.v0.api.nbt.NbtType;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.nbt.LongArrayTag;

@Mixin (LongArrayTag.class)
public class LongArrayTagMixin implements AbstractListTagAccess {
	private Object view;

	@Override
	public Object itemview_getListTag(NbtType<?> type) {
		if(type != NbtType.LONG_ARRAY) {
			throw new IllegalArgumentException("type is not of int array!");
		}

		Object view = this.view;
		if (view == null) {
			this.view = view = LongArrayView.create((LongArrayTag) (Object) this);
		}
		return view;
	}
}
