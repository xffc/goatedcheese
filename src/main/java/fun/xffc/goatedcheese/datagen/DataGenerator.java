package fun.xffc.goatedcheese.datagen;

import fun.xffc.goatedcheese.datagen.lang.*;
import fun.xffc.goatedcheese.datagen.model.ModelProvider;
import fun.xffc.goatedcheese.datagen.recipe.RecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(EnglishLangProvider::new);
        pack.addProvider(RussianLangProvider::new);
        pack.addProvider(ModelProvider::new);
        pack.addProvider(RecipeProvider::new);
    }
}

