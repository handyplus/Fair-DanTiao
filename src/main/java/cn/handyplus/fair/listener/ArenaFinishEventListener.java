package cn.handyplus.fair.listener;

import cn.handyplus.fair.FairDanTiao;
import cn.handyplus.fair.util.ConfigUtil;
import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.race.api.PlayerRaceApi;
import com.handy.guild.api.PlayerGuildApi;
import com.handy.playertitle.api.PlayerTitleApi;
import com.valorin.api.event.arena.ArenaDrawFinishEvent;
import com.valorin.api.event.arena.ArenaFinishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * 比赛结束
 *
 * @author handy
 */
@HandyListener
public class ArenaFinishEventListener implements Listener {

    /**
     * ArenaFinishEvent 比赛结束事件（非平局）.
     *
     * @param event 事件
     */
    @EventHandler
    public void onEvent(ArenaFinishEvent event) {
        this.execute(event.getLoser(), event.getWinner());
    }

    /**
     * ArenaDrawFinishEvent 比赛结束事件（平局）
     *
     * @param event 事件
     */
    @EventHandler
    public void onEvent(ArenaDrawFinishEvent event) {
        this.execute(event.getPlayer1(), event.getPlayer2());
    }

    /**
     * 处理
     */
    private void execute(Player player1, Player player2) {
        // 添加称号buff
        if (FairDanTiao.USE_TITLE && ConfigUtil.CONFIG.getBoolean("playerTitleBuff", true)) {
            PlayerTitleApi.getInstance().setBuff(player1);
            MessageUtil.sendMessage(player1, ConfigUtil.CONFIG.getString("playerTitleBuffOnMsg"));
            PlayerTitleApi.getInstance().setBuff(player2);
            MessageUtil.sendMessage(player2, ConfigUtil.CONFIG.getString("playerTitleBuffOnMsg"));
        }
        // 添加种族之力
        if (FairDanTiao.USE_RACE && ConfigUtil.CONFIG.getBoolean("playerRaceBuff", true)) {
            PlayerRaceApi.getInstance().resetPlayerRace(player1);
            MessageUtil.sendMessage(player1, ConfigUtil.CONFIG.getString("playerRaceBuffOnMsg"));
            PlayerRaceApi.getInstance().resetPlayerRace(player2);
            MessageUtil.sendMessage(player2, ConfigUtil.CONFIG.getString("playerRaceBuffOnMsg"));
        }
        // 公会pvp处理
        if (FairDanTiao.USE_GUILD && ConfigUtil.CONFIG.getBoolean("playerGuildPvp", true)) {
            PlayerGuildApi.getInstance().openPvp(player1);
            MessageUtil.sendMessage(player1, ConfigUtil.CONFIG.getString("playerGuildPvpOnMsg"));
            PlayerGuildApi.getInstance().openPvp(player2);
            MessageUtil.sendMessage(player2, ConfigUtil.CONFIG.getString("playerGuildPvpOnMsg"));
        }
    }

}
