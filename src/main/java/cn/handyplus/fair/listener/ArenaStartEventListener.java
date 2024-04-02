package cn.handyplus.fair.listener;

import cn.handyplus.fair.FairDanTiao;
import cn.handyplus.fair.util.ConfigUtil;
import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.race.api.PlayerRaceApi;
import com.handy.guild.api.PlayerGuildApi;
import com.handy.playertitle.api.PlayerTitleApi;
import com.valorin.api.event.arena.ArenaStartEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * 比赛开始事件
 *
 * @author handy
 */
@HandyListener
public class ArenaStartEventListener implements Listener {

    /**
     * 处理开始.
     *
     * @param event 事件
     */
    @EventHandler
    public void onEvent(ArenaStartEvent event) {
        Player player1 = event.getPlayer1();
        Player player2 = event.getPlayer2();

        // 称号buff处理
        if (FairDanTiao.USE_TITLE && ConfigUtil.CONFIG.getBoolean("playerTitleBuff", true)) {
            PlayerTitleApi.getInstance().removeBuff(player1);
            MessageUtil.sendMessage(player1, ConfigUtil.CONFIG.getString("playerTitleBuffOffMsg"));
            PlayerTitleApi.getInstance().removeBuff(player2);
            MessageUtil.sendMessage(player2, ConfigUtil.CONFIG.getString("playerTitleBuffOffMsg"));
        }
        // 种族处理
        if (FairDanTiao.USE_RACE && ConfigUtil.CONFIG.getBoolean("playerRaceBuff", true)) {
            PlayerRaceApi.getInstance().temporaryCancel(player1);
            MessageUtil.sendMessage(player1, ConfigUtil.CONFIG.getString("playerRaceBuffOffMsg"));
            PlayerRaceApi.getInstance().temporaryCancel(player2);
            MessageUtil.sendMessage(player2, ConfigUtil.CONFIG.getString("playerRaceBuffOffMsg"));
        }
        // 公会pvp处理
        if (FairDanTiao.USE_GUILD && ConfigUtil.CONFIG.getBoolean("playerGuildPvp", true)) {
            PlayerGuildApi.getInstance().closePvp(player1);
            MessageUtil.sendMessage(player1, ConfigUtil.CONFIG.getString("playerGuildPvpOffMsg"));
            PlayerGuildApi.getInstance().closePvp(player2);
            MessageUtil.sendMessage(player2, ConfigUtil.CONFIG.getString("playerGuildPvpOffMsg"));
        }
    }

}