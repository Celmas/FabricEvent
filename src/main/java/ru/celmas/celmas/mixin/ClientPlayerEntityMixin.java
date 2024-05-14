package ru.celmas.celmas.mixin;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import ru.celmas.celmas.event.BoundingBoxCallback;

@Mixin(Entity.class)
public abstract class ClientPlayerEntityMixin {

  @ModifyVariable(at = @At(value = "STORE", ordinal = 0), method = "adjustMovementForCollisions*")
  private List<VoxelShape> addCollisions(List<VoxelShape> collides) {
    if ((Object) this instanceof PlayerEntity) {
      return BoundingBoxCallback.EVENT.invoker().addBoundingBox(collides);
    } else return collides;
  }
}
