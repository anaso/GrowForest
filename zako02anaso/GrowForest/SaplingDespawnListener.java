package zako02anaso.GrowForest;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
		MaterialData data = event.getEntity().getItemStack().getData();

		if (data != null && data instanceof Tree)
		{
			// ダメージ値の取得には非推奨の関数を使用
			Byte b = data.getData();

			// ワールドを取得
			World world = Bukkit.getServer().getWorld(event.getLocation().getWorld().getUID());

			// 苗木の1つ下のブロックを取得
			Material baseBlock = world.getBlockAt(event.getLocation().getBlockX(), event.getLocation().getBlockY()-1, event.getLocation().getBlockZ()).getType();

			// 1つ下が草か土ならば
			if(baseBlock == Material.GRASS || baseBlock == Material.DIRT)
			{
				// 今の場所にブロックがなにも無いならば
				if(world.getBlockAt(event.getLocation().getBlockX(), event.getLocation().getBlockY(), event.getLocation().getBlockZ()).getType() == Material.AIR)
				{
					// 苗木に置換する
					world.getBlockAt(event.getLocation()).setType(Material.SAPLING);
					world.getBlockAt(event.getLocation()).setData(b);
				}
			}
			System.out.println("ItemDespawn!  " + event.getEntity().getItemStack().getType().name() + " : " + b);
		}
	}

	@EventHandler
	public void saplingSpawnEvent(ItemSpawnEvent event)
	{

	}
}
