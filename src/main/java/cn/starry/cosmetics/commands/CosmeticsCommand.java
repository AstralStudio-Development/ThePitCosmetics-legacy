package cn.starry.cosmetics.commands;

import cn.starry.cosmetics.Cosmetics;
import cn.starry.cosmetics.api.cosmetics.CosmeticType;
import cn.starry.cosmetics.menu.CosmeticMenu;
import cn.starry.cosmetics.menu.menus.cosmetics.CosmeticsMenu;
import cn.starry.cosmetics.util.TextUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CosmeticsCommand implements CommandExecutor {

    public static char titledot = 254;

    public static String dot = " &6▪ ";
    public static String reddot = " &c▪ ";

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player)sender;
        if (args.length == 0) {
            defaultMessage(player);
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("menu")) {
                new CosmeticsMenu().openMenu(player);
            } else if (args[0].equalsIgnoreCase("add")) {
                player.sendMessage(TextUtil.colorize("&7用法: &c/cosmetics add <玩家> <类别ID> <ID>"));
            } else {
                defaultMessage(player);
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("menu")){
                if (args[1].equalsIgnoreCase("projectile-trail")) {
                    CosmeticMenu.open(CosmeticType.getCosmeticType(0), player, 0);
                } else if (args[1].equalsIgnoreCase("death-cry")) {
                    CosmeticMenu.open(CosmeticType.getCosmeticType(5), player, 0);
                } else if (args[1].equalsIgnoreCase("kill-effect")) {
                    CosmeticMenu.open(CosmeticType.getCosmeticType(2), player, 0);
                } else {
                    player.sendMessage(TextUtil.colorize("&c不存在该名称的类别"));
                }
            } else if (args[0].equalsIgnoreCase("add")) {
                player.sendMessage(TextUtil.colorize("&c功能仍在开发,此功能将在下个版本更新"));
            }
        }
        return true;
    }

    public void defaultMessage(Player player) {
        TextComponent title = new TextComponent(TextUtil.colorize("&8&l" + titledot + " " + "&6ThePit Cosmetics" + " " + "v" + Cosmetics.getInstance().getDescription().getVersion() + " " + "&7-" + " " + (player.hasPermission("*") ? "&c管理员命令" : "&7玩家命令")));
        title.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(TextUtil.colorize("&7Author: &cStarry_Killer and pi_ka")).create()));
        TextComponent openMenu = new TextComponent(TextUtil.colorize(dot + "&7/cosmetics menu" + " " + "&8-" + " " + "&e打开主菜单"));
        openMenu.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(TextUtil.colorize("&7打开特效主菜单")).create()));
        openMenu.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/cosmetics menu"));
        TextComponent category = new TextComponent(TextUtil.colorize(dot + "&7/cosmetics menu &6<类别>" + " " + "&8-" + " " + "&e打开类别菜单"));
        category.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(TextUtil.colorize("&7打开指定类型的特效菜单")).create()));
        category.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/cosmetics menu <类别>"));
        TextComponent addCosmetics = new TextComponent(TextUtil.colorize(reddot + "&7/cosmetics add &6<玩家> <类别ID> <ID>" + " " + "&8-" + " " + "&e给予玩家特效 &c(实验性功能)"));
        addCosmetics.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(TextUtil.colorize("&7给予一名玩家指定类型的特效")).create()));
        addCosmetics.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/cosmetics add <玩家> <类别ID> <ID>"));
        player.spigot().sendMessage(title);
        player.sendMessage(TextUtil.colorize(""));
        player.spigot().sendMessage(openMenu);
        player.spigot().sendMessage(category);
        if (player.hasPermission("*")) {
            player.spigot().sendMessage(addCosmetics);
        }
    }
}
