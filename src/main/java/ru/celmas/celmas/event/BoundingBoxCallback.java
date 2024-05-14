package ru.celmas.celmas.event;

import java.util.List;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.shape.VoxelShape;

public interface BoundingBoxCallback {

  Event<BoundingBoxCallback> EVENT =
      EventFactory.createArrayBacked(
          BoundingBoxCallback.class,
          (listeners) ->
              (collides) -> {
                // collides is immutable
                for (BoundingBoxCallback listener : listeners) {
                  collides = listener.addBoundingBox(collides);
                }
                return collides;
              });

  List<VoxelShape> addBoundingBox(List<VoxelShape> collides);
}
