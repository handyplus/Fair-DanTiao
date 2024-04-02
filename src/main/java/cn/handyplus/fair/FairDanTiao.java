package cn.handyplus.fair;

import cn.handyplus.fair.util.ConfigUtil;
import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 主类
 *
 * @author handy
 */
public class FairDanTiao extends JavaPlugin {
    public static FairDanTiao INSTANCE;
    public static boolean USE_TITLE;
    public static boolean USE_RACE;
    public static boolean USE_GUILD;

    @Override
    public void onEnable() {
        INSTANCE = this;
        InitApi initApi = InitApi.getInstance(this);
        // 加载配置文件
        ConfigUtil.init();
        // 加载PlayerTitle
        this.loadPlayerTitle();
        // 加载PlayerRace
        this.loadPlayerRace();
        // 加载PlayerGuild
        this.loadPlayerGuild();

        initApi.initCommand("cn.handyplus.fair.command")
                .initListener("cn.handyplus.fair.listener");

        MessageUtil.sendConsoleMessage(ChatColor.GREEN + "已成功载入服务器！");
        MessageUtil.sendConsoleMessage(ChatColor.GREEN + "Author:handy 使用文档: https://ricedoc.handyplus.cn/wiki/Fair-DanTiao/");
    }

    @Override
    public void onDisable() {
        InitApi.disable();
    }

    /**
     * 加载PlayerTitle
     */
    public void loadPlayerTitle() {
        USE_TITLE = Bukkit.getPluginManager().getPlugin("PlayerTitle") != null;
        if (USE_TITLE) {
            MessageUtil.sendConsoleMessage("&a已成功加载PlayerTitle 启用兼容称号buff功能.");
        } else {
            MessageUtil.sendConsoleMessage("&7你的服务端没有安装PlayerTitle 未启用兼容称号buff功能.");
        }
    }

    /**
     * 加载 PlayerRace
     */
    public void loadPlayerRace() {
        USE_RACE = Bukkit.getPluginManager().getPlugin("PlayerRace") != null;
        if (USE_RACE) {
            MessageUtil.sendConsoleMessage("&a已成功加载playerRace 兼容种族功能.");
        } else {
            MessageUtil.sendConsoleMessage("&7你的服务端没有安装playerRace 未兼容种族功能.");
        }
    }

    /**
     * 加载 PlayerGuild
     */
    public void loadPlayerGuild() {
        USE_GUILD = Bukkit.getPluginManager().getPlugin("PlayerGuild") != null;
        if (USE_GUILD) {
            MessageUtil.sendConsoleMessage("&a已成功加载PlayerGuild 兼容公会pvp功能.");
        } else {
            MessageUtil.sendConsoleMessage("&7你的服务端没有安装PlayerGuild 未兼容公会pvp功能.");
        }
    }

}