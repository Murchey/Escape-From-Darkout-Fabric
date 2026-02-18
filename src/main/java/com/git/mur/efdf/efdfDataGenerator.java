package com.git.mur.efdf;

import com.git.mur.efdf.datagen.efdfModelsProvider;
import com.git.mur.efdf.datagen.efdfRecipesProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import com.git.mur.efdf.datagen.efdfZhLanguageProvider;

public class efdfDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(efdfModelsProvider::new);
        pack.addProvider(efdfZhLanguageProvider::new);
        pack.addProvider(efdfRecipesProvider::new);
    }
}
