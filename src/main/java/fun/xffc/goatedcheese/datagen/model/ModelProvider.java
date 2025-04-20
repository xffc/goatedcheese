package fun.xffc.goatedcheese.datagen.model;

import fun.xffc.goatedcheese.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.Item;

//? if 1.21.4 {
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

//?} else if 1.21.1 {
/*import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
*///?}

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {}

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generated(generator, ModItems.GOAT_CHEESE);
        generated(generator, ModItems.GOAT_MILK_BUCKET);
    }

    private static void generated(ItemModelGenerator generator, Item item) {
        generator.register(item, Models.GENERATED);
    }
}
