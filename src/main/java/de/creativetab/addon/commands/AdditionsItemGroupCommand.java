package de.creativetab.addon.commands;


import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import de.creativetab.addon.CreativeTab;
import de.creativetab.addon.modules.AdditionsItemGroup;
import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.commands.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.io.IOException;

public class AdditionsItemGroupCommand extends Command {
    public AdditionsItemGroupCommand() {
        super("meteoradditions-item-group-add", "Add the held item to the MeteorAdditions item group", "item-group-add", "save-item");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            try {
                ItemStack stack = MeteorClient.mc.player.getInventory().getMainHandStack();
                if (stack.getItem().equals(Items.AIR)) {
                    error("You must hold an item to add it");
                    return SINGLE_SUCCESS;
                }
                AdditionsItemGroup.addItem(stack).ifPresentOrElse(
                    s -> info("Saved item: " + s),
                    () -> error("Could not add item")
                );
            } catch (IOException e) {
                CreativeTab.LOG.error("Could not add item", e);
                error("Could not add item");
            }
            return SINGLE_SUCCESS;
        });
    }
}
