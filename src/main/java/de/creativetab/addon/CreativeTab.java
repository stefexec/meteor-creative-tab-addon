package de.creativetab.addon;

import com.mojang.logging.LogUtils;
import de.creativetab.addon.commands.AdditionsItemGroupCommand;
import de.creativetab.addon.modules.AdditionsItemGroup;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
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

public class CreativeTab extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();

    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of("creativetab", "general"));

    public static void gameInit() {
        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.SEA_PICKLE))
            .displayName(Text.translatable("creativetab.item-group"))
            .entries(AdditionsItemGroup::register)
            .build());
    }

    @Override
    public void onInitialize() {
        LOG.info("Initializing Gurkeor Creative Tab");

        // Modules
        // Modules.get().add(new ModuleExample());

        // Commands
        Commands.add(new AdditionsItemGroupCommand());

    }

    @Override
    public String getPackage() {
        return "de.gurkenwerfer.creativetab";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("stefexec", "meteor-addon-template");
    }
}
