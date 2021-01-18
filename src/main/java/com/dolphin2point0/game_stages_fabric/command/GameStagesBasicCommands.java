package com.dolphin2point0.game_stages_fabric.command;

import com.dolphin2point0.game_stages_fabric.api.GameStagesAPI;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.HashSet;

import static net.minecraft.command.argument.EntityArgumentType.getPlayer;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static net.minecraft.command.argument.EntityArgumentType.players;

public class GameStagesBasicCommands implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean b) {
        dispatcher.register(
                literal("gamestage")
                        .then(literal("check").then(argument("stage_name", string()).then(argument("player", players())
                                .requires((ServerCommandSource source) -> Permissions.check(source, "gamestages.command.check", 2))
                                .executes(this::checkGamestageCommand))))
                        .then(literal("remove").then(argument("stage_name", string()).then(argument("player", players())
                                .requires((ServerCommandSource source) -> Permissions.check(source, "gamestages.command.remove", 2))
                                .executes(this::removeGamestageCommand))))
                        .then(literal("add").then(argument("stage_name", string()).then(argument("player", players())
                                .requires((ServerCommandSource source) -> Permissions.check(source, "gamestages.command.add", 2))
                                .executes(this::addGamestageCommand))))
                        .then(literal("listall").then(argument("player", players())
                                .requires((ServerCommandSource source) -> Permissions.check(source, "gamestages.command.listall", 2))
                                .executes(this::printGamestagesCommand)))
        );
    }

    public int addGamestageCommand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        String stageName = getString(context, "stage_name");
        PlayerEntity p = getPlayer(context, "player");
        if(GameStagesAPI.addStage(p, stageName)) {
            context.getSource().sendFeedback(Text.of("Added stage \"" + stageName + "\" to " + p.getDisplayName().asString()), true);
        } else {
            context.getSource().sendFeedback(Text.of(p.getDisplayName().asString() + " already has stage \"" + stageName + "\""), true);
        }
        return 0;
    }

    public int removeGamestageCommand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        String stageName = getString(context, "stage_name");
        PlayerEntity p = getPlayer(context, "player");
        if(GameStagesAPI.removeStage(p, stageName)) {
            context.getSource().sendFeedback(Text.of("Removed stage \"" + stageName + "\" from " + p.getDisplayName().asString()), true);
        } else {
            context.getSource().sendFeedback(Text.of(p.getDisplayName().asString() + " doesn't have stage \"" + stageName + "\""), true);
        }
        return 0;
    }

    public int checkGamestageCommand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        String stageName = getString(context, "stage_name");
        PlayerEntity p = getPlayer(context, "player");
        boolean hasStage = GameStagesAPI.hasStage(p, stageName);
        Text stageText;
        if(hasStage) {
            stageText = Text.of(p.getDisplayName().asString() + " has stage \"" + stageName + "\"");
        }
        else {
            stageText = Text.of(p.getDisplayName().asString() + " doesn't have stage \"" + stageName + "\"");
        }
        context.getSource().sendFeedback(stageText, true);
        return 0;
    }

    public int printGamestagesCommand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntity p = getPlayer(context, "player");
        HashSet<String> stages = GameStagesAPI.getStages(p);
        if(stages.isEmpty()) {
            context.getSource().sendFeedback(Text.of(p.getDisplayName().asString() + " doesn't have any stages."), true);
        } else {
            context.getSource().sendFeedback(Text.of(p.getDisplayName().asString() + " has stages: " +
                    Arrays.toString(stages.toArray())), true);
        }
        return 0;
    }

}
