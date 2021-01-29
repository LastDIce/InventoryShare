package org.lastdice.inventoryshare;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
public final class InventoryShare extends JavaPlugin implements Listener 

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

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent e) {
        share_Inventory(e.getPlayer());
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent e) {
        share_Inventory(e.getPlayer());
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent e) {
        share_Inventory(e.getPlayer());
    }

    @EventHandler
    public void itemConsumeEvent(PlayerItemConsumeEvent e) {
        share_Inventory(e.getPlayer());
    }

    @EventHandler
    public void interactEvent(PlayerInteractEvent e) {
        share_Inventory(e.getPlayer());
    }

    @EventHandler
    public void inventoryDragEvent(InventoryDragEvent e) {
        share_Inventory((Player) e.getWhoClicked());
    }

}
