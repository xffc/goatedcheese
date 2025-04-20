package fun.xffc.goatedcheese.datagen.recipe;

import fun.xffc.goatedcheese.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

//? if 1.21.4 {
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;

//?} else if 1.21.1 {
/*import net.minecraft.data.server.recipe.RecipeExporter;
*///?}

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    //? if 1.21.4 {
    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapper, RecipeExporter exporter) {
        return new RecipeGenerator(wrapper, exporter) {
            @Override
            public void generate() {
                //RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                offerSmelting(
                        List.of(ModItems.GOAT_MILK_BUCKET),
                        RecipeCategory.FOOD,
                        ModItems.GOAT_CHEESE,
                        0.1f,
                        600,
                        null
                );

                offerMultipleOptions(
                        RecipeSerializer.SMOKING,
                        SmokingRecipe::new,
                        List.of(ModItems.GOAT_MILK_BUCKET),
                        RecipeCategory.FOOD,
                        ModItems.GOAT_CHEESE,
                        0.1f,
                        300,
                        null,
                        "_from_smoking"
                );
            }
        };
    }
    //?} else if 1.21.1 {
    
    /*@Override
    public void generate(RecipeExporter recipeExporter) {
        offerSmelting(
                recipeExporter,
                List.of(ModItems.GOAT_MILK_BUCKET),
                RecipeCategory.FOOD,
                ModItems.GOAT_CHEESE,
                0.1f,
                600,
                null
        );

        offerMultipleOptions(
                recipeExporter,
                RecipeSerializer.SMOKING,
                SmokingRecipe::new,
                List.of(ModItems.GOAT_MILK_BUCKET),
                RecipeCategory.FOOD,
                ModItems.GOAT_CHEESE,
                0.1f,
                300,
                null,
                "_from_smoking"
        );
    }
     
    *///?}

    @Override
    public String getName() {
        return "GoatedcheeseRecipeProvider";
    }
}
