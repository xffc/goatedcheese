package fun.xffc.goatedcheese.datagen.recipe;

import fun.xffc.goatedcheese.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

//? if 1.21.4 {
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.registry.Registries;

//?} else if 1.21.1 {
/*import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.item.Items;
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
                register(this, exporter);
            }
        };
    }
    //?} else if 1.21.1 {
    
    /*@Override
    public void generate(RecipeExporter exporter) {
        register(exporter);
    }
     
    *///?}

    private String hasItemCrit(Item item) {
        return
                //? if 1.21.4 {
                RecipeGenerator.hasItem(item);
                //?} else if 1.21.1 {
                /*hasItem(item);
                *///?}
    }

    private void register(/*? if 1.21.4 {*/RecipeGenerator generator, /*?}*/ RecipeExporter exporter) {
        //? if 1.21.4 {
        ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.FOOD, ModItems.GOAT_CHEESE)
        //?} else if 1.21.1 {
        /*ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.CRAFTING_TABLE, 1)
        *///?}
                .pattern("cc")
                .pattern("cc")
                .input('c', ModItems.GOAT_CHEESE_MASS)
                .criterion(hasItemCrit(ModItems.GOAT_CHEESE_MASS), /*? if 1.21.4 {*/generator./*?}*/conditionsFromItem(ModItems.GOAT_CHEESE_MASS))
                .offerTo(exporter);

        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(ModItems.GOAT_MILK_BUCKET),
                RecipeCategory.FOOD,
                ModItems.GOAT_CHEESE_MASS, 0.1f, 300
        )
                .criterion(hasItemCrit(ModItems.GOAT_MILK_BUCKET), /*? if 1.21.4 {*/generator./*?}*/conditionsFromItem(ModItems.GOAT_MILK_BUCKET))
                .offerTo(exporter);

        CookingRecipeJsonBuilder.createSmoking(
                Ingredient.ofItems(ModItems.GOAT_MILK_BUCKET),
                RecipeCategory.FOOD,
                ModItems.GOAT_CHEESE_MASS, 0.1f, 150
        )
                .criterion(hasItemCrit(ModItems.GOAT_MILK_BUCKET), /*? if 1.21.4 {*/generator./*?}*/conditionsFromItem(ModItems.GOAT_MILK_BUCKET))
                .offerTo(exporter);
    }

    @Override
    public String getName() {
        return "GoatedcheeseRecipeProvider";
    }
}
