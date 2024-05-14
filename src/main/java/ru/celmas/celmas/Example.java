package ru.celmas.celmas;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import ru.celmas.celmas.event.BoundingBoxCallback;

public class Example implements ModInitializer {

  @Override
  public void onInitialize() {
    BoundingBoxCallback.EVENT.register(of(new BlockBox(0, 0, 0, 74, 64, 13)));
    BoundingBoxCallback.EVENT.register(of(new BlockBox(74, 0, 13, 76, 64, -2)));
  }

  private BoundingBoxCallback of(BlockBox blockBox) {
    return collides -> {
      ImmutableList.Builder<VoxelShape> builder = ImmutableList.builder();
      builder.add(VoxelShapes.cuboid(Box.from(blockBox)));
      builder.add(collides.toArray(new VoxelShape[0]));
      return builder.build();
    };
  }
}
