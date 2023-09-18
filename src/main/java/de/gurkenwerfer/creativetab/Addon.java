package de.gurkenwerfer.creativetab;

import com.mojang.logging.LogUtils;
import de.gurkenwerfer.creativetab.commands.AdditionsItemGroupCommand;
import de.gurkenwerfer.creativetab.modules.AdditionsItemGroup;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Gadgetry");

    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("creativetab", "general"));

    @Override
    public void onInitialize() {
        LOG.info("Initializing Gurken's Creative Tab Addon");
            Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(Items.SEA_PICKLE))
                .displayName(Text.translatable("creativetab.item-group"))
                .entries(AdditionsItemGroup::register)
                .build());

        // Modules
        //Modules.get().add(new ModuleExample());

        // Commands
        Commands.add(new AdditionsItemGroupCommand());

    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}
