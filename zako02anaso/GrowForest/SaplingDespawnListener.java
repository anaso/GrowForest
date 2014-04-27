package zako02anaso.GrowForest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Tree;

public class SaplingDespawnListener implements Listener
{
	public SaplingDespawnListener(GrowForest growForest)
	{
		growForest.getServer().getPluginManager().registerEvents(this, growForest);
	}

	@EventHandler
	public void saplingDespawnEvent(ItemDespawnEvent event)
	{
		MaterialData eventItemData = event.getEntity().getItemStack().getData();

		if (eventItemData != null && eventItemData instanceof Tree)
		{
			// メタデータの取得は結局非推奨です
			byte saplingMetadata = eventItemData.getData();
			
			//Tree saplingData = (Tree)eventItemData;
			//String saplingNameString = saplingData.getSpecies().toString();
			//MaterialData setSapling = new Tree(TreeSpecies.valueOf(saplingNameString));

			// ワールドを取得
			World world = Bukkit.getServer().getWorld(event.getLocation().getWorld().getUID());

			// 苗木の1つ下のブロックを取得
			Material baseBlock = world.getBlockAt(event.getLocation().getBlockX(), event.getLocation().getBlockY()-1, event.getLocation().getBlockZ()).getType();

			// 1つ下が草か土ならば
			if(baseBlock == Material.GRASS || baseBlock == Material.DIRT)
			{
				// 今の場所にブロックが何も無いならば
				if(world.getBlockAt(event.getLocation().getBlockX(), event.getLocation().getBlockY(), event.getLocation().getBlockZ()).getType() == Material.AIR)
				{
					// 苗木に置換する 結局メタデータの設定は非推奨のまま
					world.getBlockAt(event.getLocation()).setType(Material.SAPLING);
					world.getBlockAt(event.getLocation()).setData(saplingMetadata);
				}
			}
			//System.out.println("ItemDespawn!  " + event.getEntity().getItemStack().getType().name() + " : " + saplingNameString);
			//System.out.println("ItemDespawn!  " + event.getEntity().getItemStack().getType().name() + " : " + saplingMetadata);
		}
	}

	@EventHandler
	public void saplingSpawnEvent(ItemSpawnEvent event)
	{

	}
}
