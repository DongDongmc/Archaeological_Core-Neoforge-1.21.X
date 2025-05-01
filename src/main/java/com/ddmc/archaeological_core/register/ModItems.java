package com.ddmc.archaeological_core.register;

import com.ddmc.archaeological_core.Archaeological_Core;
import com.ddmc.archaeological_core.test.TestBrushItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Archaeological_Core.MODID);

    public static final DeferredItem<Item> TEST_BRUSH = ITEMS.register("test_brush",
            () -> new TestBrushItem(new Item.Properties().durability(128),  1));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
