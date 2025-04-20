package fun.xffc.goatedcheese.datagen.lang;

import fun.xffc.goatedcheese.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EnglishLangProvider extends FabricLanguageProvider {
    public EnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapper, TranslationBuilder builder) {
        builder.add(ModItems.GOAT_CHEESE, "Goat cheese");
        builder.add(ModItems.GOAT_MILK_BUCKET, "Goat milk bucket");
    }
}
