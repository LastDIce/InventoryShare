package org.lastdice.inventoryshare;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryShare extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("제작: LastDice#7033");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void share_Inventory(Player players) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p == players) continue;
                p.getInventory().setArmorContents(players.getInventory().getArmorContents());
                p.getInventory().setContents(players.getInventory().getContents());
            }
        }, 1L);
    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent e) {
        share_Inventory(e.getPlayer());
    }

    @EventHandler
    public void pickupEvent(EntityPickupItemEvent e) {
        if(e.getEntity() instanceof Player)
            share_Inventory((Player) e.getEntity());
    }

    @EventHandler
    public void deathEvent(PlayerDeathEvent e) {
        share_Inventory(e.getEntity());
    }

    @EventHandler
    public void invClickEvent(InventoryClickEvent e) {
        share_Inventory((Player) e.getWhoClicked());
    }

}
