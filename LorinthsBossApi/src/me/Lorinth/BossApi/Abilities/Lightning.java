package me.Lorinth.BossApi.Abilities;

import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Lightning extends Action{

	TargetType type;
	double damage;
	double radius;
	
	public Lightning(TargetType type, double damage){
		this.type = type;
		this.damage = damage;
	}
	
	public Lightning(TargetType type, double damage, double radius){
		this.type = type;
		this.damage = damage;
		this.radius = radius;
	}
	
	@Override
	public void execute(Creature ent){
		if(type == TargetType.Target){
			LivingEntity targ = ent.getTarget();
			targ.getWorld().strikeLightningEffect(targ.getLocation()).setSilent(true);
            targ.getWorld().playSound(targ.getLocation(), Sound.ENTITY_LIGHTNING_IMPACT, 1, 1);
			if(damage > 0){
				targ.damage(damage);
			}
		}
		else if(type == TargetType.Self){
			ent.getWorld().strikeLightningEffect(ent.getLocation()).setSilent(true);
            ent.getWorld().playSound(ent.getLocation(), Sound.ENTITY_LIGHTNING_IMPACT, 1, 1);
			if(damage > 0){
				ent.damage(damage);
			}
		}
		else if(type == TargetType.AoePlayers){
			for(Player p : getNearbyPlayers(radius, ent)){
				p.getWorld().strikeLightningEffect(p.getLocation()).setSilent(true);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_IMPACT, 1, 1);
				if(damage > 0){
					p.damage(damage);
				}
			}
		}
		else if(type == TargetType.AoeCreatures){
			for(Creature c : getNearbyCreatures(radius, ent)){
				c.getWorld().strikeLightningEffect(c.getLocation()).setSilent(true);
                c.getWorld().playSound(c.getLocation(), Sound.ENTITY_LIGHTNING_IMPACT, 1, 1);
				if(damage > 0){
					c.damage(damage);
				}
			}
		}
	}
	
}
