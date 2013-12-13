package zako02anaso.GrowForest;

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
		
	}
	
	@EventHandler
	public void saplingSpawnEvent(ItemSpawnEvent event)
	{
		MaterialData data = event.getEntity().getItemStack().getData();
		if (data != null && data instanceof Tree)
		{
			// 非推奨だけどダメージ値の取り方がわからなかった
			Byte b = data.getData();
			System.out.println(event.getEntity().getItemStack().getType().name() + " : " + b);
		}
	}
}
