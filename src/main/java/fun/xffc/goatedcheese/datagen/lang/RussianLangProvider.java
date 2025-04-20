package fun.xffc.goatedcheese.datagen.lang;

import fun.xffc.goatedcheese.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RussianLangProvider extends FabricLanguageProvider {
    public RussianLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "ru_ru", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapper, TranslationBuilder builder) {
        builder.add(ModItems.GOAT_CHEESE, "Козий сыр");
        builder.add(ModItems.GOAT_MILK_BUCKET, "Ведро козьего молока");
    }
}
