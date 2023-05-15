package dev.xmroot.testeplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().toString().contains("RIGHT_CLICK_BLOCK")) {
            if (event.getClickedBlock().getType() == Material.GOLD_BLOCK) {
                Inventory inventory = createMyInventory();
                event.getPlayer().openInventory(inventory);
                event.getPlayer().sendMessage("§eVocê está negociando com o OS!");
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.GOLD_BLOCK) {
            event.getPlayer().sendMessage("§aVocê colocou a lojinha!");
        }
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.GOLD_BLOCK) {
            event.getPlayer().sendMessage("§cVocê quebrou a lojinha!");
        }
    }

    private Inventory createMyInventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, "Loja de esperma do LM");

        ItemStack item1 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta1 = item1.getItemMeta();
        itemMeta1.setDisplayName("§bEspada que furou o Briian");
        item1.setItemMeta(itemMeta1);

        ItemStack item2 = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName("§6Barra que o LM cagou");
        item2.setItemMeta(itemMeta2);

        ItemStack item3 = new ItemStack(Material.APPLE);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName("§aZappy devolve freenom");
        item3.setItemMeta(itemMeta3);

        ItemStack item4 = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName("§dDiamante rosa");
        item4.setItemMeta(itemMeta4);

        inventory.setItem(40, item4);
        inventory.setItem(20, item1);
        inventory.setItem(22, item2);
        inventory.setItem(24, item3);

        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;
        if (clickedInventory.getTitle().equals("Loja de esperma do LM")) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
            if (clickedItem.getType() == Material.DIAMOND_SWORD) {
                Player player = (Player) event.getWhoClicked();
                player.sendMessage("§bEssa espada fez o briian ter hemorroida");
                event.setCancelled(true);
            } else if (clickedItem.getType() == Material.GOLD_INGOT) {
                Player player = (Player) event.getWhoClicked();
                player.sendMessage("§6LM PARA DE CAGAR NAS COISAS!");
                event.setCancelled(true);
            } else if (clickedItem.getType() == Material.APPLE) {
                Player player = (Player) event.getWhoClicked();
                player.sendMessage("§aZappy pfv corre atras dos indianos!");
                event.setCancelled(true);
            } else if (clickedItem.getType() == Material.DIAMOND) {
                Player player = (Player) event.getWhoClicked();
                player.sendMessage("§dVoce comprou a doce ariela!");
                event.setCancelled(true);
            }
        }
    }


}

